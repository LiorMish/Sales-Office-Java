import java.util.Vector;
public class MarketingPerson extends Employees {
	private  String cellPhone;

	public String getCellPhone() {//get the cellphone number of the marketing employee
		return cellPhone;
	}

	public MarketingPerson(String ID, String name, String age, String cellPhone)  {//marketing person constructor,create new marketing person  object with the specific fields.
		super(ID, name, age);
		this.cellPhone = cellPhone;

	}

	public void CalculateSalary(Vector <Orders> orders, Vector <Events> events, Vector <Customers> customers) {//Implementation of abstract method ,calculate the salary, by marketing person specific terms.
  
		for (int i=0; i<customers.size(); i++) {
			if (this.getID()==customers.get(i).getRegisteredBy()) {
				this.salary+=2;

				for (int j=0; j<orders.size();j++) {
					if (orders.get(j).getCustomerID()==customers.get(i).getID())
						this.salary+=(orders.get(j).getOrderPrice()*0.01);			
				}
			}
		}
	}
	
}