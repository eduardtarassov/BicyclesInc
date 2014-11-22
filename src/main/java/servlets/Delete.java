package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mysql.jdbc.StringUtils;

import models.DeleteModel;
import utils.ConnectionUtil;
import utils.Convertors;

@WebServlet(name = "Delete", urlPatterns = { "/delete-stock_items",
		"/delete-users" })
public class Delete extends HttpServlet {

	private DataSource dataSource = null;
	private Connection conn;
	private DeleteModel dModel;

	public void init(ServletConfig config) throws ServletException {
		// Get DataSource
		dataSource = ConnectionUtil.getMySQLDataSource();
	}
	
	private String[] generateRequestParams(HttpServletRequest request) {

        String[] strRequestParams = new String[2];

        strRequestParams[0] = request.getParameter("column");
        strRequestParams[1] = request.getParameter("value");

        
        return strRequestParams;
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is my test Delete Servlet!");
		String[] strRequestParams = generateRequestParams(request);
			
		String args[] = Convertors.SplitRequestPath(request);
		
		String table = args[1].substring(args[1].lastIndexOf("-") + 1);

		try {
			conn = dataSource.getConnection();
			dModel = new DeleteModel();

			dModel.setConnection(this.conn);
			// displayProfile(username, request, response);
		delete(table, strRequestParams, request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(null, null, conn);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	private void delete(String table, String[] strRequestParams, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		System.out.println("Removing item from table: " + table);
		System.out.println("Removing item from column: " + strRequestParams[0]);
		System.out.println("Removing item with value: " + strRequestParams[1]);
		RequestDispatcher rd = null;
		boolean result = dModel.deleteItem(table, strRequestParams[0], strRequestParams[1]);
		rd = request.getRequestDispatcher("message.jsp");
		if (result)
			request.setAttribute("message", "Item(s) removed successfully!");
		else
			request.setAttribute("message", "Failure - Error in the system.");
		
		rd.forward(request, response);
	}

}