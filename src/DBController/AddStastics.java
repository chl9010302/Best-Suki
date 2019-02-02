package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBModel.BoardBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddStastics {
	BoardBean board;
	Connection conn = null;
	Statement stmt = null;
	private ObservableList<AddStastics> addstastics = FXCollections.observableArrayList();
	private StringProperty USER_ID;
	private StringProperty USER_ACTION;
	private StringProperty WRITE_DATE;
	
	public ObservableList<AddStastics> getstastics() {
		select();
		return addstastics;
	}
	
	public StringProperty getUSER_ID() {
		return USER_ID;
	}
	public StringProperty getUSER_ACTION() {
		return USER_ACTION;
	}
	public StringProperty getWRITE_DATE() {
		return WRITE_DATE;
	}
	
	
	public AddStastics() {
		// TODO Auto-generated constructor stub
	}
	public AddStastics(String user_id, String user_action, String write_date) {
		this.USER_ID = new SimpleStringProperty(user_id);
		this.USER_ACTION = new SimpleStringProperty(user_action); 
		this.WRITE_DATE = new SimpleStringProperty(write_date); 
	}

	public boolean select() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("select * from user_log")
					.append(";").toString(); 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				addstastics.add(new AddStastics((String)rs.getString("USER_ID"), (String)rs.getString("USER_ACTION"), (String)rs.getString("WRITE_DATE")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
