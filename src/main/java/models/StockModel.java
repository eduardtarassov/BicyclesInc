package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import containers.Material;
import containers.StockItem;
import utils.ConnectionUtil;

public class StockModel {
	Connection conn;

	public StockModel() {
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/* Adds item onto stock. Or updates existing item info */
	public boolean addToStock(String[] strRequestParams, boolean initial)
			throws SQLException {
		PreparedStatement ps = null;
			try {
				ps = conn
						.prepareStatement("INSERT INTO stock_items"
								+ "(displayed_name, description, price, available_number, number_sold, discount, image_id) VALUES"
								+ "(?,?,?,?,?,?,?)");

				ps.setString(1, strRequestParams[0]);
				ps.setString(2, strRequestParams[1]);
				
				// Converting string price value to BigDecimal.
				float price = Float.parseFloat(strRequestParams[2]);
				BigDecimal finalPrice = new BigDecimal(price);
				ps.setBigDecimal(3, finalPrice);
				
				ps.setInt(4, Integer.parseInt(strRequestParams[3]));
				ps.setInt(5, Integer.parseInt(strRequestParams[4]));
				ps.setInt(6, Integer.parseInt(strRequestParams[5]));

				ps.setInt(7, Integer.parseInt(strRequestParams[6]));

				System.out.println("This is your ps: " + ps.toString());
				ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("Error: Problem with connection.");
				e.printStackTrace();
				return false;
			} finally {
				ConnectionUtil.close(null, ps, null);
			}
		return true;
	}
	
	 public StockItem getStockItemInfo(String id) {
	        StockItem stockItem = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        
	        try {
	            ps = conn.prepareStatement("SELECT * FROM stock_items WHERE product_id='" + id + "'");


	            System.out.println("This is your prepared Statement: " + ps.toString());
	            rs = ps.executeQuery(); // this is where the query is executed
	            if (rs.next()) {
	            	stockItem = new StockItem(
	                   		 rs.getInt("product_id"), 
	                   		 rs.getString("displayed_name"), 
	                   		 rs.getString("description"), 
	                   		 rs.getFloat("price"),
	                   		 rs.getInt("available_number"),
	                   		 rs.getInt("number_sold"),
	                   		 rs.getInt("discount"),
	                   		 rs.getInt("image_id"));

	            } else {
	                System.out.println("No stock item returned!");
	                return null;
	            }
	       


	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionUtil.close(rs, ps, null);
	        }
	        return stockItem;
	    }
}