package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import containers.ProfileInfo;
import models.ProfileModel;
import models.UserModel;
import utils.ConnectionUtil;
import utils.Convertors;

@WebServlet(urlPatterns = {
        "/UpdateProfileInformation/*"
})
@MultipartConfig

public class Profile extends HttpServlet {
    private DataSource dataSource = null;
    private Connection conn;
    private ProfileModel pModel;
    private String username;
    private static final String HOME_PAGE = "/index.jsp";

    public void init(ServletConfig config) throws ServletException {
        // Get DataSource
        dataSource = ConnectionUtil.getMySQLDataSource();

    }

    private String[] generateRequestParams(HttpServletRequest request) {

        String[] strRequestParams = new String[7];

        strRequestParams[0] = request.getParameter("password");
        strRequestParams[1] = request.getParameter("first_name");
        strRequestParams[2] = request.getParameter("last_name");
        strRequestParams[3] = request.getParameter("email_address");
        strRequestParams[4] = request.getParameter("secret_question");
        strRequestParams[5] = request.getParameter("secret_answer");
        strRequestParams[6] = request.getParameter("username");

        return strRequestParams;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] strRequestParams = generateRequestParams(request);
        UserModel us = new UserModel();

        try {
            conn = dataSource.getConnection();
            us.setConnection(conn);
            us.registerUser(strRequestParams, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, null, conn);
        }

        System.out.println("Insert into database successful");
        response.sendRedirect(HOME_PAGE);
        
        
        String message = "Update profile successful!";
        request.setAttribute("message", message);
       
        RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
