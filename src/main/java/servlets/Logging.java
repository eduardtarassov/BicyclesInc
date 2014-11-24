package servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import containers.LoginState;
import models.UserModel;
import utils.ConnectionUtil;

@WebServlet(name = "Logging", urlPatterns = {
		"/login",
		"/logout"
})
public class Logging extends HttpServlet {


    private static final String HOME_PAGE = "/index.jsp";
    private static final String LOGIN_PAGE = "/login.jsp";

    private DataSource dataSource = null;
    private Connection conn;

    public void init(ServletConfig config) throws ServletException {
        // Get DataSource
        dataSource = ConnectionUtil.getMySQLDataSource();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String strUsername = request.getParameter("username");
        String strPassword = request.getParameter("password");
        String strErrMsg = null;
        HttpSession session = request.getSession();
        UserModel us = new UserModel();
        boolean isValidLogon = false;
        try {
            conn = dataSource.getConnection();
            us.setConnection(conn);
            isValidLogon = us.IsValidUser(strUsername, strPassword, session);
            if (isValidLogon) {
                session.setAttribute("username", strUsername);
            } else {
                System.out.println("Username or Password is invalid. Please try again.");
            }


            // Maybe it is better to put it into upper if statement
            if (isValidLogon) {              
                
                String message = "You have successfully logged in. Welcome!";
                request.setAttribute("message", message);
               
                RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
                rd.forward(request, response);
            } else {
            	String message = "Your logging details are incorrect!";
                request.setAttribute("message", message);
               
                RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, null, conn);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        LoginState lg = (LoginState) session.getAttribute("LoggedIn");
        lg.setLoginState(false);
        lg.setUsername("visitor");
        request.setAttribute("LoggedIn", lg);
        String message = "You have successfully logged out! It was nice having you here.";
        request.setAttribute("message", message);
       System.out.println("TESTTING");
        RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
        rd.forward(request, response);
    }
}