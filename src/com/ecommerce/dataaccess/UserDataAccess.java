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
import com.ecommerce.interfacedata.UserInterface;


public  class UserDataAccess implements UserInterface {
Connection con;
Scanner sc;
ConnectionDatabase obj=new ConnectionDatabase();


//method to Register User
public String UserRegisteration()
{
	try {
		con=obj.getConnection();
		
		sc=new Scanner(System.in);
		System.out.println("Enter a First Name");
		String fname=sc.next();
		System.out.println("Enter a Last Name");
		String lname=sc.next();
		System.out.println("Enter a User Name");
		String username=sc.next();
		System.out.println("Enter a Password ");
		String password=sc.next();
		System.out.println("Enter a City ");
		String city=sc.next();
		System.out.println("Enter a Mail ");
		String mail=sc.next();
		System.out.println("Enter a Mobile Number");
		String mobileno=sc.next();
		
		String query="Insert into user(firstname,lastname,username,password,city,maiIId,mobileNumber)values(?,?,?,?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, username);
				ps.setString(4, password);
				ps.setString(5, city);
				ps.setString(6, mail);
				ps.setString(7, mobileno);
				ps.executeUpdate();
				System.out.println("Registeration Successfull...");
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
	return " Registered Successfully";
}

//method for user login
public String UserLogin() {
	try {
		sc=new Scanner(System.in);
		con=obj.getConnection();
		System.out.println("Connection created Successfully");
		System.out.println("Enter a User Name");
		String username=sc.next();
		System.out.println("Enter a Password ");
		String password=sc.next();
		
		String query="Select username,password,userId from user ";
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			if(rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
				System.out.println("Your user Id is:"+rs.getInt(3));
				break;
			}
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
return " Login Successfully";
}
//public List<Products> getProductList() {
	//In this code, the ORDER BY ProductName ASC clause sorts the products by their names in ascending order.
	public List<Map<String, String>> getSortedProductList() throws ClassNotFoundException {
	    Connection con = null;
	    List<Map<String, String>> productList = new ArrayList<>();

	    try {
	    	sc=new Scanner(System.in);
	        con = new ConnectionDatabase().getConnection();

	        String query = "SELECT productId, ProductName, ProductDesc, Productprice, ProductQuantity FROM product ORDER BY ProductName ASC";
	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Map<String, String> product = new HashMap<>();
	            product.put("Product ID", String.valueOf(rs.getInt("productId")));
	            product.put("Product Name", rs.getString("ProductName"));
	            product.put("Product Description", rs.getString("ProductDesc"));
	            product.put("Price", String.valueOf(rs.getInt("Productprice")));
	            product.put("Available Quantity", String.valueOf(rs.getInt("ProductQuantity")));
	            productList.add(product);
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

	    return productList;
	}


//public void AddtoCart




@Override

public void BuyProduct() {
	// TODO Auto-generated method stub
	try {
		
		con=obj.getConnection();
		sc=new Scanner(System.in);
		
		System.out.println("Enter User Id:");
		int userId=sc.nextInt();
		System.out.println("Enter Product Id");
		int ProductId =sc.nextInt();
		System.out.println("Enter Product Quantity");
		int ProductQuantity=sc.nextInt();
		
		System.out.println("Connection created");
		// Retrieve the product price from the Product table
        String retrieveProductPriceQuery = "SELECT ProductPrice FROM product WHERE ProductId = ?";
        PreparedStatement retrieveProductPriceStatement = con.prepareStatement(retrieveProductPriceQuery);
        retrieveProductPriceStatement.setInt(1, ProductId);
        ResultSet productPriceResult = retrieveProductPriceStatement.executeQuery();

        int ProductPrice=0; // Initialize the product price to a default value

        if (productPriceResult.next()) {
            ProductPrice = productPriceResult.getInt("ProductPrice");
        }
		
		
String query="INSERT INTO `orderproduct` (userId, ProductQuantity, ProductId,ProductPrice) VALUES (?, ?, ?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, userId);
		ps.setInt(2, ProductQuantity);
		ps.setInt(3,ProductId);
		ps.setInt(4,ProductPrice);
		
		ps.executeUpdate();
		System.out.println("Item has been added to cart");
		System.out.println("Do you want view cart (yes/No)");
		String input=sc.next();
		if(input.equalsIgnoreCase("yes")) {
			System.out.println("Cart");
			displayCart();
		}
	}catch(Exception e)
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
public void displayCart() {
	// TODO Auto-generated method stub
	try {
		sc=new Scanner(System.in);
		con=obj.getConnection();
		String query="select * from orderproduct";
		PreparedStatement ps1=con.prepareStatement(query);
		ResultSet rs1=ps1.executeQuery();
		while(rs1.next()) {
			System.out.println("Product Id:"+rs1.getInt(4));
			System.out.println("Product Quantity:"+rs1.getInt(3));
			System.out.println("Product price:"+rs1.getInt(5));
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
public boolean purchaseItemsFromCart()throws SQLException, ClassNotFoundException {
    Connection con = null;
    boolean purchaseSuccessful = false;
    
    
    try {
    	sc=new Scanner(System.in);
    	System.out.print("Enter your userId: ");
        int userId = sc.nextInt();
        con = obj.getConnection();
        con.setAutoCommit(false);  // Disable auto-commit for the transaction

        // Retrieve cart items for the given user
        String retrieveCartItemsQuery = "SELECT ProductId, ProductQuantity,ProductPrice FROM orderproduct WHERE userId = ?";
        PreparedStatement retrieveCartItemsStatement = con.prepareStatement(retrieveCartItemsQuery);
        retrieveCartItemsStatement.setInt(1, userId);
        ResultSet cartItemsResult = retrieveCartItemsStatement.executeQuery();

        while (cartItemsResult.next()) {
            int ProductId = cartItemsResult.getInt("ProductId");
            int quantityToPurchase = cartItemsResult.getInt("ProductQuantity");
            int quantityPrice = cartItemsResult.getInt("ProductPrice");
            // Check if there is enough quantity in the product database
            String checkQuantityQuery = "SELECT ProductQuantity FROM product WHERE ProductId = ?";
            PreparedStatement checkQuantityStatement = con.prepareStatement(checkQuantityQuery);
            checkQuantityStatement.setInt(1, ProductId);
            ResultSet quantityResult = checkQuantityStatement.executeQuery();

            if (quantityResult.next()) {
                int availableQuantity = quantityResult.getInt("ProductQuantity");

                if (availableQuantity >= quantityToPurchase) {
                    // Update the product quantity after purchase
                    String updateQuantityQuery = "UPDATE product SET ProductQuantity = ? WHERE ProductId = ?";
                    PreparedStatement updateQuantityStatement = con.prepareStatement(updateQuantityQuery);
                    updateQuantityStatement.setInt(1, availableQuantity - quantityToPurchase);
                    updateQuantityStatement.setInt(2, ProductId);
                    updateQuantityStatement.executeUpdate();
                } else {
                    // Rollback the transaction and return false as there isn't enough quantity
                    con.rollback();
                    return false;
                }
            } else {
                // Product not found, rollback the transaction
                con.rollback();
                return false;
            }
        }

        // If everything is successful, commit the transaction
        con.commit();
        purchaseSuccessful = true;
    } catch (SQLException e) {
        if (con != null) {
            con.rollback();
        }
        e.printStackTrace();
    } finally {
        if (con != null) {
            con.setAutoCommit(true);  // Restore auto-commit to its default state
            con.close();
        }
    }
System.out.println("Purchased Successfully");
    return purchaseSuccessful;
}

@Override
public void ViewProductItem() {
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

}



