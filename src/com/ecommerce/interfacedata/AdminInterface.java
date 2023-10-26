package com.ecommerce.interfacedata;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AdminInterface {
	String insertproduct();
	int calculateBill();
	//List<Map<String, String>>checkAllPurchaseHistory() throws ClassNotFoundException;
	//List<String> checkRegisteredUser() throws ClassNotFoundException;
}
