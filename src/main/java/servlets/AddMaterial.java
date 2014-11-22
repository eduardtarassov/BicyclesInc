package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.StoreModel;

import org.apache.catalina.Store;

import utils.ConnectionUtil;


@WebServlet(name = "AddMaterial", urlPatterns = "/AddMaterial")
public class AddMaterial extends HttpServlet
{
	
	private DataSource dataSource = null;
    private Connection conn;

    public void init(ServletConfig config) throws ServletException {
        // Get DataSource
        dataSource = ConnectionUtil.getMySQLDataSource();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
		StoreModel sm = new StoreModel();
		conn = dataSource.getConnection();
        sm.setConnection(conn);
		sm.addMaterial(request.getParameter("material"), request.getParameter("amount"), request.getParameter("units"),
			request.getParameter("unit_price"), request.getParameter("description"));
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("message.jsp");
			request.setAttribute("message", "New Material was successfully added!");
		
		rd.forward(request, response);
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, null, conn);
        }
	}
}