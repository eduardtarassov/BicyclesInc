package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.StockModel;
import models.UserModel;
import utils.ConnectionUtil;
import utils.Validation;


@WebServlet(name = "AddStockItem", urlPatterns = {"/add_stock_item"})
public class AddStockItem extends HttpServlet {

    private static final String HOME_PAGE = "/shop_index.jsp";

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

        String[] strRequestParams = new String[7];

        strRequestParams[0] = request.getParameter("displayed_name");
        strRequestParams[1] = request.getParameter("description");
        strRequestParams[2] = request.getParameter("price");
        strRequestParams[3] = request.getParameter("available_number");
        strRequestParams[4] = request.getParameter("number_sold");
        strRequestParams[5] = request.getParameter("discount");
        strRequestParams[6] = request.getParameter("image_id");
        
        return strRequestParams;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    	System.out.println("This is my servlet ADDSTOCKITEM!");
        String[] strRequestParams = generateRequestParams(request);
        fieldsValidation(strRequestParams, request, response);
        StockModel sm = new StockModel();

        try {
            conn = dataSource.getConnection();
            sm.setConnection(conn);
            sm.addToStock(strRequestParams, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, null, conn);
        }

        System.out.println("Insert into database successful");
        
        String message = "New Stock Item successfully added!";
        request.setAttribute("message", message);
        
        RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
        rd.forward(request, response);

    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    	System.out.println("This is my test!");
        

    }
    
    public void fieldsValidation(String[] params, HttpServletRequest request, HttpServletResponse
    response) throws ServletException, IOException {
    	
		for (int i = 0; i < params.length; i++){
			if (!Validation.checkTextField(params[i])){
				System.out.println("Text field number " + i + " cannot be empty!");				
			}	
		}	
		System.out.println("All the values are ok!");
	}
    
    
}