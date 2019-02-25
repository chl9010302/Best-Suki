package DBController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBModel.TestDetailBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import usingstaticfunction.DBConnectionKeeping;

public class TestDetailAdd {
	TestDetailBean testdetailbean;
	public static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt;
	public static ArrayList<String> selected_testid;
	public static String testdetail_id = "";
	private StringProperty testdetail_id_pk, testdetail_subtitle, testdetail_writer, testdetail_time;
	private Button testdetail_btndetail;
	private CheckBox testdetail_checkboxdetail;
	private DBConnectionKeeping dbConnectionKeeping;
	private String sql, result;
	private StringBuilder sb;
	private ResultSet rs;
	private File theFile;
	private FileOutputStream output;
	public StringProperty getTestdetail_writer() {
		return testdetail_writer;
	}
	public void setTestdetail_writer(StringProperty testdetail_writer) {
		this.testdetail_writer = testdetail_writer;
	}
	public StringProperty getTestdetail_time() {
		return testdetail_time;
	}
	public void setTestdetail_time(StringProperty testdetail_time) {
		this.testdetail_time = testdetail_time;
	}
	private ObservableList<TestDetailAdd> testdetailadd = FXCollections.observableArrayList();
	public ObservableList<TestDetailAdd> gettestdetailadd() {
		select();
		return testdetailadd;
	}
	public StringProperty getTestdetail_id_pk() {
		return testdetail_id_pk;
	}
	public StringProperty getTestdetail_Subtitle() {
		return testdetail_subtitle;
	}
	public Button getTestdetail_btndetail() {
		return testdetail_btndetail;
	}
	public void setTestdetail_btndetail(Button testdetail_btndetail) {
		this.testdetail_btndetail = testdetail_btndetail;
	}
	public CheckBox getTestdetail_checkboxdetail() {
		return testdetail_checkboxdetail;
	}
	public void setTestdetail_checkboxdetail(CheckBox testdetail_checkboxdetail) {
		this.testdetail_checkboxdetail = testdetail_checkboxdetail;
	}
	public TestDetailAdd() { }
	public TestDetailAdd(String TESTDETAIL_ID_PK, String TESTDETAIL_SUBTITLE, String TESTDETAIL_WRITER, String TESTDETAIL_TIME) {
		selected_testid = new ArrayList<String>();
		this.testdetail_id_pk = new SimpleStringProperty(TESTDETAIL_ID_PK);
		this.testdetail_subtitle = new SimpleStringProperty(TESTDETAIL_SUBTITLE);
		this.testdetail_writer = new SimpleStringProperty(TESTDETAIL_WRITER);
		this.testdetail_time = new SimpleStringProperty(TESTDETAIL_TIME);
		this.testdetail_btndetail = new Button("Details");
		this.testdetail_checkboxdetail = new CheckBox(TESTDETAIL_SUBTITLE);
		testdetail_btndetail.setOnAction(event -> {
			testdetail_id = ""; // 초기화
			testdetail_id = testdetail_id_pk.get();
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestboarddetailview());
			} catch (IOException e) {e.printStackTrace(); }
		});
	}
	public TestDetailAdd(TestDetailBean testdetailbean) {
		insertTestDetail(testdetailbean);
	}
	public boolean updateTestDetail(TestDetailBean testdetailbean) {
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		//이미지 연결 시에는 PreparedStatement를 통해 setBinaryStream을사용해야 함.
		sql = "UPDATE "+config.StaticProperty.gettestdetail_tb()
		+" SET TESTDETAIL_SUBTITLE = '" + testdetailbean.getTESTDETAIL_SUBTITLE() 
		+ "', TESTDETAIL_DATA = '" + testdetailbean.getTESTDETAIL_DATA()
		+ "', TESTDETAIL_ANSWER = '" + testdetailbean.getTESTDETAIL_ANSWER() 
		+ "', TESTDETAIL_IMAGE_PATH = '" + testdetailbean.getTESTDETAIL_IMAGE_PATH() 
		+ "', TESTDETAIL_IMAGE = ? " 
		+ " WHERE TESTDETAIL_ID_PK = '" + testdetailbean.getTESTDETAIL_ID_PK() + "';";
		try {
			pstmt = null;
			conn = usingstaticfunction.DBConnectionKeeping.con;
			pstmt = conn.prepareStatement(sql);
			pstmt.setBinaryStream(1, testdetailbean.getTESTDETAIL_IMAGE());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {e.printStackTrace(); } return false;
	}
	public boolean insertTestDetail(TestDetailBean testdetailbean) {
		sql = "INSERT INTO "+config.StaticProperty.gettestdetail_tb()+"(TESTDETAIL_ID_PK, TESTDETAIL_DATA, TESTDETAIL_ANSWER, TESTDETAIL_IMAGE, TESTDETAIL_IMAGE_PATH, TESTDETAIL_SUBTITLE, TESTDETAIL_WRITER, TESTDETAIL_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, now());";
		pstmt = null;
		this.testdetailbean = testdetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testdetailbean.getTESTDETAIL_ID_PK());
			pstmt.setString(2, testdetailbean.getTESTDETAIL_DATA());
			pstmt.setString(3, testdetailbean.getTESTDETAIL_ANSWER());
			pstmt.setBinaryStream(4, testdetailbean.getTESTDETAIL_IMAGE());	
			pstmt.setString(5, testdetailbean.getTESTDETAIL_IMAGE_PATH());
			pstmt.setString(6, testdetailbean.getTESTDETAIL_SUBTITLE());
			pstmt.setString(7, testdetailbean.getTESTDETAIL_WRITER());
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
			sql = sb.append("SELECT * FROM "+config.StaticProperty.gettestdetail_tb()).append(" ORDER BY TESTDETAIL_TIME DESC;").toString();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				testdetailadd.add(new TestDetailAdd((String) rs.getString("TESTDETAIL_ID_PK"), (String) rs.getString("TESTDETAIL_SUBTITLE"), (String) rs.getString("TESTDETAIL_WRITER"),(String) rs.getString("TESTDETAIL_TIME").substring(0,10)));
			}
		} catch (SQLException e) { } return false;
	}
	public void delete(String id) {
		sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		sql = sb.append("DELETE FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { }
	}
	public File selectIMAGE(String id) {
		try {
			sb = new StringBuilder();
			theFile = new File("abc.jpg");
			output =new FileOutputStream(theFile);
			conn = application.DBConnection.getDBConection();
			sql = sb.append("SELECT TESTDETAIL_IMAGE FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				InputStream input = rs.getBinaryStream("TESTDETAIL_IMAGE");
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0 ) {
					output.write(buffer);
				}
			}
			return theFile;
		}catch(Exception e) { } return null;
	}
	public String selectANSWER(String id) {
		result = "";
		try {
			sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			sql = sb.append("SELECT TESTDETAIL_ANSWER FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if(rs.getString("TESTDETAIL_ANSWER") != null)
					result = rs.getString("TESTDETAIL_ANSWER");
			}
		}catch(Exception e) { } return result;
	}
}
