package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import containers.Product;
import containers.StockItem;

public class ProductModel {
	private MysqlDataSource getDataSource()
	{
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("14ac3u61");
		dataSource.setPassword("132acb");
		dataSource.setDatabaseName("14ac3d61");
		dataSource.setServerName("silva.computing.dundee.ac.uk");
		
		return dataSource;
	}
	
	public void setMaterial(String material, String amount, String units, String unitPrice, String description)
	{
		MysqlDataSource dataSource = getDataSource();
		
		try
		{
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO raw_materials(material, description, unit_price, units, amount) "
					+ "VALUES(?,?,?,?,?)");
			
			preparedStatement.setString(1, material);
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, unitPrice);
			preparedStatement.setString(4, units);
			preparedStatement.setString(5, amount);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	public LinkedList<String> getMaterials()
	{
		MysqlDataSource dataSource = getDataSource();
		LinkedList<String> materialList = new LinkedList<>();
		
		try
		{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT material FROM raw_materials");
			
			while(resultSet.next())
			{
				materialList.add(resultSet.getString("material"));
			}
			
			statement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
		
		return materialList;
	}
	
	public ArrayList<String> getMaterialDetails(String material)
	{
		MysqlDataSource dataSource = getDataSource();
		ArrayList<String> materialDetailsList = new ArrayList<>();
		
		try
		{
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM raw_materials WHERE material = ?");
			
			preparedStatement.setString(1, material);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.first();
			
			materialDetailsList.add(resultSet.getString("material_id"));
			materialDetailsList.add(material);
			materialDetailsList.add(resultSet.getString("description"));
			materialDetailsList.add(resultSet.getString("unit_price"));
			materialDetailsList.add(resultSet.getString("units"));
			materialDetailsList.add(resultSet.getString("amount"));
			
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
		
		return materialDetailsList;
	}
	
	public ArrayList<String> getUnitsAndPrice(String material)
	{
		MysqlDataSource dataSource = getDataSource();
		ArrayList<String> materialDetailsList = new ArrayList<>();
		
		try
		{
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT units, unit_price FROM raw_materials WHERE material = ?");
			
			preparedStatement.setString(1, material);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.first();
			
			materialDetailsList.add(material);
			materialDetailsList.add(resultSet.getString("units"));
			materialDetailsList.add(resultSet.getString("unit_price"));
			
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
		
		return materialDetailsList;
	}
	
	public void addToMaterial(ArrayList<String> materialDetailsList)
	{
		MysqlDataSource dataSource = getDataSource();
		
		try
		{
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE raw_materials SET amount = amount + ? WHERE material = ?");
			
			preparedStatement.setInt(1, Integer.parseInt(materialDetailsList.get(1)));
			preparedStatement.setString(2, materialDetailsList.get(0));
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	public void addPart(ArrayList<String> productDetailsList)
	{
		MysqlDataSource dataSource = getDataSource();
		
		try
		{
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO products(product_name, height, width, design_properties, product_type, "
					+ "colour, cost_to_produce) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, productDetailsList.get(0));
			preparedStatement.setString(2, productDetailsList.get(1));
			preparedStatement.setString(3, productDetailsList.get(2));
			preparedStatement.setString(4, productDetailsList.get(5));
			preparedStatement.setString(5, productDetailsList.get(6));
			preparedStatement.setString(6, productDetailsList.get(3));
			preparedStatement.setString(7, productDetailsList.get(4));
			
			preparedStatement.executeUpdate();
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT MAX(product_ID) FROM products");
			resultSet.first();
			int productID = resultSet.getInt(1);
			statement.close();
			
			preparedStatement = connection.prepareStatement("INSERT INTO parts VALUES(?)");
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();
			
			ArrayList<String> materialsEnteredList = new ArrayList<>();

			for(int i = 7; i < productDetailsList.size(); i++)
			{
				String material = productDetailsList.get(i);
				
				if(!materialsEnteredList.contains(material))
				{				
					preparedStatement = connection.prepareStatement("SELECT material_id FROM raw_materials WHERE material = ?");
					preparedStatement.setString(1, material);
					resultSet = preparedStatement.executeQuery();
					resultSet.first();
					int materialID = resultSet.getInt("material_id");
					
					preparedStatement = connection.prepareStatement("INSERT INTO materials_in_part VALUES(?, ?)");
					preparedStatement.setInt(1, productID);
					preparedStatement.setInt(2, materialID);
					preparedStatement.executeUpdate();
					
					materialsEnteredList.add(material);
				}
			}
			
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	public ArrayList<Product> getParts()
	{
		MysqlDataSource dataSource = getDataSource();
		ArrayList<Product> partList = new ArrayList<>();
		
		try
		{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT products.product_id, products.product_name, products.product_type "
					+ "FROM products "
					+ "RIGHT JOIN parts "
					+ "ON products.product_id = parts.product_id");
			
			while(resultSet.next())
			{
				String productName = resultSet.getString("products.product_name") + " (" + resultSet.getString("products.product_type") + ")";
				partList.add(new Product(productName, resultSet.getInt("products.product_id")));
			}
			
			statement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
		
		return partList;
	}
	
	public void addBicycle(ArrayList<String> productDetailsList)
	{
		MysqlDataSource dataSource = getDataSource();
		
		try
		{
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO products(product_name, height, width, design_properties, product_type, "
					+ "colour, cost_to_produce) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, productDetailsList.get(0));
			preparedStatement.setString(2, productDetailsList.get(1));
			preparedStatement.setString(3, productDetailsList.get(2));
			preparedStatement.setString(4, productDetailsList.get(5));
			preparedStatement.setString(5, productDetailsList.get(6));
			preparedStatement.setString(6, productDetailsList.get(3));
			preparedStatement.setString(7, productDetailsList.get(4));
			
			preparedStatement.executeUpdate();
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT MAX(product_id) FROM products");
			resultSet.first();
			int bicycleProductID = resultSet.getInt(1);
			statement.close();
			
			preparedStatement = connection.prepareStatement("INSERT INTO bicycles VALUES(?, ?)");
			preparedStatement.setInt(1, bicycleProductID);
			preparedStatement.setString(2, productDetailsList.get(10));
			preparedStatement.executeUpdate();
			
			ArrayList<String> partsEnteredList = new ArrayList<>();

			for(int i = 11; i < productDetailsList.size(); i += 2)
			{
				String partProductID = productDetailsList.get(i);
				
				if(!partsEnteredList.contains(partProductID))
				{		
					preparedStatement = connection.prepareStatement("INSERT INTO parts_in_bicycle VALUES(?, ?, ?)");
					preparedStatement.setInt(1, bicycleProductID);
					preparedStatement.setString(2, partProductID);
					preparedStatement.setString(3, productDetailsList.get(i + 1));
					preparedStatement.executeUpdate();
					
					partsEnteredList.add(partProductID);
				}
			}
			
			preparedStatement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
	}
	
	public ArrayList<Product> getProductsNotInStock()
	{
		MysqlDataSource dataSource = getDataSource();
		ArrayList<Product> productList = new ArrayList<>();
		
		try
		{
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(
					"SELECT products.product_id, products.product_name, products.product_type "
					+ "FROM products "
					+ "RIGHT JOIN bicycles "
					+ "ON products.product_id = bicycles.product_id "
					+ "WHERE products.product_id NOT IN (SELECT product_id FROM stock_items)");
			
			while(resultSet.next())
			{
				String productName = resultSet.getString("products.product_name") + " (" + resultSet.getString("products.product_type") + " bicycle)";
				productList.add(new Product(productName, resultSet.getInt("products.product_id")));
			}
			
			resultSet = statement.executeQuery(
					"SELECT products.product_id, products.product_name, products.product_type "
					+ "FROM products "
					+ "RIGHT JOIN parts "
					+ "ON products.product_id = parts.product_id "
					+ "WHERE products.product_id NOT IN (SELECT product_id FROM stock_items)");
			
			while(resultSet.next())
			{
				String productName = resultSet.getString("products.product_name") + " (" + resultSet.getString("products.product_type") + " part)";				
				productList.add(new Product(productName, resultSet.getInt("products.product_id")));
			}
			
			statement.close();
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
		
		return productList;
	}
	
	
}