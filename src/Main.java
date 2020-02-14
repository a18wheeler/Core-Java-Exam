import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		Logic log = new Logic();
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		boolean go = true;
		
		// if 1, admin, 2 customer
		int logChoice = log.login();
		if(logChoice == 1) {
			
			while(go) {
				log.adminMenu();
				int choice = Integer.parseInt(br.readLine());
				switch(choice) {
					case 1:
						log.viewOrders();
						break;
					case 2:
						log.approveOrders();
						break;
					case 3:
						log.rejectOrders();
						break;
					case 4:
						log.updateItems();
						break;
					case 5:
						go = false;
						break;
				}
			}
		} else if(logChoice == 2) {
			while(go) {
				log.customerMenu();
				int choice = Integer.parseInt(br.readLine());
				switch(choice) {
					case 1:
						log.viewCatalog();
						break;
					case 2:
						log.placeOrder();
						break;
					case 3:
						log.viewYourOrders();
						break;
					case 4:
						go = false;
						break;
				}
			}
		} 
	}

}
