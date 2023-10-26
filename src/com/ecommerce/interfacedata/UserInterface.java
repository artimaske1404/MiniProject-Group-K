package com.ecommerce.interfacedata;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserInterface {
	String UserRegisteration();
	String UserLogin();
	void ViewProductItem();
 void BuyProduct();
 void displayCart();
	 boolean purchaseItemsFromCart() throws ClassNotFoundException, SQLException;
	 List<Map<String, String>> getSortedProductList() throws ClassNotFoundException;
}
