package DBController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddStatistics {
	private Connection conn = null;
	private Statement stmt = null;
	private String sql;
	private StringBuilder sb;
	private ObservableList<AddStatistics> AddSTATISTICS = FXCollections.observableArrayList();
	private StringProperty USER_ID, USER_LOGIN_DATE, USER_LOGOUT_DATE;
	private ResultSet rs;
	public AddStatistics() { }
	public ObservableList<AddStatistics> getstatistics() {
		select();
		return AddSTATISTICS;
	}
	public StringProperty getUSER_ID() {
		return USER_ID;
	}
	public StringProperty getUSER_LOGIN_DATE() {
		return USER_LOGIN_DATE;
	}
	public StringProperty getUSER_LOGOUT_DATE() {
		return USER_LOGOUT_DATE;
	}
	public AddStatistics(String user_id, String user_login_date, String user_logout_date) {
		this.USER_ID = new SimpleStringProperty(user_id);
		this.USER_LOGIN_DATE = new SimpleStringProperty(user_login_date); 
		this.USER_LOGOUT_DATE = new SimpleStringProperty(user_logout_date); 
	}
	public boolean select() {
		sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		try {
			stmt = conn.createStatement();
			sql = sb.append("SELECT * FROM ").append(config.StaticProperty.getdate_tb()).append(" ORDER BY DATE_LOGINTIME DESC")
					.append(";").toString(); 
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				AddSTATISTICS.add(new AddStatistics((String)rs.getString("USER_ID"), (String)rs.getString("DATE_LOGINTIME"), (String)rs.getString("DATE_LOGOUTTIME")));
			}
		} catch (SQLException e) { e.printStackTrace();	}
		return false;
	}
}
