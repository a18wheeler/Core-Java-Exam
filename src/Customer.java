import java.util.ArrayList;

public class Customer {
	private int custId;
	private String custName;
	private String custAddress;
	private String custPassword;
	private ArrayList<Order> myOrders;
	
	public Customer(int custId, String custName, String custAddress, String custPassword) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custAddress = custAddress;
		this.custPassword = custPassword;
	}
	
	public int getCustId() {
		return custId;
	}
	public String getCustName() {
		return custName;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public ArrayList<Order> getMyOrders() {
		return myOrders;
	}
	
	
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	
	
	public void addOrder(Order o) {
		myOrders.add(o);
	}
}
