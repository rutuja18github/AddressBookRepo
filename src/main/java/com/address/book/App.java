package com.address.book;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Welcome to Address Book Program");
		Scanner scanner = new Scanner(System.in);
		logger.info("what you want  to create Address Book or dictionary");
		logger.info("1.Enter 1 to Create address book only ");
		logger.info("2.Enter 2 to Create dictionary of address book");
		String option;
		option = scanner.nextLine();
		switch (option) {
		case "1":
			AddressBook book = new AddressBook();
			book.menuOfAddressBook(book);
			break;
		case "2":
			Dictionary dictObj = new Dictionary();
			dictObj.menuOfDictionary();
			break;
		}
		scanner.close();
	}
}