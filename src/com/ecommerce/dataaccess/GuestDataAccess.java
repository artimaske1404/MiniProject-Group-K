package com.ecommerce.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ecommerce.conenction.ConnectionDatabase;
import com.ecommerce.interfacedata.GuestInterface;

public class GuestDataAccess implements GuestInterface {
//	View product item
	
	Connection con;
	Scanner sc;
	ConnectionDatabase obj=new ConnectionDatabase();
	public void GuestViewProductItem() {
		// TODO Auto-generated method stub
		//method Implementation to view products
		
		try {
			sc=new Scanner(System.in);
			con=obj.getConnection();
			
			String query="select * from product";
				PreparedStatement ps=con.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					System.out.println("Product Id:"+rs.getInt(1));
					System.out.println("Product Name:"+rs.getString(3));
					System.out.println("Product Describtion:"+rs.getString(2));
					System.out.println("Product Available Quantity:"+rs.getInt(5));
					System.out.println("Product price:"+rs.getInt(4));
					
					System.out.println("......................................");
					
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
		}
		finally {
			try {
				con.close();
				sc.close();
			}
			catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	}
	// Not purchase item
	public List<Map<String, String>> getNotPurchasedItems() throws ClassNotFoundException {
	    Connection con = null;
	    List<Map<String, String>> notPurchasedItems = new ArrayList<>();

	    try {
	        con = new ConnectionDatabase().getConnection();

	        // Query to retrieve products that have not been purchased by any user
	        String query = "SELECT productId, ProductName, ProductDesc, Productprice, ProductQuantity FROM product " +
	            "WHERE productId NOT IN (SELECT DISTINCT productId FROM `orderproduct`)";
	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Map<String, String> product = new HashMap<>();
	            product.put("Product ID", String.valueOf(rs.getInt("productId")));
	            product.put("Product Name", rs.getString("ProductName"));
	            product.put("Product Description", rs.getString("ProductDesc"));
	            product.put("Price", String.valueOf(rs.getInt("Productprice")));
	            product.put("Available Quantity", String.valueOf(rs.getInt("ProductQuantity")));
	            notPurchasedItems.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
  System.out.println("you are not able to purchase item");
	    return notPurchasedItems;
	}
	
}
