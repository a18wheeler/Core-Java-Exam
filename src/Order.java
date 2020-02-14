import java.util.ArrayList;

public class Order {
	private ArrayList<Item> items;
	private String status;
	private int orderNum;
	
	public ArrayList<Item> getItems() {
		return items;
	}
	public String getStatus() {
		return status;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void addItem(Item item) {
		items.add(item);
	}
	public void setOrderNum() {
		this.orderNum = orderNum;
	}
}
