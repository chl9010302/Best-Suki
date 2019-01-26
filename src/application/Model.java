package application;

public class Model {
	public int calculate(String operator, int x, int y) {
		if(operator.equals("+")) {
			return x + y;
		}
		else if(operator.equals("-")) {
			return x - y;
		}
		else if(operator.equals("*")) {
			return x * y;
		}
		else {
			return x / y;
		}
	}
	public void user(String User[]) {
//		String UserId, String UserPassword, String UserName, String UserAddress,
//		String UserSchoolName, String UserAge, String UserGender, String UserPhone, String UserFMPhone, String UserFmphone, String UserStudentnumber, String UserLoginsession, String Isteacher
	}
}
