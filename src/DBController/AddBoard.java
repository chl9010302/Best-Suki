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
	private ObservableList<AddBoard> addboard = FXCollections.observableArrayList();
	private StringProperty boardId;
	private StringProperty subtitle;

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
		String insertsql1 = "INSERT INTO TESTDETAIL_TB(TESTDETAIL_ID_PK, TESTDETAIL_SUBTITLE, TESTDETAIL_IMAGE, TESTDETAIL_DATA1, TESTDETAIL_DATA2, TESTDETAIL_DATA3, TESTDETAIL_DATA4, TESTDETAIL_DATA5, TESTDETAIL_ANSWER) values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = null;
		board = addboard;
		try {
			conn = application.DBConnection.getDBConection();
			pstmt = conn.prepareStatement(insertsql1);
			pstmt.setString(1, board.getBoardId());
			pstmt.setString(2, board.getSubtitle());
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

	public int count() {
		int rowcount = 0;
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT COUNT(*) FROM BOARD").append(";").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				rowcount = rs.getInt(1);
		} catch (Exception e) {
		}

		return rowcount;
	}

	public boolean select() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM BOARD").append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				addboard.add(new AddBoard((String) rs.getString("BoardId"), (String) rs.getString("Subtitle")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void delete(int id) {
		StringBuilder sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		String sql = sb.append("delete from board where BoardId = ").append(id).append(";").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int lastselect() {
		int lastselect = 0;
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM BOARD ORDER BY BoardId desc limit 1").append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				lastselect = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ++lastselect;
	}

}
