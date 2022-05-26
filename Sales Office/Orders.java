import java.util.Vector;

abstract class Orders implements Comparable,Averageble {
	private int eventID;
	private int customerID;
	private int numberOfTickets;
	private double orderPrice;

	/*get fields*/
	public int getEventID() {
		return eventID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public Orders(String eventID, String customerID, String numberOfTickets) {// orders constructor,create new orders object with the specific fields.
		this.eventID=Integer.parseInt(eventID);
		this.customerID=Integer.parseInt(customerID);
		this.numberOfTickets=Integer.parseInt(numberOfTickets);
		this.orderPrice=0;
	}
	public int compareTo( Object other ){//Implementation of "compareTo" function  of comparable interface by number of tickets criterion.

		if( this.numberOfTickets>((Orders)other).numberOfTickets)
			return 1;
		if( this.numberOfTickets<((Orders)other).numberOfTickets)
			return -1;
		return 0;
	}


	public double getValue() {//Implementation of "getvalue" function  of averageble interface,return the value you want to do average to him-total price .
		return this.orderPrice;
	}

	public void CalculateOrderPrice(Vector <Events> events) {// calculate the total price of the order.
		for (int i=0; i<events.size(); i++) {
			if (this.getEventID()==events.get(i).getID() )
				orderPrice=this.numberOfTickets*events.get(i).getPricePerTicket();	
		}
	}
}