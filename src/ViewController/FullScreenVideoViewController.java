package ViewController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class FullScreenVideoViewController implements Initializable{
    @FXML WebView fullscreenview;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
			fullscreenview.getEngine().load(
					ViewController.VideoDetailViewController.URL
					);
			fullscreenview.setPrefSize(res.getWidth(), res.getHeight()*0.83);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}