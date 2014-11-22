package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import models.SearchModel;
import utils.ConnectionUtil;
import utils.Convertors;
import containers.Material;
import containers.ProfileInfo;
import containers.StockItem;

/**
 * Created by Eduard on 19/11/2014.
 */
@WebServlet(name = "Search", urlPatterns = { "/search", "/show_stock_items",
		"/show_materials", "/show_products" })
public class Search extends HttpServlet {
	private DataSource dataSource = null;
	private Connection conn;

	public void init(ServletConfig config) throws ServletException {
		// Get DataSource
		dataSource = ConnectionUtil.getMySQLDataSource();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			conn = dataSource.getConnection();
			SearchModel searchModel = new SearchModel();
			searchModel.setConnection(conn);
			LinkedList<ProfileInfo> profiles = null;
			profiles = searchModel.getUsersForSearch(request
					.getParameter("username"));

			request.setAttribute("profiles_found", profiles);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(null, null, conn);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			String args[] = Convertors.SplitRequestPath(request);

			conn = dataSource.getConnection();
			SearchModel searchModel = new SearchModel();
			searchModel.setConnection(conn);
			switch (args[1]) {
			case "show_materials":
				LinkedList<Material> materials = null;
				materials = searchModel.getMaterialsForSearch("");
				request.setAttribute("inmaterials_result", materials);
				rd = request.getRequestDispatcher("/show_materials.jsp");
				break;
			case "show_products":

				break;
			case "show_stock_items":
				LinkedList<StockItem> items = null;
				items = searchModel.getStockItemsForSearch("");
				request.setAttribute("instock_result", items);
				rd = request.getRequestDispatcher("/shop_index.jsp");
				break;
			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(null, null, conn);
		}
		 
		rd.forward(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}