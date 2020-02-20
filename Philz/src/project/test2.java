package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String username, password;
		String employeeUsername = "";
		String employeePassword = "";
		try {
			Scanner x = new Scanner(new File("employeelogin.txt"));

			employeeUsername = x.nextLine();
			employeePassword = x.nextLine();

			x.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file.");
		}
		
		boolean login = false;
		System.out.print("Please enter the username:");
		username = input.nextLine();
		System.out.print("Please enter the password:");
		password = input.nextLine();
			
		while (login != true) {
			if (username.equals(employeeUsername) && password.equals(employeePassword))
				login = true;
			else {
				System.out.println("Incorrect login.");
				System.out.print("Please enter the username:");
				username = input.nextLine();
				System.out.print("Please enter the password:");
				password = input.nextLine();
			}
		}
		input.close();
	}

}
