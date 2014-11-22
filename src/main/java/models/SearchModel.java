package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;






import utils.ConnectionUtil;
import containers.Material;
import containers.ProfileInfo;
import containers.StockItem;

/**
 * Created by Eduard on 19/11/2014.
 */
public class SearchModel {

    Connection conn;

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public LinkedList<ProfileInfo> getUsersForSearch(String username) {
        LinkedList<ProfileInfo> profiles = new LinkedList<ProfileInfo>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ProfileInfo proInfo = null;
            ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setString(1, username);

            System.out.println("This is your prepared Statement: " + ps.toString());
            
            rs = ps.executeQuery(); // this is where the query is executed

            if (rs.first()) {
                do {
                    proInfo = new ProfileInfo(username);
                    

                    System.out.println("User found");
                    profiles.add(proInfo);

                } while (rs.next());

            } else { ///
                System.out.println("No users found by this name");
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs, ps, null);
        }

        return profiles;
    }
    
    
    public LinkedList<StockItem> getStockItemsForSearch(String key){
    	 LinkedList<StockItem> items = new LinkedList<StockItem>();
         PreparedStatement ps = null;
         ResultSet rs = null;
         try {
             StockItem item = null;
             ps = conn.prepareStatement("SELECT * FROM stock_items WHERE displayed_name LIKE ?");
             ps.setString(1, "%" + key + "%");

             System.out.println("This is your prepared Statement: " + ps.toString());
             
             rs = ps.executeQuery(); // this is where the query is executed

             if (rs.first()) {
                 do {
                	 System.out.println("Stock Item found");
                	
                	 
                     item = new StockItem(
                    		 rs.getInt("product_id"), 
                    		 rs.getString("displayed_name"), 
                    		 rs.getString("description"), 
                    		 rs.getFloat("price"),
                    		 rs.getInt("available_number"),
                    		 rs.getInt("number_sold"),
                    		 rs.getInt("discount"),
                    		 rs.getInt("image_id"));
                     

                     
                     items.add(item);

                 } while (rs.next());

             } else { ///
                 System.out.println("No products found by this value");
                 return null;
             }


         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             ConnectionUtil.close(rs, ps, null);
         }

         return items;
    }


    public LinkedList<Material> getMaterialsForSearch(String key){
   	 LinkedList<Material> materials = new LinkedList<Material>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Material material = null;
            ps = conn.prepareStatement("SELECT * FROM raw_materials WHERE material LIKE ?");
            ps.setString(1, "%" + key + "%");

            System.out.println("This is your prepared Statement: " + ps.toString());
            
            rs = ps.executeQuery(); // this is where the query is executed

            if (rs.first()) {
                do {
               	 System.out.println("Material found");
               	 
               	 
                    material = new Material(
                   		 rs.getInt("material_id"), 
                   		 rs.getString("material"), 
                   		 rs.getString("description"), 
                   		 rs.getFloat("unit_price"),
                   		 rs.getString("units"),
                   		 rs.getInt("amount"));
                    

                    
                    materials.add(material);

                } while (rs.next());

            } else { ///
                System.out.println("No materials found by this value");
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs, ps, null);
        }

        return materials;
   }
}
