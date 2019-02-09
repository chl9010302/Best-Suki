package DBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.TestDetailBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import usingstaticfunction.DBConnectionKeeping;

public class TestDetailAdd {
	TestDetailBean testdetailbean;
	Connection conn = null;
	Statement stmt = null;
	public static String testdetail_id = "";
	private StringProperty testdetail_id_pk, testdetail_subtitle, testdetail_writer, testdetail_time;
	private Button testdetail_btndetail;
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
	public TestDetailAdd() { }
	public TestDetailAdd(String TESTDETAIL_ID_PK, String TESTDETAIL_SUBTITLE, String TESTDETAIL_WRITER, String TESTDETAIL_TIME) {
		this.testdetail_id_pk = new SimpleStringProperty(TESTDETAIL_ID_PK);
		this.testdetail_subtitle = new SimpleStringProperty(TESTDETAIL_SUBTITLE);
		this.testdetail_writer = new SimpleStringProperty(TESTDETAIL_WRITER);
		this.testdetail_time = new SimpleStringProperty(TESTDETAIL_TIME);
		this.testdetail_btndetail = new Button("Details");
		testdetail_btndetail.setOnAction(event -> {
			testdetail_id = ""; // 초기화
			testdetail_id = testdetail_id_pk.get();
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavtestdetailview());
			} catch (IOException e) { }
		});
	}
	public TestDetailAdd(TestDetailBean testdetailbean) {
		insertTestDetail(testdetailbean);
	}
	public boolean updateTestDetail(TestDetailBean testdetailbean) {
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		Statement stmt = null;
		try {
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			stmt = con.createStatement();
			String updatesql = "UPDATE "+config.StaticProperty.gettestdetail_tb()
			+" SET TESTDETAIL_SUBTITLE = '" + testdetailbean.getTESTDETAIL_SUBTITLE() 
			+ "', TESTDETAIL_DATA1 = '" + testdetailbean.getTESTDETAIL_DATA1() 
			+ "', TESTDETAIL_DATA2 = '" + testdetailbean.getTESTDETAIL_DATA2() 
			+ "', TESTDETAIL_DATA3 = '" + testdetailbean.getTESTDETAIL_DATA3() 
			+ "', TESTDETAIL_DATA4 = '" + testdetailbean.getTESTDETAIL_DATA4() 
			+ "', TESTDETAIL_DATA5 = '" + testdetailbean.getTESTDETAIL_DATA5() 
			+ "', TESTDETAIL_ANSWER = '" + testdetailbean.getTESTDETAIL_ANSWER() 
			+ "', TESTDETAIL_IMAGE = '" + testdetailbean.getTESTDETAIL_IMAGE() 
			+ "' WHERE TESTDETAIL_ID_PK = '" + testdetailbean.getTESTDETAIL_ID_PK() + "';";
			stmt.executeUpdate(updatesql);
			return true;
		} catch (SQLException e) { } return false;
	}
	public boolean insertTestDetail(TestDetailBean testdetailbean) {
		String insertsql = "INSERT INTO "+config.StaticProperty.gettestdetail_tb()+"(TESTDETAIL_ID_PK, TESTDETAIL_DATA1, TESTDETAIL_DATA2, TESTDETAIL_DATA3, TESTDETAIL_DATA4, TESTDETAIL_DATA5, TESTDETAIL_ANSWER, TESTDETAIL_IMAGE, TESTDETAIL_SUBTITLE, TESTDETAIL_WRITER, TESTDETAIL_TIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());";
		PreparedStatement pstmt = null;
		this.testdetailbean = testdetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, testdetailbean.getTESTDETAIL_ID_PK());
			pstmt.setString(2, testdetailbean.getTESTDETAIL_DATA1());
			pstmt.setString(3, testdetailbean.getTESTDETAIL_DATA2());
			pstmt.setString(4, testdetailbean.getTESTDETAIL_DATA3());
			pstmt.setString(5, testdetailbean.getTESTDETAIL_DATA4());
			pstmt.setString(6, testdetailbean.getTESTDETAIL_DATA5());
			pstmt.setString(7, testdetailbean.getTESTDETAIL_ANSWER());
			pstmt.setString(8, testdetailbean.getTESTDETAIL_IMAGE());	
			pstmt.setString(9, testdetailbean.getTESTDETAIL_SUBTITLE());
			pstmt.setString(10, testdetailbean.getTESTDETAIL_WRITER());
			pstmt.executeUpdate();
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
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
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM "+config.StaticProperty.gettestdetail_tb()).append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				testdetailadd.add(new TestDetailAdd((String) rs.getString("TESTDETAIL_ID_PK"), (String) rs.getString("TESTDETAIL_SUBTITLE"), (String) rs.getString("TESTDETAIL_WRITER"),(String) rs.getString("TESTDETAIL_TIME").substring(0,10)));
			}
		} catch (SQLException e) { } return false;
	}
	public void delete(String id) {
		StringBuilder sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		String sql = sb.append("DELETE FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { }
	}
	public String selectSubtitle(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTDETAIL_SUBTITLE FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTDETAIL_SUBTITLE") != null)
					result = rs.getString("TESTDETAIL_SUBTITLE");
			}
		}catch(Exception e) { } return result;
	}
	public String selectDATA1(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTDETAIL_DATA1 FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTDETAIL_DATA1") != null)
					result = rs.getString("TESTDETAIL_DATA1");
			}
		}catch(Exception e) { } return result;
	}
	public String selectDATA2(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTDETAIL_DATA2 FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTDETAIL_DATA2") != null)
					result = rs.getString("TESTDETAIL_DATA2");
			}
		}catch(Exception e) { } return result;
	}
	public String selectDATA3(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTDETAIL_DATA3 FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTDETAIL_DATA3") != null)
					result = rs.getString("TESTDETAIL_DATA3");
			}
		}catch(Exception e) { } return result;
	}
	public String selectDATA4(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTDETAIL_DATA4 FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTDETAIL_DATA4") != null)
					result = rs.getString("TESTDETAIL_DATA4");
			}
		}catch(Exception e) { } return result;
	}
	public String selectDATA5(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT TESTDETAIL_DATA5 FROM "+config.StaticProperty.gettestdetail_tb()+" WHERE TESTDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("TESTDETAIL_DATA5") != null)
					result = rs.getString("TESTDETAIL_DATA5");
			}
		}catch(Exception e) { } return result;
	}
}
