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

public class AddBoard {
	BoardBean board;
	Connection conn = null;
	Statement stmt = null;
	ArrayList<StringProperty> arraylist;
	private ObservableList<AddBoard> addboard = FXCollections.observableArrayList();
	private StringProperty boardId;
	private StringProperty subtitle;
	ArrayList<StringProperty> arraylist2;
	
	public ObservableList<AddBoard> getaddboard() {
		select();
		return addboard;
	}
	
	public StringProperty getarraylist() {
		return boardId;
	}
	public StringProperty getarraylist2() {
		return subtitle;
	}
	
	
	public AddBoard() {
		// TODO Auto-generated constructor stub
	}
	public AddBoard(String BoardId, String Subtitle) {
		this.boardId = new SimpleStringProperty(BoardId);
		this.subtitle = new SimpleStringProperty(Subtitle); 
	}
	public AddBoard(BoardBean addboard) {
		insert(addboard);
	}

	public boolean insert(BoardBean addboard) {
		String insertsql1 = "insert into board(BoardId, Subtitle, Filepath, Radio1, Radio2, Radio3, Radio4, Radio5, Flag) values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = null;
		board = addboard;
		try {
			 conn = application.DBConnection.getDBConection();
			 pstmt = conn.prepareStatement(insertsql1);
			  			pstmt.setString(1,board.getBoardId());
			  			pstmt.setString(2,board.getSubtitle()); 
			  			pstmt.setString(3, board.getFilepath()); 
			  			pstmt.setString(4, board.getRadio1()); 
			  			pstmt.setString(5, board.getRadio2()); 
			  			pstmt.setString(6, board.getRadio3()); 
			  			pstmt.setString(7, board.getRadio4()); 
			  			pstmt.setString(8, board.getRadio5()); 
			  			pstmt.setString(9, board.getFlag()); 
			  			pstmt.executeUpdate();
			conn.close();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {

			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e3) {

			}
		}
		return false;
	}
	public int count(){
		int rowcount = 0;
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("select count(*) from board")
					.append(";").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) rowcount = rs.getInt(1);
		}catch(Exception e) { }
		
        return rowcount;
    }
	
	public boolean select() {
		arraylist = new ArrayList();
		arraylist2 = new ArrayList();
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("select * from board")
					.append(";").toString(); 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println("ddd");
				addboard.add(new AddBoard((String)rs.getString("BoardId"), (String)rs.getString("Subtitle")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
