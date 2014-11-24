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
import javax.sql.DataSource;

import models.ProfileModel;
import models.StockModel;
import models.StoreModel;
import utils.ConnectionUtil;
import utils.Convertors;
import containers.Material;
import containers.ProfileInfo;
import containers.StockItem;

@WebServlet(urlPatterns = { "/Material/*", "/Profile/*", "/Product/*",  "/StockItem/*"})
@MultipartConfig
public class Item extends HttpServlet {
	private DataSource dataSource = null;
	private Connection conn;
	private StoreModel sModel;
	private ProfileModel pModel;
	private StockModel stockModel;

	private static final String HOME_PAGE = "/index.jsp";

	public void init(ServletConfig config) throws ServletException {
		// Get DataSource
		dataSource = ConnectionUtil.getMySQLDataSource();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String args[] = Convertors.SplitRequestPath(request);
		try {
			conn = dataSource.getConnection();
			

			switch (args[1]) {
			case "Product":
				// displayProduct(args[2], request, response);
				break;
			case "Material":
				sModel = new StoreModel();
				sModel.setConnection(this.conn);
				displayMaterial(args[2], request, response);
				break;
			case "Profile":
				pModel = new ProfileModel();
				pModel.setConnection(this.conn);
				displayProfile(args[2], request, response);
				break;
			case "StockItem":
				stockModel = new StockModel();
				stockModel.setConnection(this.conn);
				displayStockItem(args[2], request, response);
				break;
			}

			displayProfile(args[1], request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(null, null, conn);
		}

	}

	private void displayMaterial(String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Display Material information");
		RequestDispatcher rd = null;
		Material material = sModel.getMaterialInfo(id);
		rd = request.getRequestDispatcher("/material.jsp");
		request.setAttribute("MaterialInfo", material);

		rd.forward(request, response);
	}

	private void displayProfile(String username, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		ProfileInfo proInfo = pModel.getProfileInfo(username);
		rd = request.getRequestDispatcher("/profile.jsp");
		request.setAttribute("ProfileInfo", proInfo);

		rd.forward(request, response);
	}
	
	private void displayStockItem(String id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		StockItem stockItem = stockModel.getStockItemInfo(id);
		rd = request.getRequestDispatcher("/stock_item.jsp");
		request.setAttribute("StockItemInfo", stockItem);

		rd.forward(request, response);
	}
}
