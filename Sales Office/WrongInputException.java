public class WrongInputException extends RuntimeException{

	public WrongInputException(String str) {//Throw exception when the user input is wrong
	System.err.println(str);
	}
	
}
