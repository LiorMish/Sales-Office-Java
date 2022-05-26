public class OnlineOrders extends Orders{
	private String URL;

	public String getURL() {//get the website URL
		return URL;
	}

	public OnlineOrders (String eventID, String customerID, String numberOfTickets, String URL) {//online order constructor,create new onlineorder object with the specific fields.
		super(eventID, customerID, numberOfTickets);
		this.URL=URL;
	}
}