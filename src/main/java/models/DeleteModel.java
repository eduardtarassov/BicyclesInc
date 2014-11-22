package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.ConnectionUtil;

public class DeleteModel {
	Connection conn;

	public DeleteModel() {
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/* Adds item onto stock. Or updates existing item info */
	public boolean deleteItem(String table, String column, String value)
			throws SQLException {
		PreparedStatement ps = null;
			try {
				ps = conn
						.prepareStatement("DELETE FROM " + table + " WHERE " + column + "=?");

				ps.setString(1, value);

				System.out.println("This is your ps: " + ps.toString());
				ps.executeUpdate();
				return true;
			} catch (Exception e) {
				System.out.println("Error: Problem with connection.");
				e.printStackTrace();
				return false;
			} finally {
				ConnectionUtil.close(null, ps, null);
			}
	}
}