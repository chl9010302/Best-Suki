package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.BoardBean;

public class AddBoard {
	BoardBean board;
	Connection conn = null;
	Statement stmt = null;

	public AddBoard(BoardBean addboard) {
		insert(addboard);
		count();
	}
	public boolean insert(BoardBean addboard) {
		String insertsql1 = "insert into board(BoardId, Subtitle, Filepath, Radio1, Radio2, Radio3, Radio4, Radio5, Flag) values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = null;
		board = addboard;
		try {
			 conn = application.DBConnection.getDBConection();
			 pstmt = conn.prepareStatement(insertsql1);
			  			pstmt.setString(1,board.getBoardId() );
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
		try {
			StringBuilder sb = new StringBuilder();
			String sql = sb.append("select count(*) from board")
					.append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.print(rs.getInt("count(*)"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        return 0;
    }
}
