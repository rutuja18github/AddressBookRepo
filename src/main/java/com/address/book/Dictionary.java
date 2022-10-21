package com.address.book;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dictionary {
	private static final Logger logger = LogManager.getLogger(Dictionary.class);
	Scanner scanner = new Scanner(System.in);
	HashMap<String, AddressBook> dictionaryMap = new HashMap<String, AddressBook>();

	public void menuOfDictionary() {
		logger.info("Options for dictionay");
		logger.info("1. To create dictionay");
		logger.info("2. Display all Address books from dictionary");
		logger.info("3. Delete Address books from dictionary");
		logger.info("4. Want to access particular address book");
		logger.info("5. Search Person in city or state");
		logger.info("6. Close program");
		logger.info("Enter number 1 between 6 select option");

		String choice;
		choice = scanner.nextLine();
		switch (choice) {
		case "1":
			creatDictionary();
			break;
		case "2":
			logger.info(dictionaryMap);
			System.out.println();
			menuOfDictionary();
			break;
		case "3":
			deleteDictionary();
			break;
		case "4":
			accessAddressBook();
			break;
		case "5":
			System.out.print("Enter city or state for you want to search person  : ");
			String location = scanner.nextLine();
			searchPersonInCityOrState(location);
			break;
		case "6":
			System.exit(0);
			break;
		}
	}

	public void creatDictionary() {
		logger.info("Enter name of address book");
		String name = scanner.nextLine();
		AddressBook addressObj = new AddressBook();
		addressObj.menuOfAddressBook(addressObj);
		dictionaryMap.put(name, addressObj);
		logger.info("Want to go to main menu of contact(address book) yes or no?");
		String option = scanner.nextLine();
		while (option.equalsIgnoreCase("yes")) {
		menuOfDictionary();
		}
	}

	public void deleteDictionary() {
		System.out.print("Enter Name of Address Book Wants to Delete : ");
		String addressBookName = scanner.nextLine();
		int matches = 0;
		Iterator<String> it = dictionaryMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.equalsIgnoreCase(addressBookName)) {
				dictionaryMap.remove(key);
				matches++;
			}
		}
		if (matches <= 0) {
			System.out.println("There is no person with this name");
		} 
		logger.info("Want to go to main menu of contact(address book) yes or no?");
		String option = scanner.nextLine();
		while (option.equalsIgnoreCase("yes")) {
		menuOfDictionary();
		}
	}

	public void accessAddressBook() {
		logger.info("Enter address book name which you want to access  :");
		String addressBookName = scanner.nextLine();
		AddressBook address = dictionaryMap.get(addressBookName);
		if (address == null) {
			logger.info("Address book does not exist");
		} else {

			logger.info("accress Address Book of name " + addressBookName);
			address.menuOfAddressBook(address);
		}
		logger.info("Want to go to main menu of contact(address book) yes or no?");
		String option = scanner.nextLine();
		while (option.equalsIgnoreCase("yes")) {
		menuOfDictionary();
		}
	}
    
	//search contact for given location across address books present in dictionary.
	public void searchPersonInCityOrState(String location) {
		dictionaryMap.entrySet().stream().forEach(entry -> {
			AddressBook address = entry.getValue();
			address.getPersonByStateOrCity(location);
		});
		logger.info("Want to go to main menu of contact(address book) yes or no?");
		String option = scanner.nextLine();
		while (option.equalsIgnoreCase("yes")) {
		menuOfDictionary();
		}
	}
}
