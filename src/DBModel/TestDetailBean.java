package DBModel;

import java.io.FileInputStream;

import javafx.scene.control.Button;

public class TestDetailBean {

	private String TESTDETAIL_ID_PK;
	private String TESTDETAIL_DATA;
	private String TESTDETAIL_ANSWER;
	private FileInputStream TESTDETAIL_IMAGE;
	private String TESTDETAIL_IMAGE_PATH;
	private String TESTDETAIL_SUBTITLE;
	private String TESTDETAIL_WRITER;
	private Button TESTDETAIL_BTNDETAIL;
	public String getTESTDETAIL_WRITER() {
		return TESTDETAIL_WRITER;
	}

	public void setTESTDETAIL_WRITER(String tESTDETAIL_WRITER) {
		TESTDETAIL_WRITER = tESTDETAIL_WRITER;
	}
	
	public TestDetailBean() { }
	
	public String getTESTDETAIL_ID_PK() {
		return TESTDETAIL_ID_PK;
	}

	public void setTESTDETAIL_ID_PK(String tESTDETAIL_ID_PK) {
		TESTDETAIL_ID_PK = tESTDETAIL_ID_PK;
	}

	public String getTESTDETAIL_DATA() {
		return TESTDETAIL_DATA;
	}

	public void setTESTDETAIL_DATA(String tESTDETAIL_DATA) {
		TESTDETAIL_DATA = tESTDETAIL_DATA;
	}

	public String getTESTDETAIL_ANSWER() {
		return TESTDETAIL_ANSWER;
	}

	public void setTESTDETAIL_ANSWER(String tESTDETAIL_ANSWER) {
		TESTDETAIL_ANSWER = tESTDETAIL_ANSWER;
	}

	public String getTESTDETAIL_IMAGE_PATH() {
		return TESTDETAIL_IMAGE_PATH;
	}
	
	public FileInputStream getTESTDETAIL_IMAGE() {
		return TESTDETAIL_IMAGE;
	}

	public void setTESTDETAIL_IMAGE(FileInputStream tESTDETAIL_IMAGE) {
		TESTDETAIL_IMAGE = tESTDETAIL_IMAGE;
	}

	public void setTESTDETAIL_IMAGE_PATH(String tESTDETAIL_IMAGE_PATH) {
		TESTDETAIL_IMAGE_PATH = tESTDETAIL_IMAGE_PATH;
	}

	public String getTESTDETAIL_SUBTITLE() {
		return TESTDETAIL_SUBTITLE;
	}

	public void setTESTDETAIL_SUBTITLE(String tESTDETAIL_SUBTITLE) {
		TESTDETAIL_SUBTITLE = tESTDETAIL_SUBTITLE;
	}

	public Button getTESTDETAIL_BTNDETAIL() {
		return TESTDETAIL_BTNDETAIL;
	}

	public void setTESTDETAIL_BTNDETAIL(Button tESTDETAIL_BTNDETAIL) {
		TESTDETAIL_BTNDETAIL = tESTDETAIL_BTNDETAIL;
	}
	
}
