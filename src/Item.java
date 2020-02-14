
public class Item {
	private int itemID;
	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	
	public Item(int itemID, String itemName, double itemPrice, int itemQuantity) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
	}
	
	public int getItemID() {
		return itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
}
