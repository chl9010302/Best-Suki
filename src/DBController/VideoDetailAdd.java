package DBController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBModel.VideoDetailBean;
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

public class VideoDetailAdd {

	VideoDetailBean videodetailbean;
	Connection conn = null;
	Statement stmt = null;
	public static String videodetail_id = "";
	private StringProperty videodetail_id_pk;
	private StringProperty videodetail_subtitle;
	private StringProperty videodetail_writer;
	private StringProperty videodetail_time;
	private StringProperty videodetail_filepath;
	private Button videodetail_btndetail;

	public StringProperty getVideodetail_id_pk() {
		return videodetail_id_pk;
	}

	public StringProperty getVideodetail_subtitle() {
		return videodetail_subtitle;
	}

	public StringProperty getVideodetail_writer() {
		return videodetail_writer;
	}

	public StringProperty getVideodetail_time() {
		return videodetail_time;
	}

	public StringProperty getVideodetail_filepath() {
		return videodetail_filepath;
	}

	public Button getVideodetail_btndetail() {
		return videodetail_btndetail;
	}

	public void setvideodetail_btndetail(Button videodetail_btndetail) {
		this.videodetail_btndetail = videodetail_btndetail;
	}

	private ObservableList<VideoDetailAdd> videodetailadd = FXCollections.observableArrayList();
	
	public ObservableList<VideoDetailAdd> getvideodetailadd() {
		select();
		return videodetailadd;
	}
	
	public VideoDetailAdd() { }
	
	public VideoDetailAdd(String VIDEODETAIL_ID_PK, String VIDEODETAIL_SUBTITLE, String VIDEODETAIL_WRITER, String VIDEODETAIL_TIME, String VIDEODETAIL_FILEPATH) {
		this.videodetail_id_pk = new SimpleStringProperty(VIDEODETAIL_ID_PK);
		this.videodetail_subtitle = new SimpleStringProperty(VIDEODETAIL_SUBTITLE);
		this.videodetail_writer = new SimpleStringProperty(VIDEODETAIL_WRITER);
		this.videodetail_time = new SimpleStringProperty(VIDEODETAIL_TIME);
		this.videodetail_filepath = new SimpleStringProperty(VIDEODETAIL_FILEPATH);
		this.videodetail_btndetail = new Button("Details");
		
		videodetail_btndetail.setOnAction(event -> {
				videodetail_id = ""; // 초기화
				videodetail_id = videodetail_id_pk.get();
				Parent SignupView = null;
				try {
					SignupView = FXMLLoader.load(getClass().getResource("../View/VideoDetailView.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene SignupView_scene = new Scene(SignupView);
				SignupView_scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(SignupView_scene);
				app_stage.show();
		});
	}

	public VideoDetailAdd(VideoDetailBean videodetailbean) {
		insertVideodetail(videodetailbean);
	}
	public boolean updateVideoDetail(VideoDetailBean videodetailbean) {
		DBConnectionKeeping dbConnectionKeeping;
		if (usingstaticfunction.DBConnectionKeeping.con == null)
			dbConnectionKeeping = new DBConnectionKeeping();
		
		Statement stmt = null;
		try {
			Connection con = usingstaticfunction.DBConnectionKeeping.con;
			stmt = con.createStatement();
			String updatesql = "UPDATE "+config.StaticProperty.getvideodetail_tb()+" SET VIDEODETAIL_SUBTITLE = '" + videodetailbean.getVIDEODETAIL_SUBTITLE() + "', VIDEODETAIL_FILEPATH = '" + videodetailbean.getVIDEODETAIL_FILEPATH() + "' WHERE VIDEODETAIL_ID_PK = '" + videodetailbean.getVIDEODETAIL_ID_PK() + "';";
			stmt.executeUpdate(updatesql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean insertVideodetail(VideoDetailBean videodetailbean) {
		String insertsql = "INSERT INTO "+config.StaticProperty.getvideodetail_tb()+"(VIDEODETAIL_ID_PK, VIDEODETAIL_SUBTITLE, VIDEODETAIL_WRITER, VIDEODETAIL_TIME, VIDEODETAIL_FILEPATH) VALUES(?, ?, ?, now(), ?);";
		PreparedStatement pstmt = null;
		this.videodetailbean = videodetailbean;
		try {
			conn = application.DBConnection.getDBConection();	
			pstmt = conn.prepareStatement(insertsql);
			pstmt.setString(1, videodetailbean.getVIDEODETAIL_ID_PK());
			pstmt.setString(2, videodetailbean.getVIDEODETAIL_SUBTITLE());
			pstmt.setString(3, videodetailbean.getVIDEODETAIL_WRITER());
			pstmt.setString(4, videodetailbean.getVIDEODETAIL_FILEPATH());
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
			String sql = sb.append("SELECT * FROM "+config.StaticProperty.getvideodetail_tb()).append(";").toString();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				videodetailadd.add(new VideoDetailAdd((String) rs.getString("VIDEODETAIL_ID_PK"), (String) rs.getString("VIDEODETAIL_SUBTITLE"), (String) rs.getString("VIDEODETAIL_WRITER"),(String) rs.getString("VIDEODETAIL_TIME").substring(0,10), rs.getString("VIDEODETAIL_FILEPATH")));
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
		String sql = sb.append("DELETE FROM "+config.StaticProperty.getvideodetail_tb()+" WHERE VIDEODETAIL_ID_PK = '").append(id).append("';").toString();
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
			String sql = sb.append("SELECT VIDEODETAIL_SUBTITLE FROM "+config.StaticProperty.getvideodetail_tb()+" WHERE VIDEODETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("VIDEODETAIL_SUBTITLE") != null)
					result = rs.getString("VIDEODETAIL_SUBTITLE");
			}
		}catch(Exception e) { }
		return result;
	}
	
	public String selectFilepath(String id) {
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			conn = application.DBConnection.getDBConection();
			String sql = sb.append("SELECT VIDEODETAIL_FILEPATH FROM "+config.StaticProperty.getvideodetail_tb()+" WHERE VIDEODETAIL_ID_PK = '").append(id).append("';").toString();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("VIDEODETAIL_FILEPATH") != null)
					result = rs.getString("VIDEODETAIL_FILEPATH");
			}
		}catch(Exception e) { }
		return result;
	}
}