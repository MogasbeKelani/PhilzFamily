package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String choice, buffer;
		String unSortedCustomers="";
		String username = null, password = null, firstName, lastName, streetAddress, city, state, zip;
		Customer mainCustomer = null;
		// BST fill call
		philzMenu menu = new philzMenu();
		menu.fillMenu();
		
		@SuppressWarnings("unused")
		boolean guestUser = false;
		
		Heap priorityQueue = new Heap();
		
		// Hash table fill call (Customer/Employee information)
		// Heap fill call (Order information)
		Hash<Customer> hashTable = new Hash<Customer>(10);
		try {
			Scanner x = new Scanner(new File("input2"));
			while (x.hasNextLine()) {
				Customer c = new Customer(x.nextLine(), x.nextLine(), x.nextLine(), x.nextLine(), x.nextLine(),
						x.nextLine(), x.nextLine(), x.nextLine());
				unSortedCustomers+=c.info();
				while (x.hasNextLine()) {
					buffer = x.nextLine();

					if (buffer.isEmpty())
						break;

					else {
						Order o = new Order(buffer, Integer.parseInt(x.nextLine()), Double.parseDouble(x.nextLine()));
						c.addOrder(o);
						priorityQueue.insert(o);
					}
				}
				hashTable.insert(c);
			}

			x.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the customers file.");
		}

		
		//Get employee login information, using the file employeelogin.txt
		String employeeUsername = "";
		String employeePassword = "";
		try {
			Scanner x = new Scanner(new File("employeelogin.txt"));

			employeeUsername = x.nextLine();
			employeePassword = x.nextLine();

			x.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the employees file.");
		}

		// Put employee login into local variables(employeeUsername and
		// employeePassword)
		// ***Assuming employees all share a single login
		

		System.out.println("Welcome to the Philz Coffee Storefront.\n");
		System.out.print("Press C to login as a customer or E to login as employee: ");

		choice = input.nextLine();
		

		// Allow user to keep entering a choice until input is valid
		if (!choice.equals("C") && !choice.equals("E")) {
			System.out.println("User choice was not a valid option. " + "Enter C or E only.");

			System.out.print("Enter your choice: ");
			choice = input.nextLine();

			while (!choice.equals("C") && !choice.equals("E")) {
				System.out.println("\nUser choice was not a valid option. " + "Enter C or E only.");
				System.out.print("Enter your choice: ");
				choice = input.nextLine();

				
			}
		}

		// If user chooses to login as customer, display customer options
		if (choice.equals("C")) {
			String option;

			System.out.println("\n-Press '1' to enter account details to login.");
			System.out.println("-Press '2' to create a new account.");
			System.out.println("-Press '3' to login as guest");
			System.out.print("\nOption: ");
			option = input.nextLine();

			if (!option.equals("1") && !option.equals("2") && !option.equals("3")) {
				System.out.println("User choice was not a valid option. " + "Enter 1, 2, or 3 only.");

				System.out.print("Enter your choice: ");
				option = input.nextLine();

				while (!option.equals("1") && !option.equals("2") && !option.equals("3")) {
					System.out.println("\nUser choice was not a valid option. " + "Enter 1, 2, or 3 only.");
					System.out.print("Enter your choice: ");
					option = input.nextLine();

					
				}
			}

			switch (option) { // Customer enters login details
			case "1":
				boolean login = false;

				while (login != true) {
					System.out.print("Please enter your username:");
					username = input.nextLine();
					System.out.print("Please enter your password:");
					password = input.nextLine();
					
					Customer c = new Customer(username, password, "", "", "", "", "", "");
					if (hashTable.search(c) == -1)
						System.out.println("Invalid login. Please try again.");
					else
						login = true;
				}
				Customer c = new Customer(username, password, "", "", "", "", "", "");
				mainCustomer = hashTable.get_object(c);
				
				// if verified, print message
				System.out.println("Login successful!");

				// else continue asking for account details until user inputs valid info
				break;

			case "2":
				System.out.print("Please enter your desired username: ");
				username = input.nextLine();

				System.out.print("Please enter your desired password: ");
				password = input.nextLine();

				System.out.print("Please enter your first name:");
				firstName = input.nextLine();

				System.out.print("Please enter your last name:");
				lastName = input.nextLine();

				System.out.print("Please enter your street address:");
				streetAddress = input.nextLine();

				System.out.print("Please enter your city:");
				city = input.nextLine();

				System.out.print("Please enter your two-letter state code:");
				state = input.nextLine();

				System.out.print("Please enter your zip code:");
				zip = input.nextLine(); //input.nextInt()

				// Write account details to account text file
				mainCustomer = new Customer(username, password, firstName, lastName, streetAddress, city, state, zip);
				hashTable.insert(mainCustomer);
				unSortedCustomers+=mainCustomer.info();
				break;

			case "3":
				System.out.println("Logging in as guest!");
				guestUser = true;
				break;

			}

			// Display menu options for customer
			do {
				System.out.println("Choose from the following options.\n");
				System.out.println("-Press 'S' to search for a product");
				System.out.println("-Press 'L' to list the product database");
				System.out.println("-Press 'P' to place an order");
				System.out.println("-Press 'V' to view purchases");
				System.out.println("-Press 'Q' to quit the program");

				System.out.print("\nUser choice: ");
				choice = input.nextLine();

				// Allow user to keep entering a choice until input is valid
				if (!choice.equals("S") && !choice.equals("L") && !choice.equals("P") && !choice.equals("V")
						&& !choice.equals("Q")) {
					System.out.println("\nUser choice was not a valid option. " + "Enter S, L, P, V, or Q only.");

					System.out.print("Enter your choice: ");
					choice = input.nextLine();

					while (!choice.equals("S") && !choice.equals("L") && !choice.equals("P") && !choice.equals("V")
							&& !choice.equals("Q")) {
						System.out.println("\nUser choice was not a valid option. " + "Enter S, L, P, V, or Q only.");
						System.out.print("Enter your choice: ");
						choice = input.nextLine();

					}
				}
				// Perform action based on user's choice
				switch (choice) {
				case "S":
					String temp = "";
					boolean correctName = false;
					boolean correctAroma = false;
					while (correctName != true && correctAroma != true) {
						System.out.print("Please enter the coffee name or the aromas:");
						temp = input.nextLine();
						if (menu.searchAroma(temp) == true)
							correctAroma = true;
						else if (menu.searchName(temp) == true)
							correctName = true;
						else
							System.out.println(
									"We could not find your item, please take care to check spelling and case.");
					}
					if (correctAroma)
						menu.printAroma(temp);
					if (correctName)
						menu.printName(temp);
					break;

				case "L":
					System.out.println("Press N to sort menu by name. \nPress A to sort menu by aromas.");
					System.out.print("Enter your choice: ");
					String temp2 = input.nextLine();
					while (!temp2.equals("N") && !temp2.equals("A"))
					{
						System.out.println("Invalid key. Please try again.");
						System.out.print("Enter your choice: ");
						temp2 = input.nextLine();
					}
					
					if (temp2.equals("A"))
						menu.printAromaMenu();
					if (temp2.equals("N"))
						menu.printNameMenu();
					break;

				case "P":
					String tempName;
					double tempPrice;
					String pattern = "yyyyMMdd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					
					boolean doneOrdering = false;
					
					while (doneOrdering != true)
					{
						System.out.print("Please enter the coffee name: ");
						tempName = input.nextLine();
						while (menu.searchName(tempName) == false)
						{
							System.out.println("We could not find your item, please take care to check spelling and case.");
							System.out.print("Please enter the coffee name: ");
							tempName = input.nextLine();
						}
						tempPrice = menu.getPriceP(tempName);
						
						System.out.println("Press O for overnight shipping.");
						System.out.println("Press R for rush shipping.(3 days)");
						System.out.println("Press S for standard shipping.(7 days)");
						System.out.print("Enter your choice: ");
						String temp4 = input.nextLine();
						while (!temp4.equals("O") && !temp4.equals("R") && !temp4.equals("S"))
						{
							System.out.println("Invalid key. Please try again.");
							System.out.print("Enter your choice: ");
							temp4 = input.nextLine();
						}
						int date = 0;
						if (temp4.equals("O"))
						{
							date = Integer.parseInt(simpleDateFormat.format(new Date())) + 1;
						}
						if (temp4.equals("R"))
						{
							date = Integer.parseInt(simpleDateFormat.format(new Date())) + 3;
						}
						if (temp4.equals("S"))
						{
							date = Integer.parseInt(simpleDateFormat.format(new Date())) + 7;
						}
						
						Order o = new Order(tempName, date, tempPrice);
						if (guestUser = false)
						mainCustomer.addOrder(o);
						
						priorityQueue.insert(o);
						System.out.println("Your order has been successful.");
						System.out.println("If you would like to make another order, press Y. If not, press N.");
						System.out.print("Enter your choice: ");
						temp4 = input.nextLine();
						while (!temp4.equals("Y") && !temp4.equals("N"))
						{
							System.out.println("Invalid key. Please try again.");
							System.out.print("Enter your choice: ");
							temp4 = input.nextLine();
						}
						
						if (temp4.equals("N"))
							doneOrdering = true;
					}
					
					break;

				case "V":
					System.out.println("Press U to view unshipped orders.");
					System.out.println("Press S to view shipped orders");
					System.out.print("Enter your choice: ");
					String temp5 = input.nextLine();
					while (!temp5.equals("U") && !temp5.equals("S"))
					{
						System.out.println("Invalid key. Please try again.");
						System.out.print("Enter your choice: ");
						temp5 = input.nextLine();
					}
					if (temp5.equals("U"))
						mainCustomer.printUnshippedOrders();
					if (temp5.equals("S"))
						mainCustomer.printShippedOrders();
					
					break;

				case "Q":
					System.out.print("\nExiting application. Goodbye!");
					System.exit(0);
					break;
				}

			} while (choice.equals("S") || choice.equals("L") || choice.equals("P") || choice.equals("V"));
		}

		// If user chooses to login as employee, display employee options
		if (choice.equals("E")) {

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

			// Verify login details

			// if verified, print message
			System.out.println("Login successful!");

			System.out.println("Welcome: "+username+"!");
			
			

			// Display menu options for employee
			do {
				System.out.println("Choose from the following options.\n");
				System.out.println("-Press 'S' to search for a customer by name");
				System.out.println("-Press 'D' to display unsorted customer information");
				System.out.println("-Press 'V' to view orders by priority");
				System.out.println("-Press 'O' to ship an order");
				System.out.println("-Press 'L' to list the product database");
				System.out.println("-Press 'A' to add a new product");
				System.out.println("-Press 'R' to remove a product");
				System.out.println("-Press 'Q' to quit the program");

				System.out.print("\nUser choice: ");
				choice = input.nextLine();

				// Allow user to keep entering a choice until input is valid
				if (!choice.equals("S") && !choice.equals("D") && !choice.equals("V") && !choice.equals("O")
						&& !choice.equals("L") && !choice.equals("A") && !choice.equals("R") && !choice.equals("Q")) {
					System.out.println(
							"\nUser choice was not a valid option. " + "Enter S, D, V, O, L, A, R, or Q only.");

					System.out.print("Enter your choice: ");
					choice = input.nextLine();

					while (!choice.equals("S") && !choice.equals("D") && !choice.equals("V") && !choice.equals("O")
							&& !choice.equals("L") && !choice.equals("A") && !choice.equals("R")
							&& !choice.equals("Q")) {
						System.out.println(
								"\nUser choice was not a valid option. " + "Enter S, D, V, O, L, A, R, or Q only.");
						System.out.print("Enter your choice: ");
						choice = input.nextLine();

					}
				}
				// Perform action based on user's choice
				switch (choice) {
				case "S":
					String name="", tempPass = "";
					System.out.printf("Enter the Customers username: ");
					name=input.nextLine();
					System.out.printf("Enter the Customers password: ");
					tempPass=input.nextLine();
					int n= name.hashCode()%10;
					Customer t= new Customer(name,tempPass,"","","","","","");
					if(hashTable.search(t)!=-1)
					{
						System.out.println(hashTable.get_object(t).getFirstName()+
								hashTable.get_object(t).getLastName()+" "+
								" is a Customer");
					}
					else
					{
						hashTable.printTable();;
						System.out.println("Customer name: "+name+" "
								+ "does not exist");
					}
					break;

				case "D":
					System.out.println(unSortedCustomers);
					break;

				case "V":
					System.out.println(priorityQueue.sort());
					break;

				case "O":
					int tempInt;
					System.out.print("Which order would you like to ship?");
					tempInt = Integer.parseInt(input.nextLine()) - 1;
					priorityQueue.getOrder(tempInt).setShipped();
					break;

				case "L":
					menu.printMenu();
					break;

				case "A":
					String a,b="";
					Double c;
					System.out.println("What is the name of the coffee(With first letter Capital): ");
					a=input.nextLine();
					System.out.println("What is the name of the aroma (Ex: Nutty, Sugary): ");
					b=input.nextLine();
					System.out.println("What is the price of the coffee: ");
					c=Double.parseDouble(input.nextLine());
					menu.insertItem(a, b, c);
					System.out.println("Coffee inserted!");
					break;

				case "R":
					String w,y="";
					System.out.println("What is the name of the coffee(With first letter Capital): ");
					w=input.nextLine();
					System.out.println("What is the name of the aroma (Ex: Nutty, Sugary): ");
					y=input.nextLine();
					menu.removeItem(w, y);
					break;

				case "Q":
					System.out.print("\nExiting application. Goodbye!");
					System.exit(0);
					break;
				}

			} while (choice.equals("S") || choice.equals("D") || choice.equals("V") || choice.equals("O")
					|| choice.equals("L") || choice.equals("A") || choice.equals("R") || choice.equals("Q"));
		}

		input.close();
		
		    
		    
		    
		    
		

	}

}