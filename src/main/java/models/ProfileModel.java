package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.ConnectionUtil;
import containers.ProfileInfo;

public class ProfileModel {
    Connection conn;

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public ProfileInfo getProfileInfo(String username) {
        ProfileInfo proInfo = new ProfileInfo(username);
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement("SELECT * FROM users WHERE id='" + username + "'");


            System.out.println("This is your prepared Statement: " + ps.toString());
            rs = ps.executeQuery(); // this is where the query is executed
            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String emailaddress = rs.getString("email");

                System.out.println("User information retrieved successfully!");
                proInfo.setUsername(username);
                proInfo.setFirstname(firstname);
                proInfo.setLastname(lastname);
                proInfo.setEmailaddress(emailaddress);

            } else {
                System.out.println("No user returned!");
                return null;
            }
       


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs, ps, null);
        }
        return proInfo;
    }
}
