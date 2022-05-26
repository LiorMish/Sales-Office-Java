import java.util.Vector;

public class Events implements Comparable,Averageble{
	private String name;
	private int ID;
	private int PricePerTicket;
	private int totalTickets;

	public Events(String name, String ID, String PricePerTicket) {//events constructor ,create events object with the specific fields
		this.ID=Integer.parseInt(ID);
		this.name=name;
		if (Integer.parseInt(PricePerTicket)<0) 
			throw new WrongInputException(this.name+" " +"price per ticket is negative, please change it ,the price is :"+" "+PricePerTicket); //checks if the price of the event is not negative , if not throw exception.
		this.PricePerTicket=Integer.parseInt(PricePerTicket);
		this.totalTickets=0;
	}
	public void sumTotalTickets(Vector<Orders> orders) { //calculate the total tickets the has been ordered to the event.
		for(int s=0;s<orders.size();s++) {
			if(orders.get(s).getEventID()==this.ID)	
				this.totalTickets+=orders.get(s).getNumberOfTickets();
		}

	}

	public int compareTo( Object other ){//Implementation of "compareTo" function  of comparable interface by total tickets criteria.

		if( this.totalTickets>((Events)other).totalTickets)
			return 1;
		if( this.totalTickets<((Events)other).totalTickets)
			return -1;
		else
			return 0;

	}
	
	public double getValue() {//Implementation of "getValue" function  of averageble interface,return the value you want to do average to him ,price of tickets.
		return this.PricePerTicket;
	}
	
	/*get fields*/
	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
	}
	public int getPricePerTicket() {
		return PricePerTicket;
	}
	public int getTotalTickets() {
		return totalTickets;
	}

}