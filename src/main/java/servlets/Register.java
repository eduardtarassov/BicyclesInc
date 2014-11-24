package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.UserModel;
import utils.ConnectionUtil;
import utils.Convertors;
import utils.Validation;

@WebServlet(name = "Register", urlPatterns = {"/register", "/register_staff"})
public class Register extends HttpServlet {

    private static final String HOME_PAGE = "/index.jsp";

    private DataSource dataSource = null;
    private Connection conn;

    public void init(ServletConfig config) throws ServletException {
        // Get DataSource
        dataSource = ConnectionUtil.getMySQLDataSource();
    }



    /*
        * fetches the request parameters and generates the insert query
        * which will be passed on to the executeQuery method of the ConnectionUtil.
        * Below is the code for the method
         */
    private String[] generateRequestParams(HttpServletRequest request) {

        String[] strRequestParams = new String[9];

        strRequestParams[0] = request.getParameter("id");
        strRequestParams[1] = request.getParameter("password");
        strRequestParams[2] = request.getParameter("first_name");
        strRequestParams[3] = request.getParameter("last_name");
        strRequestParams[4] = request.getParameter("secret_question");
        strRequestParams[5] = request.getParameter("secret_answer");
        strRequestParams[6] = request.getParameter("email");
        strRequestParams[7] = request.getParameter("date_of_birth");
        strRequestParams[8] = request.getParameter("company_affiliation");
        
        return strRequestParams;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    	System.out.println("This is my test!");
    	
    	String args[] = Convertors.SplitRequestPath(request);
    	
    	
        String[] strRequestParams = generateRequestParams(request);
        //registerValidation(strRequestParams, request, response); // ERROR
        UserModel us = new UserModel();

        try {
            conn = dataSource.getConnection();
            us.setConnection(conn);
            
            if (args[1] == "register")
            us.registerUser(strRequestParams, true, false);
            else
            	us.registerUser(strRequestParams, true, true);

            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, null, conn);
        }

        System.out.println("Insert into database successful");
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    	System.out.println("This is my test!");
        

    }
    
    public void registerValidation(String[] params, HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    	
    	//RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
    	
		for (int i = 0; i < params.length; i++){
			if (!Validation.checkTextField(params[i])){
				System.out.println("Text field number " + i + " cannot be empty!");	
				/*rd.forward(request, response);*/
				
			}	
		}
		
		if (!Validation.checkDate(params[8])){
			System.out.println("Date should be of type dd/mm/yyyy!");
			/*rd.forward(request, response);*/
		}
		
		System.out.println("All the values are ok!");
	}
    
    
}