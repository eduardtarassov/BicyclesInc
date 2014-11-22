package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import models.ProductModel;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet(name = "AddProduct", urlPatterns = "/AddProduct")
public class AddProduct extends HttpServlet
{       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ProductModel pModel = new ProductModel();
		request.setAttribute("MaterialList", pModel.getMaterials());
		request.setAttribute("PartList", pModel.getParts());
		
		RequestDispatcher rd = request.getRequestDispatcher("add_product.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ProductModel pModel = new ProductModel();
		String message = "Error has occured";
		
		ArrayList<String> productDetailsList = new ArrayList<>();
		productDetailsList.add(request.getParameter("productName"));
		productDetailsList.add(request.getParameter("height"));
		productDetailsList.add(request.getParameter("width"));
		productDetailsList.add(request.getParameter("colour"));
		productDetailsList.add(request.getParameter("productionCost"));
		productDetailsList.add(request.getParameter("design"));
		
		if(request.getParameter("productType").equals("part"))
		{
			productDetailsList.add(request.getParameter("partType"));
			
			String material = null;
			int i = 0;
			
			do
			{
				String name = "material" + Integer.toString(i++);
				material = request.getParameter(name);
				if(material != null)
				{
					productDetailsList.add(material);
				}
			}
			while(material != null);
			
			pModel.addPart(productDetailsList);
			message = "New Part was successfully added!";
		}
		
		else
		{
			productDetailsList.add(request.getParameter("bicycleType"));
			productDetailsList.add(request.getParameter("releaseDay"));
			productDetailsList.add(request.getParameter("releaseMonth"));
			productDetailsList.add(request.getParameter("releaseYear"));
			productDetailsList.add(request.getParameter("packagingType"));
			
			String part = null;
			int i = 0;
			
			do
			{
				String name = "part" + Integer.toString(i);
				part = request.getParameter(name);
				if(part != null)
				{
					productDetailsList.add(part);
					
					name = "quantity" + Integer.toString(i++);
					productDetailsList.add(request.getParameter(name));
				}
			}
			while(part != null);
			
			pModel.addBicycle(productDetailsList);
			message = "New Product was successfully added!";
	
		}
		
		
        request.setAttribute("message", message);
       
        RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
        rd.forward(request, response);
	}
}
