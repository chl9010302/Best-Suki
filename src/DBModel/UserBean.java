package DBModel;

public class UserBean { 
	//s
	private String USER_ID_PK         ;
	private String USER_PASSWORD      ;
	private String USER_NAME          ;
	private String USER_ADDRESS       ;
	private String USER_SCHOOLNAME    ;
	private String USER_AGE           ;
	private String USER_GENDER        ;
	private String USER_PHONE         ;
	private String USER_FMPHONE       ;
	private String USER_LOGINSESSION  ;
	private String USER_TEACHERSESSION;
	private String CLASS_NUMBER       ;
	
	public UserBean(){
		setUSER_TEACHERSESSION("1");
	}
	public String getUSER_ID_PK() {
		return USER_ID_PK;
	}

	public void setUSER_ID_PK(String uSER_ID_PK) {
		USER_ID_PK = uSER_ID_PK;
	}

	public String getUSER_PASSWORD() {
		return USER_PASSWORD;
	}

	public void setUSER_PASSWORD(String uSER_PASSWORD) {
		USER_PASSWORD = uSER_PASSWORD;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getUSER_ADDRESS() {
		return USER_ADDRESS;
	}

	public void setUSER_ADDRESS(String uSER_ADDRESS) {
		USER_ADDRESS = uSER_ADDRESS;
	}

	public String getUSER_SCHOOLNAME() {
		return USER_SCHOOLNAME;
	}

	public void setUSER_SCHOOLNAME(String uSER_SCHOOLNAME) {
		USER_SCHOOLNAME = uSER_SCHOOLNAME;
	}

	public String getUSER_AGE() {
		return USER_AGE;
	}

	public void setUSER_AGE(String uSER_AGE) {
		USER_AGE = uSER_AGE;
	}

	public String getUSER_GENDER() {
		return USER_GENDER;
	}

	public void setUSER_GENDER(String uSER_GENDER) {
		USER_GENDER = uSER_GENDER;
	}

	public String getUSER_PHONE() {
		return USER_PHONE;
	}

	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}

	public String getUSER_FMPHONE() {
		return USER_FMPHONE;
	}

	public void setUSER_FMPHONE(String uSER_FMPHONE) {
		USER_FMPHONE = uSER_FMPHONE;
	}

	public String getUSER_LOGINSESSION() {
		return USER_LOGINSESSION;
	}

	public void setUSER_LOGINSESSION(String uSER_LOGINSESSION) {
		USER_LOGINSESSION = uSER_LOGINSESSION;
	}

	public String getUSER_TEACHERSESSION() {
		return USER_TEACHERSESSION;
	}

	public void setUSER_TEACHERSESSION(String uSER_TEACHERSESSION) {
		USER_TEACHERSESSION = uSER_TEACHERSESSION;
	}

	public String getCLASS_NUMBER() {
		return CLASS_NUMBER;
	}

	public void setCLASS_NUMBER(String cLASS_NUMBER) {
		CLASS_NUMBER = cLASS_NUMBER;
	}
}
