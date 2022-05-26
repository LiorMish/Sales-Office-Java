public class FrontalOrders extends Orders{
		private int employeeID;

		public int getEmployeeID() {//get method to the employee ID
			return employeeID;
		}

		public FrontalOrders (String eventID, String customerID, String employeeID, String numberOfTickets) {//frontal order constructor,create new frontalorder object with the specific fields.
			super(eventID, customerID, numberOfTickets);
			this.employeeID=Integer.parseInt(employeeID);
		}
	}