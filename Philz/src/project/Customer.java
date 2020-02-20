package project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer implements Comparable<Customer> {

	private String first_name;
	private String last_name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String username;
	private String password;

	private List<Order> orders;

	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
		this.orders = new List<Order>();
	}

	public Customer(String username, String password, String first_name, String last_name, String address, String city,
			String state, String zip) {
		this.orders = new List<Order>();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	// ** GETTERS AND SETTERS **
	public String getUsername() {
		return username;
	}

	private String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void addOrder(int date, String coffeeName, double cost) {
		orders.addLast(new Order(coffeeName, date, cost));
	}

	public void addOrder(Order order) {
		orders.addLast(order);
	}

	public void removeOrder(Order order) {
		orders.pointIterator();
		for (int i = 0; i < orders.getLength(); i++) {
			if (orders.getIterator() == order)
				orders.removeIterator();
		}
	}

	@Override
	public int compareTo(Customer o) {
		int thisHash = (username + password).hashCode();
		int toCompareHash = (o.getUsername()+o.getPassword()).hashCode();
		if (thisHash == toCompareHash)
			return 0;
		else
			return thisHash > toCompareHash ? 1 : -1;
	}

	public void printUnshippedOrders() {
		// An order is unshipped if there is a 3 or more day difference between current
		// date
		// and shipping date
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		int currentDate = Integer.parseInt(simpleDateFormat.format(new Date()));

		if (orders.isEmpty()) {
			System.out.println("You have no pending orders!");
		}
		int orderNumber = 1;
		orders.pointIterator();
		for (int i = 0; i < orders.getLength(); i++) {
			if (orders.getIterator().getShipped() == true) {
				orders.advanceIterator();
			}

			else {
				System.out.println();
				System.out.println("Order: " + (orderNumber++));
				System.out.println("Coffee: " + orders.getIterator().getCoffeeName());
				System.out.println("Price: " + orders.getIterator().getCost());
				System.out.println("Shipping date: " + orders.getIterator().getShippingDate());
				System.out.println();

				orders.advanceIterator();
			}
		}
	}
	public String info()
	{
		return this.getFirstName()+"\n"+this.getLastName()+"\n"+this.getCity()+"\n";
	}
	
	public String infoV2()
	{
		return this.getUsername()+"\n"+ this.getPassword()+"\n"+this.getFirstName()+"\n"+this.getLastName()+"\n"+this.getAddress()+"\n"+this.getCity()+"\n"+
				this.getState()+"\n"+this.getZip()+"\n";
	}
	public void printShippedOrders() {
		// An order is shipped if there is a 2 or less day difference between current
		// date and shipping date
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		int currentDate = Integer.parseInt(simpleDateFormat.format(new Date()));

		if (orders.isEmpty()) {
			System.out.println("You have no pending orders!");
		}
		int orderNumber = 1;
		orders.pointIterator();
		for (int i = 0; i < orders.getLength(); i++) {
			if ((orders.getIterator().getShipped()) == false) {
				orders.advanceIterator();
			}

			else {
				System.out.println();
				System.out.println("Order: " + (orderNumber++));
				System.out.println("Coffee: " + orders.getIterator().getCoffeeName());
				System.out.println("Price: " + orders.getIterator().getCost());
				System.out.println("Shipping date: " + orders.getIterator().getShippingDate());
				System.out.println();

				orders.advanceIterator();
			}
		}
	}

	@Override
	public int hashCode() {
		return (username + password).hashCode();
	}

}