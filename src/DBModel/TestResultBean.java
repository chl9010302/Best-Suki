package DBModel;

public class TestResultBean {

	private String TESTRESULT_ID_PK;
	private String TEST_ID;
	private String TESTRESULT_ID;
	private String TESTRESULT_ANSWER;
	private String TESTRESULT_WRITER;
	
	public TestResultBean() {
		
	}
	public String getTESTRESULT_ID_PK() {
		return TESTRESULT_ID_PK;
	}
	public void setTESTRESULT_ID_PK(String tESTRESULT_ID_PK) {
		TESTRESULT_ID_PK = tESTRESULT_ID_PK;
	}
	public String getTEST_ID() {
		return TEST_ID;
	}
	public void setTEST_ID(String tEST_ID) {
		TEST_ID = tEST_ID;
	}
	public String getTESTRESULT_ID() {
		return TESTRESULT_ID;
	}
	public void setTESTRESULT_ID(String tESTRESULT_ID) {
		TESTRESULT_ID = tESTRESULT_ID;
	}
	public String getTESTRESULT_ANSWER() {
		return TESTRESULT_ANSWER;
	}
	public void setTESTRESULT_ANSWER(String tESTRESULT_ANSWER) {
		TESTRESULT_ANSWER = tESTRESULT_ANSWER;
	}
	public String getTESTRESULT_WRITER() {
		return TESTRESULT_WRITER;
	}
	public void setTESTRESULT_WRITER(String tESTRESULT_WRITER) {
		TESTRESULT_WRITER = tESTRESULT_WRITER;
	}
	
}