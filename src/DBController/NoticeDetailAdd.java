package DBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.NoticeDetailBean;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import usingstaticfunction.DBConnectionKeeping;

public class NoticeDetailAdd {
	NoticeDetailBean noticedetailbean;
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	DBConnectionKeeping dbConnectionKeeping;
	private String sql;
	private StringBuilder sb;
	private ResultSet rs;
	public static String noticedetail_id = "";
	private StringProperty noticedetail_id_pk, noticedetail_subtitle, noticedetail_writer, noticedetail_time, noticedetail_context;
	private Button noticedetail_btndetail;
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
	public Button getNoticedetail_btndetail() {
		return noticedetail_btndetail;
	}
	public void setNoticedetail_btndetail(Button noticedetail_btndetail) {
		this.noticedetail_btndetail = noticedetail_btndetail;
	}
	private ObservableList<NoticeDetailAdd> noticedetailadd = FXCollections.observableArrayList();
	public ObservableList<NoticeDetailAdd> getnoticedetailadd() {
		select();
		return noticedetailadd;
	}
	public NoticeDetailAdd() { 
	}
	public NoticeDetailAdd(NoticeDetailBean noticedetailbean) {
		insertNoticeDetail(noticedetailbean);
	}
	public NoticeDetailAdd(String NOTICEDETAIL_ID_PK, String NOTICEDETAIL_SUBTITLE, String NOTICEDETAIL_WRITER, String NOTICEDETAIL_TIME, String NOTICEDETAIL_CONTEXT) {
		this.noticedetail_id_pk = new SimpleStringProperty(NOTICEDETAIL_ID_PK);
		this.noticedetail_subtitle = new SimpleStringProperty(NOTICEDETAIL_SUBTITLE);
		this.noticedetail_writer = new SimpleStringProperty(NOTICEDETAIL_WRITER);
		this.noticedetail_time = new SimpleStringProperty(NOTICEDETAIL_TIME);
		this.noticedetail_context = new SimpleStringProperty(NOTICEDETAIL_CONTEXT);
		this.noticedetail_btndetail = new Button("Details");
		noticedetail_btndetail.setOnAction(event -> {
			noticedetail_id = noticedetail_id_pk.get();
			try {
				ViewController.CommonController.NAV(getClass(), event, config.StaticProperty.getnavmaindetailview());
			} catch (IOException e) { }
		});
	}
	
	public boolean insertNoticeDetail(NoticeDetailBean noticedetailbean) {
		sql = "INSERT INTO "+config.StaticProperty.getnoticedetail_tb()+"(NOTICEDETAIL_ID_PK, NOTICEDETAIL_SUBTITLE, NOTICEDETAIL_WRITER, NOTICEDETAIL_TIME, NOTICEDETAIL_CONTEXT) VALUES(?, ?, ?, now(), ?);";
		pstmt = null;
		this.noticedetailbean = noticedetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticedetailbean.getNOTICEDETAIL_ID_PK());
			pstmt.setString(2, noticedetailbean.getNOTICEDETAIL_SUBTITLE());
			pstmt.setString(3, noticedetailbean.getNOTICEDETAIL_WRITER());
			pstmt.setString(4, noticedetailbean.getNOTICEDETAIL_CONTEXT());
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
		}
		return false;
	}
	public boolean updateNoticeDetail(NoticeDetailBean noticedetailbean) {
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		try {
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			stmt = con.createStatement();
			sql = "UPDATE "+config.StaticProperty.getnoticedetail_tb()+" SET NOTICEDETAIL_SUBTITLE = '" + noticedetailbean.getNOTICEDETAIL_SUBTITLE() + "', NOTICEDETAIL_CONTEXT = '" + noticedetailbean.getNOTICEDETAIL_CONTEXT() + "' WHERE NOTICEDETAIL_ID_PK = '" + noticedetailbean.getNOTICEDETAIL_ID_PK() + "';";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {}
		return false;
	}
	public boolean select() {
		sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			sql = sb.append("SELECT * FROM "+config.StaticProperty.getnoticedetail_tb()).append(";").toString();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				noticedetailadd.add(new NoticeDetailAdd((String) rs.getString("NOTICEDETAIL_ID_PK"), (String) rs.getString("NOTICEDETAIL_SUBTITLE"), (String) rs.getString("NOTICEDETAIL_WRITER"),(String) rs.getString("NOTICEDETAIL_TIME").substring(0,10), rs.getString("NOTICEDETAIL_CONTEXT")));
			}
		} catch (SQLException e) { }
		return false;
	}
	public void delete(String id) {
		sb = new StringBuilder();
		conn = application.DBConnection.getDBConection();
		String sql = sb.append("DELETE FROM "+config.StaticProperty.getnoticedetail_tb()+" WHERE NOTICEDETAIL_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { }
	}
}
