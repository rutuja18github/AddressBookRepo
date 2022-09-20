package com.address.book;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBook {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Contact> contactList = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(App.class);

	public void creatContact() {

		logger.info("Enter First name: ");
		String firstName = scanner.nextLine();
		logger.info("Enter Last name: ");
		String lastName = scanner.nextLine();
		logger.info("Enter phone number: ");
		String phone = scanner.nextLine();
		logger.info("Enter Address: ");
		String address = scanner.nextLine();
		logger.info("Enter City: ");
		String city = scanner.nextLine();
		logger.info("Enter State: ");
		String state = scanner.nextLine();
		logger.info("Enter Zip Number: ");
		int zip = scanner.nextInt();
		Contact con = new Contact(firstName, lastName, address, city, state, zip, phone);
		contactList.add(con);
	}

}
