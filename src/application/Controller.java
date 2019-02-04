package application;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import DBController.AddStastics;
import DBController.SelectNowUser;
import DBController.TestDetailAdd;
import DBController.UserDataUpdate;
import DBController.UserJoin;
import DBController.UserLogin;
import DBModel.TestDetailBean;
import DBModel.UserBean;
import ImageStore.TestImageStore;
import academyutil.Sha256;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//참고용 Controller
public class Controller implements Initializable {
	//Declare JAVA
	private String usergender = "";
	private UserBean user; // 회원가입 시 User 정보를 송신하기 위함.
	private TestDetailBean testdetailbean;
	private Stage stage; // file choose 하기 위함.
	public static String userId;
	public static String filename;
	public static String filepath;
	public static String login_id="";
	Sha256 sha256 = new Sha256();
	//Declare FXML
	@FXML private TextField UserId, UserPassword, UserPasswordConfirm, UserName, UserAddress, UserSchoolName, UserPhone, UserFmphone;
	@FXML private Label Mypage_UserId, Mypage_UserPassword, Mypage_UserName,  Mypage_UserAddress, Mypage_UserSchoolName, Mypage_UserAge, Mypage_UserGender, Mypage_UserPhone, Mypage_UserFmphone;
	@FXML private TextField EditProperty_UserId, EditProperty_UserPassword, EditProperty_UserName,  EditProperty_UserAddress, EditProperty_UserSchoolName, EditProperty_UserAge, EditProperty_UserGender, EditProperty_UserPhone, EditProperty_UserFmphone;
	@FXML private TextField Radio1, Radio2, Radio3, Radio4, Radio5;
	@FXML private RadioButton Rb1, Rb2, Rb3, Rb4, Rb5;
	@FXML private DatePicker UserAge;
	@FXML private Button Property_userID;
	@FXML private RadioButton UserGenderMale;
	@FXML private RadioButton UserGenderFeMale;
	@FXML private ToggleGroup Quest1Group1;
	@FXML private Text result;
	@FXML private Button BtnSave;
	@FXML private Button BtnAdd;
	@FXML private Button BtnDelete;
	@FXML private Button TestButton;
	@FXML private ListView<String> listBoxMain;
	@FXML private TableView<TestDetailAdd> testTableView;
	@FXML private TableColumn<TestDetailAdd, String> ColBoardId;
	@FXML private TableColumn<TestDetailAdd, String> ColSubtitle;
	@FXML private TableView<AddStastics> StasticsView;
	@FXML private TableColumn<AddStastics, String> USER_ID;
	@FXML private TableColumn<AddStastics, String> USER_LOGIN_DATE;
	@FXML private TableColumn<AddStastics, String> USER_LOGOUT_DATE;
	@FXML private TextField txtAddItem; 
	@FXML private TextField txtSubtitle; 
	@FXML private Label txtFilepath;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { NAV(event, "../View/SignupView.fxml"); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, "../View/LoginView.fxml"); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { NAV(event, "../View/MainView.fxml");	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, "../View/TestView.fxml"); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, "../View/TestBoardView.fxml"); }
	@FXML private void NAV_AddTestView(ActionEvent event) throws IOException { NAV_POPUP(event, "../View/AddTestView.fxml"); }
	@FXML private void NAV_StasticsView(ActionEvent event) throws IOException { NAV(event, "../View/StasticsView.fxml"); }
	@FXML private void NAV_MypageView(ActionEvent event) throws IOException { NAV(event, "../View/MypageView.fxml"); }
	@FXML private void NAV_MypageEditView(ActionEvent event) throws IOException { NAV(event, "../View/MypageEditView.fxml"); }
	@FXML private void NAV_VideoView(ActionEvent event) throws IOException { NAV(event, "../View/VideoView.fxml"); }
	@FXML
	private void ButtonTest(ActionEvent event) {
		sung(event);
	}
	public void sung(ActionEvent event) { 
		try {
		SelectNowUser selectnowuser = new SelectNowUser();
		UserBean userbean;
		userbean  = selectnowuser.getSelectUser(login_id);
		Mypage_UserId.setText(userbean.getUSER_ID_PK());
		Mypage_UserPassword.setText(userbean.getUSER_PASSWORD());
		Mypage_UserName.setText(userbean.getUSER_NAME());
		Mypage_UserAddress.setText(userbean.getUSER_ADDRESS());
		Mypage_UserSchoolName.setText(userbean.getUSER_SCHOOLNAME());
		Mypage_UserAge.setText(userbean.getUSER_AGE());
		Mypage_UserGender.setText(userbean.getUSER_GENDER());
		Mypage_UserPhone.setText(userbean.getUSER_PHONE());
		Mypage_UserFmphone.setText(userbean.getUSER_FMPHONE());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@FXML
	private void ButtonTest2(ActionEvent event) {
		tuk(event);
	}
	public void tuk(ActionEvent event) { 
		try {
		SelectNowUser selectnowuser = new SelectNowUser();
		UserBean userbean;
		userbean  = selectnowuser.getSelectUser(login_id);
		EditProperty_UserId.setText(userbean.getUSER_ID_PK());
		EditProperty_UserPassword.setText(userbean.getUSER_PASSWORD());
		EditProperty_UserName.setText(userbean.getUSER_NAME());
		EditProperty_UserAddress.setText(userbean.getUSER_ADDRESS());
		EditProperty_UserSchoolName.setText(userbean.getUSER_SCHOOLNAME());
		EditProperty_UserAge.setText(userbean.getUSER_AGE());
		EditProperty_UserGender.setText(userbean.getUSER_GENDER());
		EditProperty_UserPhone.setText(userbean.getUSER_PHONE());
		EditProperty_UserFmphone.setText(userbean.getUSER_FMPHONE());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@FXML
	public void ButtonTest3(ActionEvent event) {
		UserBean userbean = new UserBean();
		userbean.setUSER_ID_PK(EditProperty_UserId.getText().toString());
		userbean.setUSER_PASSWORD(EditProperty_UserPassword.getText().toString());
		userbean.setUSER_NAME(EditProperty_UserName.getText().toString());
		userbean.setUSER_ADDRESS(EditProperty_UserAddress.getText().toString());
		userbean.setUSER_SCHOOLNAME(EditProperty_UserSchoolName.getText().toString());
		userbean.setUSER_AGE(EditProperty_UserAge.getText().toString());
		userbean.setUSER_GENDER(EditProperty_UserGender.getText().toString());
		userbean.setUSER_PHONE(EditProperty_UserPhone.getText().toString());
		userbean.setUSER_FMPHONE(EditProperty_UserFmphone.getText().toString());
		UserDataUpdate userdataupdate = new UserDataUpdate();
		userdataupdate.UserUpdate(userbean, EditProperty_UserId.getText().toString());
	}
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void login(ActionEvent event) {
		UserLogin userlogin = new UserLogin();
		try {
			int i = userlogin.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
			login_id = UserId.getText().toString();
			if(i == 1) {
				NAV(event, "../View/MainView.fxml");
				
			}
			else {
				login_id ="";
				Alert alert = new Alert(AlertType.NONE);
				alert.setTitle("Message Here...");
				alert.setHeaderText("로그인에 실패하셨습니다.");
				alert.showAndWait();
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void logout(ActionEvent event) {
		UserLogin userlogout = new UserLogin();
		ButtonType YES = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
		ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.NONE,"Would you want to logout?", YES, NO);
		alert.setTitle("Logout");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.orElse(NO) == YES) {
			try {
				userlogout.logout(login_id);
				userlogout.logout2(login_id);
				NAV(event, "../View/LoginView.fxml");
			}catch(Exception e) { }
		}
	}
	@FXML
	private void Signup(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		LocalDate localDate = UserAge.getValue();
		// 회원가입 시 정보가  인터페이스됨.
		user = new UserBean();
		user.setUSER_ID_PK(UserId.getText().toString());
		user.setUSER_PASSWORD(sha256.sha256(UserPassword.getText()));
		user.setUSER_NAME(UserName.getText().toString());
		user.setUSER_ADDRESS(UserAddress.getText().toString());
		user.setUSER_SCHOOLNAME(UserSchoolName.getText().toString());
		user.setUSER_AGE(localDate.toString());
		user.setUSER_GENDER(usergender);
		user.setUSER_PHONE(UserPhone.getText().toString());
		user.setUSER_FMPHONE(UserFmphone.getText().toString());
		
		UserJoin join = new UserJoin();
		join.insert(user);
		// 회원가입과 함께 Login Page로 이동됨.
		
		
		NAV(event, "../View/LoginView.fxml");
	}
	
	
	@FXML
	private void addAction(ActionEvent action){
	}
	@FXML
	private void deleteAction(ActionEvent action){
//	  int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
	}
	
	@FXML
	private void saveAction(ActionEvent action) {
		testdetailbean = new TestDetailBean();
		testdetailbean.setTESTDETAIL_ID_PK(usingstaticfunction.TestDetailFunction.makeTestDetailKey(txtSubtitle.getText().toString()));
		testdetailbean.setTESTDETAIL_SUBTITLE(txtSubtitle.getText().toString());
		testdetailbean.setTESTDETAIL_IMAGE(filepath);
		testdetailbean.setTESTDETAIL_DATA1(Radio1.getText().toString());
		testdetailbean.setTESTDETAIL_DATA2(Radio2.getText().toString());
		testdetailbean.setTESTDETAIL_DATA3(Radio3.getText().toString());
		testdetailbean.setTESTDETAIL_DATA4(Radio4.getText().toString());
		testdetailbean.setTESTDETAIL_DATA5(Radio5.getText().toString());

		if(Rb1.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio1.getText().toString());
		}else if(Rb2.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio2.getText().toString());
		}else if(Rb3.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio3.getText().toString());
		}else if(Rb4.isSelected()) {
			testdetailbean.setTESTDETAIL_ANSWER(Radio4.getText().toString());
		}else {
			testdetailbean.setTESTDETAIL_ANSWER(Radio5.getText().toString());
		}
	    ((Stage) ((Node) action.getSource()).getScene().getWindow()).close(); // 창 닫음.
	    TestDetailAdd detailAdd = new TestDetailAdd();
	    detailAdd.insertTestDetail(testdetailbean);
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
		System.out.println(Quest1Group1.getSelectedToggle().toString());
	}
	@FXML
	private void removeAction(ActionEvent action){
		TestDetailAdd testdetailadd = new TestDetailAdd();
	  int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
	  testdetailadd.delete(String.valueOf((testTableView.getItems().get(selectedItem).getTestdetail_id_pk().getValue())));
	  testTableView.setItems(testdetailadd.gettestdetailadd());
	}
	@FXML
	private void modify(ActionEvent action) {
	}
	
	public void radioSelect(ActionEvent action) {
		if(UserGenderMale.isSelected()) { usergender = UserGenderMale.getText(); }
		if(UserGenderFeMale.isSelected()) { usergender = UserGenderFeMale.getText(); }
	}
	//fileChoose function
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) {
			filename = file.getName();
			String Address = file.toString().replaceAll("\\\\", "//");
			new TestImageStore("112233", Address); 
			filepath = Address;
		}
	}
	  public void initialize(URL url, ResourceBundle rb) {
	    // TODO
		  try {
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
		  
		  try {
			  AddStastics stasticsview = new AddStastics();
			  USER_ID.setCellValueFactory(cellData -> cellData.getValue().getUSER_ID());
			  USER_LOGIN_DATE.setCellValueFactory(cellData -> cellData.getValue().getUSER_LOGIN_DATE());
			  USER_LOGOUT_DATE.setCellValueFactory(cellData -> cellData.getValue().getUSER_LOGOUT_DATE());
			  StasticsView.setItems(stasticsview.getstastics());
		  }catch(Exception e) {}
		  
		  try {
			  TestDetailAdd testdetailadd = new TestDetailAdd();
			  ColBoardId.setCellValueFactory(cellData -> cellData.getValue().getTestdetail_id_pk());
			  ColSubtitle.setCellValueFactory(cellData -> cellData.getValue().getSubtitle());
			  testTableView.setItems(testdetailadd.gettestdetailadd());
		  }catch(Exception e) {}
	  }
	public void fileChooserSelect(ActionEvent event) { 
		openFile(); 
		txtFilepath.setText(filename);
	}
	private void NAV (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
	private void NAV_POPUP (ActionEvent event, String str) throws IOException {
		Parent SignupView = FXMLLoader.load(getClass().getResource(str));
		Scene SignupView_scene = new Scene(SignupView);
		SignupView_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage app_stage = new Stage();
		app_stage.setScene(SignupView_scene);
		app_stage.show();
	}
}
