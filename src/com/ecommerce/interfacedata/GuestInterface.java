package com.ecommerce.interfacedata;

import java.util.List;
import java.util.Map;

public interface GuestInterface {
	void GuestViewProductItem();
	List<Map<String, String>> getNotPurchasedItems() throws ClassNotFoundException;
}
