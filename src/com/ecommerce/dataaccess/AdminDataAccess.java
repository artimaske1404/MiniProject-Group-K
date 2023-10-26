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
import com.ecommerce.interfacedata.AdminInterface;

import com.mysql.cj.protocol.Resultset;

import java.util.Scanner;
public class AdminDataAccess implements AdminInterface{
Connection con; 



int quantity; 
int price;

int totalAmount; 
Scanner sc;
ConnectionDatabase obj=new ConnectionDatabase();

// method implementation to add products

public String insertproduct() {
    try {
        sc = new Scanner(System.in);
        con = obj.getConnection();

        System.out.println("Enter Product name");
        String productName = sc.nextLine();
        System.out.println("Enter Product Description:");
        String productDesc = sc.nextLine();
        System.out.println("Enter Product Price:");
        int productPrice = sc.nextInt();
        System.out.println("Enter Product Quantity:");
        int productQuantity = sc.nextInt();

        String query = "INSERT INTO product(ProductName, ProductDesc, Productprice, ProductQuantity) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, productName);
        ps.setString(2, productDesc);
        ps.setInt(3, productPrice);
        ps.setInt(4, productQuantity);

        // Use executeUpdate() to execute INSERT, UPDATE, or DELETE statements
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
          System.out.println("Product Inserted Successfully");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (con != null) {
                con.close();
            }
            if (sc != null) {
                sc.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return "Product Insertion Failed";
}



//method to calculate bill
public int calculateBill() {
	try {
		sc=new Scanner(System.in);
		con=obj.getConnection();
		String query="select * from orderproduct";
		PreparedStatement ps= con.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
	
System.out.println("...Bill.....");
while(rs.next()) { 
	int productId=rs.getInt("ProductId");
int quantity=rs.getInt("ProductQuantity");
System.out.println(" Product quantity:"+ quantity);



PreparedStatement ps1= con.prepareStatement("Select * from product where ProductId = ? ");
ps1.setInt(1, productId);
ResultSet rs1= ps1.executeQuery();
while(rs1.next()) {
int price=rs1.getInt("ProductPrice");
System.out.println("Product Price:"+price);
}
}
totalAmount=quantity * price;
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
	return totalAmount;
	}



/* public void displayAmountToEndUser() throws ClassNotFoundException {
	// TODO Auto-generated method stub
	try {
		sc=new Scanner(System.in);
		con=obj.getConnection();
	String query = "SELECT SUM(totalAmount) FROM orderproduct";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        int totalAmount = rs.getInt(1);
        System.out.println("Total Amount to End User: " + totalAmount);
    } else {
        System.out.println("No purchase history found.");
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
}
*/
public double displayAmountToEndUser() throws SQLException, ClassNotFoundException {
    double totalAmount = 0.0;
    Connection con = null;

    try {
    	sc=new Scanner(System.in);
		con=obj.getConnection();
		System.out.print("Enter User Id: ");
	    int userId= sc.nextInt();

        // Retrieve cart items and their prices for the given user
        String retrieveCartItemsQuery = "SELECT p.ProductId, o.ProductQuantity, p.ProductPrice " +
                "FROM orderproduct o " +
                "JOIN product p ON o.ProductId = p.ProductId " +
                "WHERE o.userId = ?";
        PreparedStatement retrieveCartItemsStatement = con.prepareStatement(retrieveCartItemsQuery);
        retrieveCartItemsStatement.setInt(1, userId);
        ResultSet cartItemsResult = retrieveCartItemsStatement.executeQuery();

        while (cartItemsResult.next()) {
            int quantity = cartItemsResult.getInt("ProductQuantity");
            double price = cartItemsResult.getDouble("ProductPrice");
            totalAmount += quantity * price;
        }

        // Display the total amount to the end user
        System.out.println("Total Amount: Rs." + totalAmount);

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (con != null) {
            con.close();
        }
    }

    return totalAmount;
}

public void checkQuantity()  {
	
	// TODO Auto-generated method stub
	sc=new Scanner(System.in);
	try {
		con=obj.getConnection();
	System.out.print("Enter the Product ID: ");
    int productId = sc.nextInt();

    String query = "SELECT ProductName, ProductQuantity FROM product WHERE ProductId = ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, productId);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        String productName = rs.getString("ProductName");
        int availableQuantity = rs.getInt("ProductQuantity");
        System.out.println("Product Name: " + productName);
        System.out.println("Available Quantity: " + availableQuantity);
    } else {
        System.out.println("Product with ID " + productId + " not found.");
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    try {
        if (con != null) {
            con.close();
        }
        sc.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}



//public void checkRegisteredUser() {
	// TODO Auto-generated method stub
	
public List<Map<String, String>> checkRegisteredUser() throws SQLException, ClassNotFoundException {
    Connection con = null;
    List<Map<String, String>> userList = new ArrayList<>();

    try {
        con = obj.getConnection();
        String query = "SELECT userId, firstName, lastName, username, password, city, mailId, mobileNumber FROM user";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, String> user = new HashMap<>();
            user.put("User ID", rs.getString("userId"));
            user.put("First Name", rs.getString("firstName"));
            user.put("Last Name", rs.getString("lastName"));
            user.put("Username", rs.getString("username"));
            user.put("Password", rs.getString("password"));
            user.put("City", rs.getString("city"));
            user.put("Email", rs.getString("mailId"));
            user.put("Mobile Number", rs.getString("mobileNumber"));
            userList.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (con != null) {
            con.close();
        }
    }

    return userList;
}


//public void checkUserPurchaseHistory() {
	// TODO Auto-generated method stub
	 public List<Map<String, String>> checkAllPurchaseHistory() throws ClassNotFoundException {
	        Connection con = null;
	        List<Map<String, String>> purchaseHistory = new ArrayList<>();

	        try {
	            con = new ConnectionDatabase().getConnection();

	            String query = "SELECT userId, ProductId, ProductQuantity FROM `orderproduct`";
	            PreparedStatement ps = con.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Map<String, String> purchase = new HashMap<>();
	                purchase.put("User ID", String.valueOf(rs.getInt("userId")));
	                purchase.put("Product ID", String.valueOf(rs.getInt("ProductId")));
	                purchase.put("Quantity", String.valueOf(rs.getInt("ProductQuantity")));
	                purchaseHistory.add(purchase);
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

	        return purchaseHistory;
	    }



	
	
}
