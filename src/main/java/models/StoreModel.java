package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import containers.Material;
import containers.ProfileInfo;
import utils.ConnectionUtil;


public class StoreModel {
	Connection conn;
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public void addMaterial(String material, String amount, String units,
			String unitPrice, String description) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO raw_materials"
					+ "(material, description, unit_price, units, amount) "
							+ "VALUES(?,?,?,?,?)");

			ps.setString(1, material);
			ps.setString(2, description);
			ps.setString(3, unitPrice);
			ps.setString(4, units);
			ps.setString(5, amount);

			ps.executeUpdate();


		} catch (Exception e) {
            System.out.println("Error: Problem with connection.");
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(null, ps, null);
        }
	}
	
	 public Material getMaterialInfo(String id) {
	        Material material = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        
	        try {
	            ps = conn.prepareStatement("SELECT * FROM raw_materials WHERE material_id='" + id + "'");


	            System.out.println("This is your prepared Statement: " + ps.toString());
	            rs = ps.executeQuery(); // this is where the query is executed
	            if (rs.next()) {
	            	material = new Material(
	                   		 rs.getInt("material_id"), 
	                   		 rs.getString("material"), 
	                   		 rs.getString("description"), 
	                   		 rs.getFloat("unit_price"),
	                   		 rs.getString("units"),
	                   		 rs.getInt("amount"));

	            } else {
	                System.out.println("No material returned!");
	                return null;
	            }
	       


	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionUtil.close(rs, ps, null);
	        }
	        return material;
	    }
}
