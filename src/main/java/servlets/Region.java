package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;

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

import containers.DemographicInfo;
import containers.ProfileInfo;
import models.DemographicModel;
import models.ProfileModel;
import utils.ConnectionUtil;
import utils.Convertors;

/**
 * Created by Luke
 */
@WebServlet(name = "Region", urlPatterns = {"/region"})
@MultipartConfig

public class Region extends HttpServlet {
    private DataSource dataSource = null;
    private Connection conn;
    private DemographicModel dModel;
    private String regionName;
    
    public void init(ServletConfig config) throws ServletException {
        // Get DataSource
        dataSource = ConnectionUtil.getMySQLDataSource();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	regionName = request.getParameter("RegionName");
    	System.out.println("Set region name 1 " + regionName);
    	response.sendRedirect("region");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	System.out.println("Set region name 2 "+regionName);
        try {
            conn = dataSource.getConnection();
            dModel = new DemographicModel();
            dModel.setConnection(this.conn);
            displayRegion(regionName, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, null, conn);
        }
    }
    
    private void displayRegion(String region, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("Display Region information");
	    RequestDispatcher rd = null;
	    DemographicInfo dInfo = dModel.getRegionsForSearch(region);
	    rd = request.getRequestDispatcher("demographics.jsp");
	    request.setAttribute("DemographicInfo", dInfo);
	
	    rd.forward(request, response);
	}
    
    
    }

	