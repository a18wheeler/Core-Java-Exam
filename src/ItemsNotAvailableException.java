
public class ItemsNotAvailableException extends Exception {
	//  Checks if quantity >= want
	String str1;
	
	ItemsNotAvailableException(String str2) {
		str1 = str2;
	}
	
	public String toString(){ 
		return ("User-defined Exception: " + str1) ;
	}
}
