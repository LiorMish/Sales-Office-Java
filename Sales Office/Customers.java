import java.util.Vector;

public class Customers implements Comparable, Averageble{
	private int registeredBy;   
	private  int ID;
	private  int age;
	private String gender;
	private  String name;
	private int numOfTicketsts;
	private double totalPrice;

	/*get fields*/
	public int getRegisteredBy() {
		return registeredBy;
	}

	public int getID() {
		return ID;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public int getNumOfTicketsts() {
		return numOfTicketsts;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Customers(String ID, String name, String age, String gender, String registeredBy) { //customers constructor ,create customer object with the specific fields
		this.ID = Integer.parseInt(ID);
		this.age = Integer.parseInt(age);
		this.name = name;
		this.registeredBy=Integer.parseInt(registeredBy);

		try{
			this.gender=gender;
			if (gender.length()!=1|| (gender.charAt(0)!='m' && gender.charAt(0)!='f')) //checks if the gender field is m or f, if not throw exception.
				throw new WrongInputException(" coustomer ID : "+ID+"	gender's is wrong. change it to f or m. The gender is : "+" "+gender);
		}
		catch (WrongInputException w) {
			System.err.println("customer ID: "+ ID + "   gender is now default 'f' ");
			this.gender = "f";

		}
		this.numOfTicketsts=0;
		this.totalPrice=0;
	}

	public void sumNumOfTicketsAndPrice(Vector<Orders> orders) {// calculate the total price that customer pay and total number of tickets he ordered.
		for(int s=0;s<orders.size();s++) {
			if(orders.get(s).getCustomerID()==this.ID) {	
				this.numOfTicketsts+=orders.get(s).getNumberOfTickets();
				this.totalPrice+=orders.get(s).getOrderPrice();
			}
		}

	}
	public int compareTo( Object other ){//Implementation of "compareTo" function  of comparable interface by number of tickets criteria.

		if( this.numOfTicketsts>((Customers)other).numOfTicketsts)
			return 1;
		if( this.numOfTicketsts<((Customers)other).numOfTicketsts)
			return -1;
		else
			return 0;
	}

	public double getValue() {//Implementation of "getvalue" function  of averageble interface,return the value you want to do average to him-total price .
		return this.totalPrice;
	}
}