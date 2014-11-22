package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;







import containers.DemographicInfo;
import containers.ProfileInfo;
import containers.StockItem;
import utils.ConnectionUtil;


/**
 * Created by Luke on 19/11/2014.
 */
public class DemographicModel {

    Connection conn;

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public DemographicInfo getRegionsForSearch(String RegionName) {
    	DemographicInfo dInfo = new DemographicInfo(RegionName);
        //public LinkedList<DemographicInfo> getRegionsForSearch(String RegionName) {
        //LinkedList<DemographicInfo> profiles = new LinkedList<DemographicInfo>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	/*ps = conn.prepareStatement("SELECT ? FROM region WHERE RegionName = '?'");
        	ps.setString(1, "GDP");
        	ps.setString(2, "Drenthe");*/
        	
        	
            ps = conn.prepareStatement("SELECT * FROM region WHERE RegionName='" + RegionName + "'");
            System.out.println("This is your prepared Statement: " + ps.toString());            
            rs = ps.executeQuery(); // this is where the query is executed

            if (rs.next()) {               
                    //demInfo = new DemographicInfo(RegionName);
                    String regionName = rs.getString("RegionName");
                    int population = rs.getInt("Population");
                    int dryDaysPerYear = rs.getInt("DryDaysPerYear");
                    int gdp = rs.getInt("GDP");
                    int averagePopSalary = rs.getInt("AveragePopSalary");
                    
                    System.out.println("Region Found");
                    
                    dInfo.setRegionName(regionName);
                    dInfo.setPopulation(population);
                    dInfo.setDryDaysPerYear(dryDaysPerYear);
                    dInfo.setGdp(gdp);
                    dInfo.setAveragePopSalary(averagePopSalary);
                    //demographics.add(demInfo);

            } else { ///
                System.out.println("No regions found by this name");
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs, ps, null);
        }

        return dInfo;
    }  
}
