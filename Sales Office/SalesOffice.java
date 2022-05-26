import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class SalesOffice {
	//The Vectors keep all the files data
	public Vector <Events> events;
	public Vector <Employees> employees;
	public Vector <Customers> customers;
	public Vector <Orders> orders;
	//Helper Vectors 
	private Vector <Events> eventsHelper;
	private Vector <Employees> employeesHelper;
	private Vector <Customers> customersHelper;

	private double revenue;
	private double expenses;
	private double onlineProportionCounter;
	private static double sumValue;
	private static int maxObjectPlace;
	private int maxIndex;
	private int [] age;
	private boolean [] isChecked;


	private void createHelperVectors() {//create the helper vectors as a copy to the original vectors

		eventsHelper=(Vector<Events>) events.clone();
		employeesHelper=(Vector<Employees>) employees.clone();
		customersHelper=(Vector<Customers>) customers.clone();
	}

	//Sales office constructor. Create the vectors with the data from the files.
	public SalesOffice (String fileEvents, String fileEmployees, String fileCustomers , String fileTicketsSales) {
		initialVariables();//Initial the vectors and the methods variables

		//File (convert) -  String (convert) - vector
		createEvents (readString(fileEvents).split("\t"));
		createOrders (readString(fileTicketsSales).split("\t"));
		createCustomers (readString(fileCustomers).split("\t"));
		createEmployees (readString(fileEmployees).split("\t"));

		createHelperVectors();//create helper vectors, copy of the originals
		initialDataOfTheClasses();//Initial important fields of the classes

	}

	private void initialDataOfTheClasses() {//Initial important fields of the classes
		for (int i=0; i<orders.size(); i++)
			orders.get(i).CalculateOrderPrice(events);
		for (int i=0; i<employees.size(); i++) 
			employees.get(i).CalculateSalary(orders, events, customers);
		for (int i=0; i<customers.size(); i++) 
			customers.get(i).sumNumOfTicketsAndPrice(orders);
		for (int i=0; i<events.size(); i++) 
			events.get(i).sumTotalTickets(orders);


	}
	private void initialVariables() {//Initial the vectors and the methods variables
		age = new int [7];
		this.events = new Vector <Events>();
		this.employees = new Vector <Employees>();
		this.customers = new Vector <Customers>();
		this.orders = new Vector <Orders>();
	}

	private String readString(String file) {//convert file to a long String 
		String text = "";
		try {
			Scanner s = new Scanner (new File(file));
			while (s.hasNextLine()) {
				text+=s.nextLine()+"\t";
			}
		}
		catch (FileNotFoundException e) {//Throw an exception if the file is not found
			System.out.println("file "+ file + " not found");
		}
		return text;
	}

	private void createCustomers (String [] customersStr) {//create the customers inside each sale in the vector
		for (int i=5; i<customersStr.length; i+=5) {
			customers.add(new Customers (customersStr[i], customersStr[i+1], customersStr[i+2], customersStr[i+3], customersStr[i+4]));
		}
	}

	private void createEmployees (String [] employeesStr) {//create the employees inside each sale in the vector
		for (int i=5; i<employeesStr.length; i+=5) {
			if (employeesStr[i+3].isEmpty())
				employees.add (new MarketingPerson (employeesStr[i], employeesStr[i+1], employeesStr[i+2], employeesStr[i+4] ));
			else employees.add (new SalesPerson (employeesStr[i], employeesStr[i+1], employeesStr[i+2], employeesStr[i+3] ));	
		}
	}

	private void createEvents (String [] eventsStr) {//create the events inside each sale in the vector
		for (int i=3; i<eventsStr.length; i+=3)
			events.add (new Events (eventsStr[i], eventsStr[i+1], eventsStr[i+2]));
	}

	private void createOrders (String [] ordersStr) {//create the order inside each sale in the vector
		for (int i=5; i<ordersStr.length; i+=5) {
			if (ordersStr[i+2].isEmpty())
				orders.add (new OnlineOrders (ordersStr[i], ordersStr[i+1], ordersStr[i+3], ordersStr[i+4] ));
			else orders.add (new FrontalOrders (ordersStr[i], ordersStr[i+1], ordersStr[i+2], ordersStr[i+3] ));
		}
	}
	public void printAgeReport(int eventID) {//Make a report of the customers age in each event

		getAges(eventID);//get customers age array of this events 
		//print the report
		for (int i=0; i<events.size(); i++) {
			if (events.get(i).getID()==eventID) {
				System.out.println("Event name:"+events.get(i).getName());
				System.out.println("");
				System.out.println("0-18:" +" "+(age[0]*100/age[6])+"%");
				System.out.println("");
				System.out.println("18-24:" +" "+(age[1]*100/age[6])+"%");
				System.out.println("");
				System.out.println("25-35:" +" "+(age[2]*100/age[6])+"%");
				System.out.println("");
				System.out.println("36-50:" +" "+(age[3]*100/age[6])+"%");
				System.out.println("");
				System.out.println("51-70:" +" "+(age[4]*100/age[6])+"%");
				System.out.println("");
				System.out.println("71:" +" "+(age[5]*100/age[6])+"%");	
			
		break;
			}
		}
	}
	private int ageToNumber(int age) { //helper function ,casting ages to place in array.
		if(age<=18 )
			return 0;
		if(age<=24)
			return 1;
		if(age<=35)
			return 2;
		if(age<=50)
			return 3;
		if(age<=70)
			return 4;	
		else
			return 5;
	}

	private int[] getAges(int eventID) {//check all the customers age in the events 
		isChecked = new boolean [this.customers.size()] ;
		for(int i=0;i<orders.size();i++) {
			if(orders.get(i).getEventID()==eventID) {
				for (int j=0; j<customers.size(); j++) {
					if (orders.get(i).getCustomerID()==customers.get(j).getID() && !(isChecked[customers.get(j).getID()-1])) {
						// order the age of the customers in an array
						age[ageToNumber(customers.get(j).getAge())]++;
						age[6]++;
						isChecked[orders.get(i).getCustomerID()-1] = true;
					}
				}
			}
		}
		return age;//return the age array to print a report
	}

	public double getOnlineProportion() {//check the proportion between the online orders and all the orders
		for (int i=0; i<orders.size(); i++) {
			if (orders.get(i) instanceof OnlineOrders)
				onlineProportionCounter++;
		}
		return onlineProportionCounter/orders.size();
	}

	public double getBalance() {//get the company revenue and expenses, and return the balance
		this.expenses=0;
		this.revenue=0;
		return GetCompanyRevenue() - GetCompanyExpenses();
	}

	private double GetCompanyExpenses() {//get the expenses of all employees salary
		for (int i=0; i<employees.size(); i++) 
			expenses+=employees.get(i).salary;
		return expenses;
	}

	private double GetCompanyRevenue() {//get the revenue of the tickets sale
		for (int i=0; i<orders.size(); i++) {
			revenue+=orders.get(i).getOrderPrice();
		}
		return revenue;
	}

	//A method to find the max object by his compare value
	public static Comparable getMax(Vector<? extends Comparable> any) {

		maxObjectPlace=0;//initial the place to 0

		for(int in=maxObjectPlace+1;in<any.size();in++) 
			if(any.get(maxObjectPlace).compareTo(any.get(in))==-1) 
				maxObjectPlace=in;//find the max object place in the vector

		return any.get(maxObjectPlace);//return the max object
	}

	//print the company data report of employees, customers and events.
	public void firmReport() {
		System.out.println("SalesOffice report:");
		System.out.println(" ");

		PrintEmployeesReport();
		PrintEventReport();
		PrintCustomersReport();
	}

	private void PrintCustomersReport() {//Customers report (name, age and gender)
		byOrder(customersHelper);//order the customers by the number of tickets the ordered
		System.out.println("Customer list:");
		System.out.println(" ");
		for (int k=0; k<customersHelper.size(); k++) {
			System.out.println("Name: "+customersHelper.get(k).getName() + "; Age: " + customersHelper.get(k).getAge() + "; Gender: "+ customersHelper.get(k).getGender());
			System.out.println(" ");
		}
	}
	private void PrintEventReport() {//Events report (name)
		byOrder(eventsHelper);//order the events by the number of tickets in each event
		System.out.println("Event list:");
		System.out.println(" ");
		for (int j=0; j<eventsHelper.size(); j++) {
			System.out.println(eventsHelper.get(j).getName());
			System.out.println(" ");
		}
		System.out.println("--------------------");
		System.out.println(" ");

	}
	private void PrintEmployeesReport() {//Employees report (name and age) 
		byOrder(employeesHelper);//order the employees by their salary
		System.out.println("Employees list:");
		System.out.println(" ");
		for (int i=0; i<employeesHelper.size(); i++) {
			System.out.println("Name: "+ employeesHelper.get(i).getName()+ " Age: " + employeesHelper.get(i).getAge());
			System.out.println(" ");
		}
		System.out.println("--------------------");
		System.out.println(" ");
	}

	//order each vector by his comparable value
	public void byOrder(Vector<? extends Comparable> any) {
		for(int i=0;i<any.size();i++)	
			Collections.swap(any, getMaxIndex(any,i), i);//swap the objects in the vector to the new order system
	}

	//get the index of the max object 
	private int getMaxIndex(Vector<? extends Comparable> any, int index) {
		maxIndex = index;
		for(int in=index+1;in<any.size();in++) 
			if(any.get(maxIndex).compareTo(any.get(in))==-1) 
				maxIndex=in;

		return maxIndex;
	}

	public static double getAvgValue(Vector< ? extends Averageble> any) {//return the avg value of the vector by the vector criteria.
		sumValue=0;
		for(int i=0;i<any.size();i++)
			sumValue+=any.get(i).getValue();

		return sumValue/any.size();
	}

}