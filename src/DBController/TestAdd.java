package DBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBModel.TestBean;
import ViewController.CommonController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import usingstaticfunction.DBConnectionKeeping;

public class TestAdd {
	public static String test_id_fk = ""; // test_id_fk를 알아내기 위함
	public static String testing_id = ""; // test하고자 하는 id를 전체 뷰에서 사용하기 위함
	public static int pagenumber, maxpage; // testing 중에 페이지를기억하기 위함 / 테스트를 진행하기 위한 초기 값 설정
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt;
	private TestBean testbean;
	private StringProperty test_id_pk, test_subtitle, test_writer, test_time;
	private Button test_btndetail;
	public static ArrayList<String> result_arraylist, initial_result;
	private String sql;
	private StringBuilder sb;
	private ResultSet rs;
	private ObservableList<String> test;
	private int page, lineCnt, fromIndex;
	private DBConnectionKeeping dbConnectionKeeping;
	public StringProperty getTest_writer() {
		return test_writer;
	}
	public void setTest_writer(StringProperty test_writer) {
		this.test_writer = test_writer;
	}
	public StringProperty getTest_time() {
		return test_time;
	}
	public void setTest_time(StringProperty test_time) {
		this.test_time = test_time;
	}
	private ObservableList<TestAdd> testadd = FXCollections.observableArrayList();
	public ObservableList<TestAdd> gettestadd() {
		select();
		return testadd;
	}
	public StringProperty getTest_id_pk() {
		return test_id_pk;
	}
	public StringProperty getTest_Subtitle() {
		return test_subtitle;
	}
	public Button getTest_btndetail() {
		return test_btndetail;
	}
	public void setTest_btndetail(Button test_btndetail) {
		this.test_btndetail = test_btndetail;
	}
	public TestAdd() { }
	public TestAdd(String TEST_ID_PK, String TEST_SUBTITLE, String TEST_WRITER, String TEST_TIME) {
		this.test_id_pk = new SimpleStringProperty(TEST_ID_PK);
		this.test_subtitle = new SimpleStringProperty(TEST_SUBTITLE);
		this.test_writer = new SimpleStringProperty(TEST_WRITER);
		this.test_time = new SimpleStringProperty(TEST_TIME);
		this.test_btndetail = new Button("Details");
		test_btndetail.setOnAction(event -> {
			TestResultAdd.testresult = new ArrayList<String>();
			pagenumber = 0;
			testing_id = "";
			test_id_fk = ""; // 초기화
			testing_id = TEST_ID_PK;
			maxpage = ccount(TEST_ID_PK);
			result_arraylist = CommonController.splitQuestion(CommonController.selectcontent(TEST_ID_PK, "TESTDETAIL_ID_FK", config.StaticProperty.gettest_tb(), "TEST_ID_PK"));
			test_id_fk = result_arraylist.get(0);
			initial_result = CommonController.splitQuestion(CommonController.selectcontent(TestAdd.test_id_fk, "TESTDETAIL_DATA", config.StaticProperty.gettestdetail_tb(), "TESTDETAIL_ID_PK"));
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestingview());
			} catch (IOException e) {e.printStackTrace(); }
		});
	}
	public TestAdd(TestBean Testbean) {
		insertTest(Testbean);
	}
	public boolean updateTestDetail(TestBean testbean) {
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		try {
			conn = usingstaticfunction.DBConnectionKeeping.con;
			stmt = conn.createStatement();
			sql = "UPDATE "+config.StaticProperty.gettest_tb()
			+" SET TEST_SUBTITLE = '" + testbean.getTEST_SUBTITLE() 
			+ "', TESTDETAIL_ID_FK = '" + testbean.getTESTDETAIL_ID_FK() 
			+ "' WHERE TEST_ID_PK = '" + testbean.getTEST_ID_PK() + "';";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) { } return false;
	}
	public boolean insertTest(TestBean Testbean) {
		sql = "INSERT INTO "+config.StaticProperty.gettest_tb()+"(TEST_ID_PK, TEST_SUBTITLE, TESTDETAIL_ID_FK, TEST_WRITER, TEST_TIME) VALUES(?, ?, ?, ?, now());";
		pstmt = null;
		this.testbean = Testbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testbean.getTEST_ID_PK());
			pstmt.setString(2, testbean.getTEST_SUBTITLE());
			pstmt.setString(3, testbean.getTESTDETAIL_ID_FK());
			pstmt.setString(4, testbean.getTEST_WRITER());
			pstmt.executeUpdate();
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) { }
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e3) { }
		} return false;
	}
	public boolean select() {
		sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			sql = sb.append("SELECT * FROM "+config.StaticProperty.gettest_tb()).append(";").toString();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				testadd.add(new TestAdd((String) rs.getString("TEST_ID_PK"), (String) rs.getString("TEST_SUBTITLE"), (String) rs.getString("TEST_WRITER"),(String) rs.getString("TEST_TIME").substring(0,10)));
			}
		} catch (SQLException e) { } return false;
	}
	public void delete(String id) {
		sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		sql = sb.append("DELETE FROM "+config.StaticProperty.gettest_tb()+" WHERE TEST_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { }
	}
	public ObservableList<String> gettestdetailid(String id) {
		test = FXCollections.observableArrayList();
		page = ccount(id);
		for(int i=0; i<CommonController.splitQuestion(CommonController.selectcontent(id, "TESTDETAIL_ID_FK", config.StaticProperty.gettest_tb(), "TEST_ID_PK")).size(); i++) {
			test.add(CommonController.splitQuestion(CommonController.selectcontent(id, "TESTDETAIL_ID_FK", config.StaticProperty.gettest_tb(), "TEST_ID_PK")).get(i));
		}
		return test;
	}
	public int ccount(String id) {
		lineCnt = 1;
	    fromIndex = -1;
	    while ((fromIndex = CommonController.selectcontent(id, "TESTDETAIL_ID_FK", config.StaticProperty.gettest_tb(), "TEST_ID_PK").indexOf(";", fromIndex + 1)) >= 0) {
	      lineCnt++;
	    }
		return lineCnt;
	}
}
