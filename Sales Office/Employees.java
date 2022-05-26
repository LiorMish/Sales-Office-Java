import java.util.Vector;
abstract class Employees implements Comparable,Averageble{
	protected  int ID;
	private  int age;
	private  String name;
	protected double salary;

	/*get fields*/
	public int getID() {
		return ID;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Employees(String ID, String name, String age) {// employees constructor,create new customer object with the specific fields.
		this.ID = Integer.parseInt(ID);
		this.name = name;
		this.age = Integer.parseInt(age);
		this.salary=0;
	}

	abstract public void CalculateSalary(Vector <Orders> orders, Vector <Events> events, Vector <Customers> customers);//every type of employees will create his own calculate salary method by his terms

	public int compareTo( Object other ){ //Implementation of "compareTo" function  of comparable interface by salary criteria.


		if( this.salary>((Employees)other).salary)
			return 1;
		if( this.salary<((Employees)other).salary)
			return -1;
		else
			return 0;	
	}


	public double getValue() {	//Implementation of "getvalue" function  of averageble interface,return the value you want to do average to him, salary.
		return this.salary;
	}

}