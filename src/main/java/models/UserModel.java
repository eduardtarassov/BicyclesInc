package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import containers.LoginState;
import utils.ConnectionUtil;
import utils.CryptMD5;

public class UserModel {
    private static final String LOGIN_QUERY = "select * from users where id=? and password=?";
    Connection conn;

    public UserModel() {
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }


    /* Registers user. Or updates existing user info*/
    public boolean registerUser(String[] strRequestParams, boolean initial) throws SQLException {
        PreparedStatement ps = null;
            try {
                if (initial) {
                    strRequestParams[1] = CryptMD5.cryptWithMD5(strRequestParams[1]);
                    ps = conn.prepareStatement("INSERT INTO users" +
                            "(id, password, firstname, lastname, secret_question, secret_answer, core_role, email, access_level, privileges_description, deleted, last_login) VALUES" +
                            "(?,?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, strRequestParams[0]);
                ps.setString(2, strRequestParams[1]);
                ps.setString(3, strRequestParams[2]);
                ps.setString(4, strRequestParams[3]);
                ps.setString(5, strRequestParams[4]);
                ps.setString(6, strRequestParams[5]);
                
                ps.setString(7, "user");
                ps.setString(8, strRequestParams[6]);
                
                ps.setInt(9, 2);
                ps.setString(10, "You can search/purchase products.");
                ps.setBoolean(11, false);
                
                Date date = new Date();
                String modifiedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                ps.setString(12, modifiedDate);

                System.out.println("This is your ps: " + ps.toString());
                ps.executeUpdate();
                
                ps = conn.prepareStatement("INSERT INTO customers" +
                        "(id, date_of_birth, company_affiliation) VALUES" +
                        "(?,?,?)");
                ps.setString(1, strRequestParams[0]);
                ps.setString(2, strRequestParams[7]);
                ps.setString(3, strRequestParams[8]);
                
                System.out.println("This is your ps: " + ps.toString());
                ps.executeUpdate();
                }else{
                    strRequestParams[1] = CryptMD5.cryptWithMD5(strRequestParams[1]);
                    ps = conn.prepareStatement("UPDATE users SET password=?, firstname=?, lastname=?, email=?, secret_question=?, secret_answer=? WHERE id=?");
                    ps.setString(1, strRequestParams[1]);
                    ps.setString(2, strRequestParams[2]);
                    ps.setString(3, strRequestParams[3]);
                    ps.setString(4, strRequestParams[6]);
                    ps.setString(5, strRequestParams[4]);
                    ps.setString(6, strRequestParams[5]);
                    ps.setString(7, strRequestParams[0]);
                    System.out.println("This is your ps: " + ps.toString());
                    ps.executeUpdate();
                    
                    ps = conn.prepareStatement("UPDATE customers SET date_of_birth=?, company_affiliation=? WHERE id=?");
                    ps.setString(1, strRequestParams[7]);
                    ps.setString(2, strRequestParams[8]);
                    ps.setString(3, strRequestParams[0]);
                    System.out.println("This is your ps: " + ps.toString());
                    ps.executeUpdate();
                }
            } catch (Exception e) {
                System.out.println("Error: Problem with connection.");
                e.printStackTrace();
                return false;
            } finally {
                ConnectionUtil.close(null, ps, null);
            }
            
        return true;
    }


    /**
     * Internally called to validate the request data. This method does a simple
     * check for userName and password to be entered. However
     * it can be extended to add more complex check at the server side like email address validity etc.
     * * * @return boolean indicating success / failure of the validation
     */
    private boolean validateData(String strUsername, String strPassword) {
        boolean isValid = false;
        if (strUsername != null && !strUsername.equals("") && strPassword != null && !strPassword.equals("")) {
            isValid = true;
        }
        return isValid;
    }


    public boolean IsValidUser(String strUsername, String strPassword, HttpSession session) throws Exception {
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean isValid = false;
        String encodedPassword;
        try {
            encodedPassword = CryptMD5.cryptWithMD5(strPassword);
            ps = conn.prepareStatement(LOGIN_QUERY);
            ps.setString(1, strUsername);
            ps.setString(2, encodedPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("User login and password are valid in DB");
                int access = rs.getInt("access_level");
                
                LoginState lg = new LoginState();
                lg.setLoginState(true);
                lg.setUsername(strUsername);
                lg.setAccess(access);
                session.setAttribute("LoggedIn", lg);
                
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs, ps, null);
        }
        return isValid;
    }
}