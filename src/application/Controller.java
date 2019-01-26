package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import DBModel.UserBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
	private String UserGender = "";
	private UserBean user; // 회원가입 시 User 정보를 송신하기 위함.
	private Stage stage; // file choose 하기 위함.
	private ObservableList<String> listItems;
	public static String userId;
	
	@FXML
	private TextField UserId, UserPassword, UserName, UserAddress, 
						UserSchoolName, UserPhone, UserFmphone;
	@FXML private DatePicker UserAge;
	@FXML private Button Property_userID;
	@FXML private RadioButton UserGenderMale;
	@FXML private RadioButton UserGenderFeMale;
	@FXML private Text result;
	@FXML private Button BtnAdd;
	@FXML private Button BtnDelete;
	@FXML private ListView<String> listBoxMain;
	@FXML private TextField txtAddItem; 
	
	@FXML // Move SignupView
	private void NAV_SignUp(ActionEvent event) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource("../View/SignupView.fxml"));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
	
	@FXML // Move LoginView
	private void NAV_Login(ActionEvent event) throws IOException {
		Parent LoginView = FXMLLoader.load(getClass().getResource("../View/LoginView.fxml"));
		Scene LoginView_scene = new Scene(LoginView);
		LoginView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(LoginView_scene);
		app_stage.show();
	}
	
	@FXML // Move MainView
	private void NAV_Main(ActionEvent event) throws IOException {
		Parent MainView = FXMLLoader.load(getClass().getResource("../View/MainView.fxml"));
		Scene MainView_scene = new Scene(MainView);
		MainView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(MainView_scene);
		app_stage.show();
	}
	
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void NAV_Signup(ActionEvent event) throws IOException {
		LocalDate localDate = UserAge.getValue();
		// 회원가입 시 정보가 인터페이스됨.
		user = new UserBean();
		user.setUserId(UserId.getText().toString());
		user.setUserPassword(UserPassword.getText().toString());
		user.setUserName(UserName.getText().toString());
		user.setUserAddress(UserAddress.getText().toString());
		user.setUserSchoolName(UserSchoolName.getText().toString());
		user.setUserAge(localDate.toString());
		user.setUserGender(UserGender);
		user.setUserPhone(UserPhone.getText().toString());
		user.setUserFmphone(UserFmphone.getText().toString());
		
		
		// 회원가입과 함께 Login Page로 이동됨.
		Parent MainView = FXMLLoader.load(getClass().getResource("../View/LoginView.fxml"));
		Scene MainView_scene = new Scene(MainView);
		MainView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(MainView_scene);
		app_stage.show();
	}
	
	public void radioSelect(ActionEvent event) {
		if(UserGenderMale.isSelected()) { UserGender = UserGenderMale.getText(); }
		if(UserGenderFeMale.isSelected()) { UserGender = UserGenderFeMale.getText(); }
	}
	
	public void fileChooserSelect(ActionEvent event) { openFile(); }
	
	//fileChoose function
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) {
			System.out.println("File Chosen : " + file);
		}
	}
	
	  // Add event handlers
	  @FXML
	  private void addAction(ActionEvent action){
	    listItems.add(txtAddItem.getText());
	    System.out.println("동적 추가");
	    txtAddItem.clear();
	  }
	  
	  @FXML
	  private void deleteAction(ActionEvent action){
	    int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
	    listItems.remove(selectedItem);
	  }
	  
	  public void initialize(URL url, ResourceBundle rb) {
	    // TODO
		  try {
			  listItems = FXCollections.observableArrayList("First"); 
			  listBoxMain.setItems(listItems);
			  // Disable buttons to start
			  BtnAdd.setDisable(true);
			  BtnDelete.setDisable(true);
			  txtAddItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
				  public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					  if(txtAddItem.isFocused()){
						  BtnAdd.setDisable(false);
					  }
				  }
			  });    
			  
			  // Add a ChangeListener to ListView to look for change in focus
			  listBoxMain.focusedProperty().addListener(new ChangeListener<Boolean>() {
				  public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					  if(listBoxMain.isFocused()){
						  BtnDelete.setDisable(false);
					  }
				  }
			  });    
			  
		  }catch(Exception e) {  }
	  }  
	  
}
