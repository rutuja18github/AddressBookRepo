package com.address.book;

import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dictionary {
	private static final Logger logger = LogManager.getLogger(Dictionary.class);
	Scanner scanner = new Scanner(System.in);
	HashMap<String, AddressBook> DictionaryMap = new HashMap<String, AddressBook>();
	//method to create dictionary which stores name of address book
	public void creatDictionary() {
		logger.info("Enter how many address book have to create");
		int n=scanner.nextInt();
		for (int i = 1; i <= n; i++) {
			AddressBook addressObj= new AddressBook();
			addressObj.showMainMenu();
			DictionaryMap.put("Address Book_"+i ,addressObj);
		    }
		logger.info("map"+DictionaryMap);
	}
}
