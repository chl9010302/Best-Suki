package DBModel;

import javafx.scene.control.Button;

public class TestBean {

	private String TEST_ID_PK;
	private String TEST_SUBTITLE;
	private String TEST_TYPE;
	private String TESTDETAIL_ID_FK;
	private String TEST_WRITER;
	private Button TEST_BTNDETAIL;
	public String getTEST_ID_PK() {
		return TEST_ID_PK;
	}
	public void setTEST_ID_PK(String tEST_ID_PK) {
		TEST_ID_PK = tEST_ID_PK;
	}
	public String getTEST_SUBTITLE() {
		return TEST_SUBTITLE;
	}
	public void setTEST_SUBTITLE(String tEST_SUBTITLE) {
		TEST_SUBTITLE = tEST_SUBTITLE;
	}
	public String getTEST_TYPE() {
		return TEST_TYPE;
	}
	public void setTEST_TYPE(String tEST_TYPE) {
		TEST_TYPE = tEST_TYPE;
	}
	public String getTESTDETAIL_ID_FK() {
		return TESTDETAIL_ID_FK;
	}
	public void setTESTDETAIL_ID_FK(String tESTDETAIL_ID_FK) {
		TESTDETAIL_ID_FK = tESTDETAIL_ID_FK;
	}
	public String getTEST_WRITER() {
		return TEST_WRITER;
	}
	public void setTEST_WRITER(String tEST_WRITER) {
		TEST_WRITER = tEST_WRITER;
	}
	public Button getTEST_BTNDETAIL() {
		return TEST_BTNDETAIL;
	}
	public void setTEST_BTNDETAIL(Button tEST_BTNDETAIL) {
		TEST_BTNDETAIL = tEST_BTNDETAIL;
	}
	
	
}