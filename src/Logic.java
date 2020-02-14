import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Logic {
	private InputStreamReader r = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(r);
	
	public HashMap<Integer, Item> catalog;
	public HashMap<Integer, Customer> customers;
	public ArrayList<Order> orders;
	private int currentUserId; // solely for customers
	
	// logins in all users
	public int login() throws Exception { 
		// ask for either admin or customer
		int choice;
		while(true) {
			System.out.println("Please enter the usertype\n"
					+ "1. Admin\n"
					+ "2. New Customer\n"
					+ "3. Existing Customer");
			
			try {
				choice = Integer.parseInt(br.readLine());
				break;
			} catch(Exception e) {
				System.out.println("Bad input.");
			}
		}
		// if customer
		if(choice == 1) {
			System.out.println("You have chosen to login as Admin\n");
			while(true) {
				try {
					System.out.println("Please enter username: ");
					String user = br.readLine();
					System.out.println("Please enter password: ");
					String pass = br.readLine();
				
					// verify
					if(user.equals("admin") && pass.equals("admin")) {
						System.out.println("You have successfully logged in.");
						break;
					}
					else
						System.out.println("Your credentials did not match.");
				} catch(Exception e) {
					System.out.println("Bad input");
				}
			}
			return 1;
		}
		// else
		else if (choice == 2) {
			registerCust();
		}
		else {
			try {
				// else, ask for id and pass
				System.out.println("Please enter your id: ");
				int id = Integer.parseInt(br.readLine());
				System.out.println("Please enter your password: ");
				String pass = br.readLine();
					
				if(customers.containsKey(id) && customers.get(id).getCustPassword().equals(pass))
					System.out.println("Login successful");
				currentUserId = id;
				
			} catch (Exception e) {
				System.out.println("Bad input");
			}
			return 2;
		}
		return 0;
	}
	
	////////// ADMIN METHODS ///////////////
	
	// prints the login menu for admin
	public void adminMenu() {
		System.out.println("Please enter one of the following options\n"
				+ "1. View Orders"
				+ "2. Approve Orders"
				+ "3. Reject Orders"
				+ "4. Update Item"
				+ "5. Exit");
	}
	
	// display the orders
	public void viewOrders() {
		//System.out.println("You have chosen to view orders.");
		
		// checks if database is empty
		if(orders.isEmpty()) {
			System.out.println("There are no orders.");
			return;
		}
		
		// Printing all orders
		// orders will have, order number based on pos + item info
		
		for(Order o : orders) {
			System.out.printf("Order %d:%nStatus: %s%n %-15d %-15s %-10f %-15d%n", o.getOrderNum(), o.getStatus(),
					"Item ID", "Name", "Price", "Quantity");
			for(Item i : o.getItems()) {
				System.out.printf("     %-15d %-15s %-10f %-15d...%n", i.getItemID(),  
						i.getItemName(), i.getItemPrice(), i.getItemQuantity());
			}
		}
	}
	
	// approval of orders
	public void approveOrders() {
		// display orders
		viewOrders();
		// Ask which order you wish to approve
		System.out.println("Enter the IDs of the orders you wish to approve"
				+ "Type a single ID and then hit enter."
				+ "You can type '-1' to quit.");
		int id = 0;
		while(id != -1) {
			try {
				id = Integer.parseInt(br.readLine());
				orders.get(id-1).setStatus("Approved");
			} catch(Exception e) {
				System.out.println("Bad input");
			}
		}
		System.out.println("These orders have been successfully approved.");
	}
	
	// rejection of orders
	public void rejectOrders() {
		// display orders
				viewOrders();
				// Ask which order you wish to approve
				System.out.println("Enter the IDs of the orders you wish to reject"
						+ "Type a single ID and then hit enter."
						+ "You can type '-1' to quit.");
				int id = 0;
				while(id != -1) {
					try {
						id = Integer.parseInt(br.readLine());
						orders.get(id-1).setStatus("Rejected");
					} catch(Exception e) {
						System.out.println("Bad input");
					}
				}
				System.out.println("These orders have been successfully rejected.");
	}
	
	// allows admin to change info on an item
	public void updateItems() throws Exception{
		// display update options
		System.out.println("Please choose from the following: \n"
				+ "1. Add new item"
				+ "2. Remove an item"
				+ "3. Update an item"
				+ "4. Exit");
		int choice = Integer.parseInt(br.readLine());
		switch(choice) {
			case 1:
				// ask for item info
				System.out.println("Please enter item ID");
				int id = Integer.parseInt(br.readLine());
				System.out.println("Please enter item name");
				String name = br.readLine();
				System.out.println("Please enter item price");
				double price = Double.parseDouble(br.readLine());
				System.out.println("Please enter item quantity");
				int quantity = Integer.parseInt(br.readLine());
				
				// add to catalog
				catalog.put(id, new Item(id, name, price, quantity));
				System.out.println("New item successfully added");
				break;
			case 2:
				System.out.println("Please enter an id of the item to be removed");
				int id2 = Integer.parseInt(br.readLine());
				catalog.remove(id2);
				System.out.println("Item has been removed");
				break;
			case 3:
				System.out.println("Please enter an id of the item to be removed");
				int id3 = Integer.parseInt(br.readLine());
				
				// update
				System.out.println("Please enter new item name");
				String newName = br.readLine();
				System.out.println("Please enter new item price");
				double newPrice = Double.parseDouble(br.readLine());
				System.out.println("Please enter new item quantity");
				int newQuantity = Integer.parseInt(br.readLine());
				
				catalog.put(id3, new Item(id3, newName, newPrice, newQuantity));
				System.out.println("The item was updated");
				break;
		}
	}
	
	
	//////////// CUSTOMER METHODS //////////////////
	
	// displays customer menu
	public void customerMenu() {
		System.out.println("Welcome back!\n"
				+ "Please choose one of the options below\n"
				+ "1. View Catalog"
				+ "2. Place Order"
				+ "3. View Your Orders"
				+ "4. Exit");
	}
	
	// registers a new customer
	public void registerCust() throws Exception {
		// ask for info
		System.out.println("Please enter a customer id");
		int id = Integer.parseInt(br.readLine());
		System.out.println("Please enter your name");
		String name = br.readLine();
		System.out.println("Please enter your address");
		String address = br.readLine();
		System.out.println("Your password has been automatically generated."
				+ "It is the first two and last two letters of your name.");
		String pass = name.substring(0,2) + name.substring(name.length()-2);
		
		// add to customers hash
		customers.put(id, new Customer(id, name, address, pass));
		// return to login
		login();
	}
	
	// displays the full catalogue
	public void viewCatalog() {
		// checks if database is empty
		if(orders.isEmpty()) {
			System.out.println("There are no orders.");
			return;
		}
		
				
		System.out.printf("%-15d %-15s %-10f %-15d%n",
				"Item ID", "Name", "Price", "Quantity");
		for(Map.Entry<Integer, Item> entry : catalog.entrySet()) {
			Item i = entry.getValue();
			System.out.printf("%-15d %-15s %-10f %-15d...%n", i.getItemID(),  
					i.getItemName(), i.getItemPrice(), i.getItemQuantity());
		}
	}
	
	// allows customer to place a new order
	public void placeOrder()  throws IOException {
		// display catalog
		viewCatalog();
		// add items to order based on id
		System.out.println("Type in '-1' if you want to exit.");
		System.out.println("Please type in ID of item you want");
		
		Order o = new Order();
		int id = 0;
		
		while(id != -1) {
			id = Integer.parseInt(br.readLine());
			if(catalog.containsKey(id)) {
				// ask how many
				System.out.println("Please enter a quantity: ");
				int quantity = Integer.parseInt(br.readLine());
				int prevQuan = catalog.get(id).getItemQuantity();
				if(prevQuan - quantity < 0) {
					try {
						throw new ItemsNotAvailableException("Quantity was higher than what is available.");
					} catch(ItemsNotAvailableException e) {
						System.out.println("Please enter a valid quantity");
						continue;
					}
				}
					// throw new ItemsNotAvailableException
				else {
					// add to orders
					String itemName = catalog.get(id).getItemName();
					double itemPrice = catalog.get(id).getItemPrice();
					o.addItem(new Item(id, itemName, itemPrice, quantity));
					
					// remove from catalog
					catalog.get(id).setItemQuantity(prevQuan - quantity);
				}
			} else
				System.out.println("No item with that id found.");;
		}
		// confirm done and add order to orders and myOrders
		System.out.println("Your order has been submitted.");
		orders.add(o);
		customers.get(currentUserId).addOrder(o);
	}
	
	// view your orders
	public void viewYourOrders() {
		// same as admin view orders, except it's person arraylist
		// checks if database is empty
		if(orders.isEmpty()) {
			System.out.println("There are no orders.");
			return;
		}
		
		Customer current = customers.get(currentUserId);
		ArrayList<Order> temp = current.getMyOrders();
	
		for(Order o : temp) {
			System.out.printf("Order %d:%nStatus: %s%n %-15d %-15s %-10f %-15d%n", o.getOrderNum(), o.getStatus(),
					"Item ID", "Name", "Price", "Quantity");
			for(Item i : o.getItems()) {
				System.out.printf("     %-15d %-15s %-10f %-15d...%n", i.getItemID(),  
						i.getItemName(), i.getItemPrice(), i.getItemQuantity());
			}
		}
	}
	
}
