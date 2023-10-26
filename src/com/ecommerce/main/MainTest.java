package com.ecommerce.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.ecommerce.dataaccess.AdminDataAccess;
import com.ecommerce.dataaccess.GuestDataAccess;
import com.ecommerce.dataaccess.UserDataAccess;
//import com.ecommerce.product.ProductList;

public class MainTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 AdminDataAccess admin = new AdminDataAccess();
		 UserDataAccess user = new UserDataAccess();
		 GuestDataAccess guest=new GuestDataAccess();
		Scanner scanner;
	scanner = new Scanner(System.in);
		// ProductList productList = new ProductList();
//	       
    int choice;
//	        while(true)
	        {
	        	  
	            System.out.println("E-Commerce Application Menu");
	            System.out.println("1. User Registration");
	            System.out.println("2. User Login");
	            System.out.println("3. User View Product Items (Sorted Order)");
	            System.out.println("4. Buy Product");
	            System.out.println("5. View Cart");
	            System.out.println("6. Purchase Item");
	            System.out.println("7. Add Product Item");
	            System.out.println("8. Calculate Bill");
	            System.out.println("9. Display Amount to End User");
	            System.out.println("10. Check Quantity");
	            System.out.println("11. Check Registered User");
	            System.out.println("12. Check User Purchase History");
	            System.out.println("13.View product to guest");
	            System.out.println("14.NotPurchase item List");
	            System.out.println("15. Exit");
	            System.out.print("Enter your choice: ");
	            
	         choice = scanner.nextInt();

	          switch (choice) {
	                case 1:
	                    // User Registration
	                    user.UserRegisteration();
	                    break;

	                case 2:
	                    // User Login
	                    user.UserLogin();
	                    break;

	                case 3:
	                    // User View Product Items
	                    user.getSortedProductList();
	                    break;

	                case 4:
	                    // Buy Product
	                  user.BuyProduct();
	                    break;

	                case 5:
	                    // View Cart
	                    user.displayCart();
	                    break;

	                case 6:
	                    // Purchase Item
	                    user. purchaseItemsFromCart();
	                    break;

	                case 7:
	                    // Add Product Item (Admin)
	                    admin.insertproduct();
	                    break;

	                case 8:
	                    // Calculate Bill (Admin)
	                    admin.calculateBill();
	                    break;

	                case 9:
	                    // Display Amount to End User (Admin)
	                    admin.displayAmountToEndUser();
	                    break;

	                case 10:
	                    // Check Quantity (Admin)
	                    admin.checkQuantity();
	                    break;

	                case 11:
	                    // Check Registered User (Admin)
	                admin.checkRegisteredUser();
	                    break;

	                case 12:
	                    // Check User Purchase History (Admin)
	                    admin.checkAllPurchaseHistory();
	                    break;

	                case 13:
	                	guest.GuestViewProductItem();
	                break;

	                case 14:
	                   guest.getNotPurchasedItems();
	                    break;
	                case 15:
	                    System.out.println("Exiting the application.");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    
	                   scanner.close();
	            }
	        }

	     
	    }
	}