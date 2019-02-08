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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import usingstaticfunction.DBConnectionKeeping;

public class NoticeDetailAdd {

	NoticeDetailBean noticedetailbean;
	Connection conn = null;
	Statement stmt = null;
	public static String noticedetail_id = "";
	private StringProperty noticedetail_id_pk;
	private StringProperty noticedetail_subtitle;
	private StringProperty noticedetail_writer;
	private StringProperty noticedetail_time;
	private StringProperty noticedetail_context;
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
	
	public NoticeDetailAdd() { }
	
	public NoticeDetailAdd(String NOTICEDETAIL_ID_PK, String NOTICEDETAIL_SUBTITLE, String NOTICEDETAIL_WRITER, String NOTICEDETAIL_TIME, String NOTICEDETAIL_CONTEXT) {
		this.noticedetail_id_pk = new SimpleStringProperty(NOTICEDETAIL_ID_PK);
		this.noticedetail_subtitle = new SimpleStringProperty(NOTICEDETAIL_SUBTITLE);
		this.noticedetail_writer = new SimpleStringProperty(NOTICEDETAIL_WRITER);
		this.noticedetail_time = new SimpleStringProperty(NOTICEDETAIL_TIME);
		this.noticedetail_context = new SimpleStringProperty(NOTICEDETAIL_CONTEXT);
		this.noticedetail_btndetail = new Button("Details");
		
		noticedetail_btndetail.setOnAction(event -> {
				noticedetail_id = "";
				noticedetail_id = noticedetail_id_pk.get();
				Parent SignupView = null;
				try {
					SignupView = FXMLLoader.load(getClass().getResource(config.StaticProperty.getnavmaindetailview()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene SignupView_scene = new Scene(SignupView);
				SignupView_scene.getStylesheets().add(getClass().getResource(config.StaticProperty.getnavapplication()).toExternalForm());
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(SignupView_scene);
				app_stage.show();
		});
	}

	public NoticeDetailAdd(NoticeDetailBean noticedetailbean) {
		insertNoticeDetail(noticedetailbean);
	}
	public boolean insertNoticeDetail(NoticeDetailBean noticedetailbean) {
		String insertsql = "INSERT INTO "+config.StaticProperty.getnoticedetail_tb()+"(NOTICEDETAIL_ID_PK, NOTICEDETAIL_SUBTITLE, NOTICEDETAIL_WRITER, NOTICEDETAIL_TIME, NOTICEDETAIL_CONTEXT) VALUES(?, ?, ?, now(), ?);";
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
	
	public boolean updateNoticeDetail(NoticeDetailBean noticedetailbean) {
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		
		Statement stmt = null;
		try {
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			stmt = con.createStatement();
			String updatesql = "UPDATE "+config.StaticProperty.getnoticedetail_tb()+" SET NOTICEDETAIL_SUBTITLE = '" + noticedetailbean.getNOTICEDETAIL_SUBTITLE() + "', NOTICEDETAIL_CONTEXT = '" + noticedetailbean.getNOTICEDETAIL_CONTEXT() + "' WHERE NOTICEDETAIL_ID_PK = '" + noticedetailbean.getNOTICEDETAIL_ID_PK() + "';";
			stmt.executeUpdate(updatesql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean select() {
		StringBuilder sb = new StringBuilder();
		try {
			conn = application.DBConnection.getDBConection();
			stmt = conn.createStatement();
			String sql = sb.append("SELECT * FROM "+config.StaticProperty.getnoticedetail_tb()).append(";").toString();
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
		String sql = sb.append("DELETE FROM "+config.StaticProperty.getnoticedetail_tb()+" where NOTICEDETAIL_ID_PK = '").append(id).append("';").toString();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String selectSubtitle(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT NOTICEDETAIL_SUBTITLE FROM "+config.StaticProperty.getnoticedetail_tb()+" where NOTICEDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("NOTICEDETAIL_SUBTITLE") != null)
					result = rs.getString("NOTICEDETAIL_SUBTITLE");
			}
		}catch(Exception e) { }
		return result;
	}
	
	public String selectContext(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT NOTICEDETAIL_CONTEXT FROM "+config.StaticProperty.getnoticedetail_tb()+" where NOTICEDETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("NOTICEDETAIL_CONTEXT") != null)
					result = rs.getString("NOTICEDETAIL_CONTEXT");
			}
		}catch(Exception e) { }
		return result;
	}
}
