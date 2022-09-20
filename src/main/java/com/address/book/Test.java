package com.address.book;

import java.util.HashMap;
import java.util.Map;

public class Test {
	Map<String, AddressBook> map = new HashMap<String, AddressBook>();
	void show() {
	for (int i = 0; i < 2; i++) {
		AddressBook addressObj= new AddressBook();
		addressObj.showMainMenu();
	    map.put("Address Book"+i ,addressObj);
	    }
	System.out.println("dictionary"+map);
	}
	
}
