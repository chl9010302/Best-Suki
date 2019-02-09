package ViewController;

import java.io.IOException;
import java.util.Optional;

import DBController.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CommonController{
	public static void NAV (Class getclass, ActionEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getclass.getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	
	public static void NAV_Key (Class getclass, KeyEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getclass.getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	
	public static void NAV_POPUP (Class getclass, ActionEvent event, String str) throws IOException {
		Parent View = FXMLLoader.load(getclass.getResource(str));
		Scene View_scene = new Scene(View);
		View_scene.getStylesheets().add(getclass.getResource(config.StaticProperty.getnavapplication()).toExternalForm());
		Stage app_stage = new Stage();
		app_stage.setScene(View_scene);
		app_stage.show();
	}
	
	public static void logout(ActionEvent event, Class getclass) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType(config.StaticProperty.alertbtnyes(), ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType(config.StaticProperty.alertbtnno(), ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,config.StaticProperty.alertlogout(), YES, NO);
		alert.setTitle(config.StaticProperty.alerttitlelogout());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(LoginViewController.login_id);
				userlogout.logout2(LoginViewController.login_id);
				NAV(getclass, event, config.StaticProperty.getnavloginview());
			}catch(Exception e) { }
		}
	}
}
