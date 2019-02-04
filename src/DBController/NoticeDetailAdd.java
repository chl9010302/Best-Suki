package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.NoticeDetailBean;
import DBModel.TestDetailBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NoticeDetailAdd {

	NoticeDetailBean noticedetailbean;
	Connection conn = null;
	Statement stmt = null;
	private StringProperty noticedetail_id_pk;
	private StringProperty noticedetail_subtitle;
	private StringProperty noticedetail_writer;
	private StringProperty noticedetail_time;
	private StringProperty noticedetail_context;

	public StringProperty getNoticedetail_id_pk() {
		return noticedetail_id_pk;
	}

	public StringProperty getNoticedetail_subtitle() {
		return noticedetail_subtitle;
	}

	public StringProperty getNoticedetail_writer() {
		return noticedetail_writer;
	}

	public StringProperty getNoticedetail_time() {
		return noticedetail_time;
	}

	public StringProperty getNoticedetail_context() {
		return noticedetail_context;
	}

	private ObservableList<NoticeDetailAdd> noticedetailadd = FXCollections.observableArrayList();
	
	public ObservableList<NoticeDetailAdd> getnoticedetailadd() {
		select();
		return noticedetailadd;
	}
	
	public NoticeDetailAdd() { }
	
	public NoticeDetailAdd(String NOTICEDETAIL_ID_PK, String NOTICEDETAIL_SUBTITLE, String NOTICEDETAIL_WRITER, String NOTICEDETAIL_TIME, String NOTICEDETAIL_CONTEXT) {
		this.noticedetail_id_pk = new SimpleStringProperty(NOTICEDETAIL_ID_PK);
		this.noticedetail_subtitle = new SimpleStringProperty(NOTICEDETAIL_SUBTITLE);
		this.noticedetail_writer = new SimpleStringProperty(NOTICEDETAIL_WRITER);
		this.noticedetail_time = new SimpleStringProperty(NOTICEDETAIL_TIME);
		this.noticedetail_context = new SimpleStringProperty(NOTICEDETAIL_CONTEXT);
	}

	public NoticeDetailAdd(NoticeDetailBean noticedetailbean) {
		insertNoticeDetail(noticedetailbean);
	}
	public boolean insertNoticeDetail(NoticeDetailBean noticedetailbean) {
		String insertsql = "INSERT INTO NOTICEDETAIL_TB(NOTICEDETAIL_ID_PK, NOTICEDETAIL_SUBTITLE, NOTICEDETAIL_WRITER, NOTICEDETAIL_TIME, NOTICEDETAIL_CONTEXT) VALUES(?, ?, ?, now(), ?);";
		PreparedStatement pstmt = null;
		this.noticedetailbean = noticedetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, noticedetailbean.getNOTICEDETAIL_ID_PK());
			pstmt.setString(2, noticedetailbean.getNOTICEDETAIL_SUBTITLE());
			pstmt.setString(3, noticedetailbean.getNOTICEDETAIL_WRITER());
			pstmt.setString(4, noticedetailbean.getNOTICEDETAIL_CONTEXT());
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
	
	public boolean select() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM NOTICEDETAIL_TB").append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				noticedetailadd.add(new NoticeDetailAdd((String) rs.getString("NOTICEDETAIL_ID_PK"), (String) rs.getString("NOTICEDETAIL_SUBTITLE"), (String) rs.getString("NOTICEDETAIL_WRITER"),(String) rs.getString("NOTICEDETAIL_TIME").substring(0,10), rs.getString("NOTICEDETAIL_CONTEXT")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void delete(String id) {
		StringBuilder sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		String sql = sb.append("DELETE FROM NOTICEDETAIL_TB where NOTICEDETAIL_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
