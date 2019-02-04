package DBModel;

public class TestDetailBean {

	private String TESTDETAIL_ID_PK;
	private String TESTDETAIL_DATA1;
	private String TESTDETAIL_DATA2;
	private String TESTDETAIL_DATA3;
	private String TESTDETAIL_DATA4;
	private String TESTDETAIL_DATA5;
	private String TESTDETAIL_ANSWER;
	private String TESTDETAIL_IMAGE;
	private String TESTDETAIL_SUBTITLE;
	private String TESTDETAIL_WRITER;
	
	public String getTESTDETAIL_WRITER() {
		return TESTDETAIL_WRITER;
	}

	public void setTESTDETAIL_WRITER(String tESTDETAIL_WRITER) {
		TESTDETAIL_WRITER = tESTDETAIL_WRITER;
	}

	private int Totalcount; // Board의 총 수를 가지고오기 위함.
	
	public TestDetailBean() { }
	
	
	public int getCount() {
		return Totalcount;
	}
	public void setBoardId(int totalcount) {
		Totalcount = totalcount;
	}
	
	public String getTESTDETAIL_ID_PK() {
		return TESTDETAIL_ID_PK;
	}

	public void setTESTDETAIL_ID_PK(String tESTDETAIL_ID_PK) {
		TESTDETAIL_ID_PK = tESTDETAIL_ID_PK;
	}

	public String getTESTDETAIL_DATA1() {
		return TESTDETAIL_DATA1;
	}

	public void setTESTDETAIL_DATA1(String tESTDETAIL_DATA1) {
		TESTDETAIL_DATA1 = tESTDETAIL_DATA1;
	}

	public String getTESTDETAIL_DATA2() {
		return TESTDETAIL_DATA2;
	}

	public void setTESTDETAIL_DATA2(String tESTDETAIL_DATA2) {
		TESTDETAIL_DATA2 = tESTDETAIL_DATA2;
	}

	public String getTESTDETAIL_DATA3() {
		return TESTDETAIL_DATA3;
	}

	public void setTESTDETAIL_DATA3(String tESTDETAIL_DATA3) {
		TESTDETAIL_DATA3 = tESTDETAIL_DATA3;
	}

	public String getTESTDETAIL_DATA4() {
		return TESTDETAIL_DATA4;
	}

	public void setTESTDETAIL_DATA4(String tESTDETAIL_DATA4) {
		TESTDETAIL_DATA4 = tESTDETAIL_DATA4;
	}

	public String getTESTDETAIL_DATA5() {
		return TESTDETAIL_DATA5;
	}

	public void setTESTDETAIL_DATA5(String tESTDETAIL_DATA5) {
		TESTDETAIL_DATA5 = tESTDETAIL_DATA5;
	}

	public String getTESTDETAIL_ANSWER() {
		return TESTDETAIL_ANSWER;
	}

	public void setTESTDETAIL_ANSWER(String tESTDETAIL_ANSWER) {
		TESTDETAIL_ANSWER = tESTDETAIL_ANSWER;
	}

	public String getTESTDETAIL_IMAGE() {
		return TESTDETAIL_IMAGE;
	}

	public void setTESTDETAIL_IMAGE(String tESTDETAIL_IMAGE) {
		TESTDETAIL_IMAGE = tESTDETAIL_IMAGE;
	}

	public String getTESTDETAIL_SUBTITLE() {
		return TESTDETAIL_SUBTITLE;
	}

	public void setTESTDETAIL_SUBTITLE(String tESTDETAIL_SUBTITLE) {
		TESTDETAIL_SUBTITLE = tESTDETAIL_SUBTITLE;
	}
}
