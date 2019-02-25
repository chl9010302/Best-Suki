package DBController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddStastics {
	private Connection conn = null;
	private Statement stmt = null;
	private String sql;
	private StringBuilder sb;
	private ObservableList<AddStastics> addstastics = FXCollections.observableArrayList();
	private StringProperty USER_ID, USER_LOGIN_DATE, USER_LOGOUT_DATE;
	private ResultSet rs;
	public AddStastics() {
		// TODO Auto-generated constructor stub
	}
	public ObservableList<AddStastics> getstastics() {
		select();
		return addstastics;
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
	public AddStastics(String user_id, String user_login_date, String user_logout_date) {
		this.USER_ID = new SimpleStringProperty(user_id);
		this.USER_LOGIN_DATE = new SimpleStringProperty(user_login_date); 
		this.USER_LOGOUT_DATE = new SimpleStringProperty(user_logout_date); 
	}
	public boolean select() {
		sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			sql = sb.append("SELECT * FROM "+config.StaticProperty.getdate_tb()+" ORDER BY DATE_LOGINTIME DESC")
					.append(";").toString(); 
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				addstastics.add(new AddStastics((String)rs.getString("USER_ID"), (String)rs.getString("DATE_LOGINTIME"), (String)rs.getString("DATE_LOGOUTTIME")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
