import java.util.Vector;
public class SalesPerson extends Employees {
	private double bonusRate;

	public double getBonusRate() {//get the bonus rate of the employee
		return bonusRate;
	}
	public SalesPerson(String ID, String name, String age, String bonusRate) {//sales person constructor,create new sales person  object with the specific fields.
		super(ID, name, age);
		this.bonusRate = Double.parseDouble(bonusRate);
	
	}
	public void CalculateSalary(Vector <Orders> orders, Vector <Events> events, Vector <Customers> customer) {//Implementation of abstract method ,calculate the salary by sales person specific terms.

		for (int i=0; i<orders.size(); i++) {
			if (!(orders.get(i) instanceof FrontalOrders))
				continue;
			if (this.ID==((FrontalOrders)orders.get(i)).getEmployeeID()) {
				this.salary+=(orders.get(i).getOrderPrice()*this.bonusRate);					
			}		
		}
	}

}