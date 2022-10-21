package com.address.book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBook {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Contact> contactList = new ArrayList<>();
	private HashMap<String, ArrayList<Contact>> directoryOfCity = new HashMap<>();
	private HashMap<String, ArrayList<Contact>> directoryOfState = new HashMap<>();
	private static final Logger logger = LogManager.getLogger(AddressBook.class);

	// select from menu which operation is to be perform
	public void menuOfAddressBook(AddressBook address) {
		logger.info("Options for Address Book");
		logger.info("1. TO create single contact person");
		logger.info("2. To edit Contact person");
		logger.info("3. To Delete Contact person");
		logger.info("4. Display all contact person");
		logger.info("5. TO Add Multiple Contact");
		logger.info("6. Close program");
		logger.info("7. View and Count Person by city");
		logger.info("8. View and Count Person by state");
		logger.info("9. Sort Person by name,city,state and zip");
		logger.info("Enter number between 1 to 8 select option");
		String option;
		String choice;
		choice = scanner.nextLine();
		switch (choice) {
		case "1":
			address.creatContact();
			logger.info("Want to go to main menu of contact(address book) yes or no?");
			option = scanner.nextLine();
			while (option.equalsIgnoreCase("yes")) {
				menuOfAddressBook(address);
			}
			break;
		case "2":
			address.updateContact();
			logger.info("Want to go to main menu of contact(address book) yes or no?");
			option = scanner.nextLine();
			while (option.equalsIgnoreCase("yes")) {
				menuOfAddressBook(address);
			}
			break;
		case "3":
			address.deleteContact();
			logger.info("Want to go to main menu of contact(address book) yes or no?");
			option = scanner.nextLine();
			while (option.equalsIgnoreCase("yes")) {
				menuOfAddressBook(address);
			}
			break;
		case "4":
			logger.info(address.contactList);
			System.out.println();
			logger.info("Want to go to main menu of contact(address book) yes or no?");
			option = scanner.nextLine();
			while (option.equalsIgnoreCase("yes")) {
				menuOfAddressBook(address);
			}
			break;
		case "5":
			address.createMultipleContact();
			logger.info("Want to go to main menu of contact(address book) yes or no?");
			option = scanner.nextLine();
			while (option.equalsIgnoreCase("yes")) {
				menuOfAddressBook(address);
			}
			break;
		case "6":
			System.exit(0);
			break;
		case "7":
			System.out.print("Enter city for which you want to view person  : ");
			String city = scanner.nextLine();
			viewAndCountPersonsByCityOrStateDictionary(city, "city");
			break;
		case "8":
			System.out.print("Enter state for which you want to view person  : ");
			String state = scanner.nextLine();
			viewAndCountPersonsByCityOrStateDictionary(state, "state");
			break;
		case "9":
			sortContact();
			break;
		}
	}

	// method to create single contact person by taking information from user
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
		String zip = scanner.nextLine();
		Contact con = new Contact(firstName, lastName, address, city, state, zip, phone);
		// check that contact already exist or not if not then add to contact list
		if (!checkDuplicate(firstName)) {
			contactList.add(con);
			if (directoryOfCity.containsKey(city)) {
				directoryOfCity.entrySet().stream().forEach(entry -> {
					if (entry.getKey().equalsIgnoreCase(city)) {
						entry.getValue().add(con);
					}
				});
			} else {
				ArrayList<Contact> cityPersonList = new ArrayList<>();
				cityPersonList.add(con);
				directoryOfCity.put(city, cityPersonList);
			}

			if (directoryOfState.containsKey(state)) {
				directoryOfState.entrySet().stream().forEach(entry -> {
					if (entry.getKey().equalsIgnoreCase(state)) {
						entry.getValue().add(con);
					}
				});
			} else {
				ArrayList<Contact> statePersonList = new ArrayList<>();
				statePersonList.add(con);
				directoryOfState.put(state, statePersonList);
			}
		}

	}

	// method to create multiple contact
	public void createMultipleContact() {
		String option = "yes";
		while (option.equalsIgnoreCase("yes")) {
			creatContact();
			logger.info("Add More contact person yes or no?");
			option = scanner.nextLine();
		}
	}

	// method to check there is no duplicate entry of same person
	public boolean checkDuplicate(String firstName) {
		boolean alreadyExist = contactList.stream()
				.anyMatch(contactValue -> contactValue.getFirstName().equals(firstName));
		return alreadyExist;
	}

	// method to update existing contact
	public void updateContact() {
		logger.info("Enter the First Name of the contact that you want to replace");
		String replacedFirstName = scanner.nextLine();
		logger.info("Enter the First Last of the contact that you want to replace");
		String replacedLastName = scanner.nextLine();
		int matches = 0;
		for (Contact contact : contactList) {
			if (contact.getFirstName().equalsIgnoreCase(replacedFirstName)
					&& contact.getLastName().equalsIgnoreCase(replacedLastName)) {
				int index = findContact(contact);
				System.out.println(contact);
				if (index >= 0) {
					// select from option what we have to update
					logger.info("What you want to change: ");
					logger.info("1. Edit Only Name");
					logger.info("2. Edit Only Phone Only");
					logger.info("3. Edit Only Address ");
					logger.info("4. Edit all details");
					String newName;
					String newLastName;
					String newPhone;
					String address;
					String city;
					String state;
					String zip;
					String option = scanner.nextLine();
					switch (option) {
					case "1":
						logger.info("Enter name: ");
						newName = scanner.nextLine();
						logger.info("Enter surname: ");
						newLastName = scanner.nextLine();
						contactList.set(index, new Contact(newName, newLastName, contact.getAddress(),
								contact.getCity(), contact.getState(), contact.getZip(), contact.getPhone()));
						break;
					case "2":
						logger.info("Enter phone number: ");
						newPhone = scanner.nextLine();
						contactList.set(index,
								new Contact(contact.getFirstName(), contact.getLastName(), contact.getAddress(),
										contact.getCity(), contact.getState(), contact.getZip(), newPhone));
						break;
					case "3":
						logger.info("Enter Address: ");
						address = scanner.nextLine();
						logger.info("Enter City: ");
						city = scanner.nextLine();
						logger.info("Enter State: ");
						state = scanner.nextLine();
						logger.info("Enter Zip Number: ");
						zip = scanner.nextLine();
						contactList.set(index, new Contact(contact.getFirstName(), contact.getLastName(), address, city,
								state, zip, contact.getPhone()));
						break;

					case "4":
						logger.info("Enter name: ");
						newName = scanner.nextLine();
						logger.info("Enter surname: ");
						newLastName = scanner.nextLine();
						logger.info("Enter phone number: ");
						newPhone = scanner.nextLine();
						logger.info("Enter Address: ");
						address = scanner.nextLine();
						logger.info("Enter City: ");
						city = scanner.nextLine();
						logger.info("Enter State: ");
						state = scanner.nextLine();
						logger.info("Enter Zip Number: ");
						zip = scanner.nextLine();
						contactList.set(index, new Contact(newName, newLastName, address, city, state, zip, newPhone));
						break;
					default:
						System.out.print("Enter numer from 1 to 5");
						updateContact();
						break;
					}

					matches++;
				}
			}
		}
		if (matches <= 0) {
			System.out.println("No such contact found to update");
		}
	}

	// method to get index of particular record
	public int findContact(Contact contact) {
		return contactList.indexOf(contact);
	}

	// method to delete contact person first search person using name then delete it
	public void deleteContact() {
		logger.info("Enter First name of contact wants to delete : ");
		String firstNameToFind = scanner.nextLine();
		logger.info("Enter Last name of contact wants to delete : ");
		String LastNameToFind = scanner.nextLine();
		int matches = 0;
		for (Contact contact : contactList) {
			if (contact.getFirstName().equalsIgnoreCase(firstNameToFind)
					&& contact.getLastName().equalsIgnoreCase(LastNameToFind)) {
				int index = findContact(contact);
				contactList.remove(index);
				System.out.println(contact);
				matches++;
			}
		}
		if (matches <= 0) {
			System.out.println("There is no person with this name");
		}
	}

	// search contact for given location in address book.
	public void getPersonByStateOrCity(String location) {
		contactList.stream().filter(contact -> {
			return (contact.getCity().equalsIgnoreCase(location) || contact.getState().equalsIgnoreCase(location));
		}).forEach(System.out::println);
	}

	public void viewAndCountPersonsByCityOrStateDictionary(String location, String locationaType) {
		if (locationaType.equals("city")) {
			ArrayList<Contact> personByCity = directoryOfCity.get(location);
			System.out.println(personByCity);
			System.out.println("number of contact persons i.e. count by city = " + personByCity.stream().count());
		}
		if (locationaType.equals("state")) {
			ArrayList<Contact> personByState = directoryOfState.get(location);
			System.out.println(personByState);
			System.out.println("number of contact persons i.e. count by state =" + personByState.stream().count());
		}
	}

	public void sortContact() {
		System.out.println("1.Sort By name"+"2.Sort By City\n" + "3.Sort By State\n" + "4.Sort By Zip\n");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			ArrayList<Contact> sortedContact = (ArrayList<Contact>) contactList.stream()
					.sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());
			System.out.println("Sorted Contact List" + sortedContact);
			break;
		case "2":
			ArrayList<Contact> sortedContactByCity = (ArrayList<Contact>) contactList.stream()
					.sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList());
			System.out.println("Sorted Contact List by city" + sortedContactByCity);
			break;
		case "3":
			ArrayList<Contact> sortedContactByState = (ArrayList<Contact>) contactList.stream()
					.sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList());
			System.out.println("Sorted Contact List by state" + sortedContactByState);
			break;
		case "4":
			ArrayList<Contact> sortedContactByZip = (ArrayList<Contact>) contactList.stream()
					.sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList());
			System.out.println("Sorted Contact List by zip" + sortedContactByZip);
			break;
		}
	}
}
