package com.address.book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIOService {

	public String AddressBookFilePath = "D:\\BridgeLabz\\AddressBook\\src\\main\\java\\addressBook_file.txt";

    public void write(List<Contact> contactsList) {
        StringBuffer contactBuffer = new StringBuffer();
        contactsList.forEach(person ->{
            String contactDataString = person.toString().concat("\n");
            contactBuffer.append(contactDataString);
        });
        try {
            Files.write(Paths.get(AddressBookFilePath),contactBuffer.toString().getBytes());
        }catch (IOException x){
            x.printStackTrace();
        }
    }

    public void printData() {
        try {
            Files.lines(new File(AddressBookFilePath).toPath())
                    .forEach(System.out::println);
        }catch (IOException x){
            x.printStackTrace();
        }
    }

    public List<Contact> readData() {
        List<Contact> contactsList = new ArrayList();
        try {
            Files.lines(new File(AddressBookFilePath).toPath())
                    .map(line -> line.trim())
                    .forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactsList;
    }
}
