package application;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DBController.AddBoard;
import DBController.UserJoin;
import DBController.UserLongin;
import DBModel.BoardBean;
import DBModel.UserBean;
import ImageStore.TestImageStore;
import academyutil.Sha256;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
	//Declare JAVA
	private String UserGender = "";
	private UserBean user; // 회원가입 시 User 정보를 송신하기 위함.
	private BoardBean board; // 게시판 보내
	private Stage stage; // file choose 하기 위함.
	private UserLongin login;
	private ObservableList<String> listItems;
	public static String userId;
	public static String fileName;
	public static String filePath;
	Sha256 sha256 = new Sha256();
	
	
	//Declare FXML
	@FXML private TextField UserId, UserPassword, UserPasswordConfirm, UserName, UserAddress, UserSchoolName, UserPhone, UserFmphone;
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
	@FXML private ListView<String> listBoxMain;
	@FXML private TableView<AddBoard> testTableView;
	@FXML private TableColumn<AddBoard, String> ColBoardId;
	@FXML private TableColumn<AddBoard, String> ColSubtitle;
	@FXML private TextField txtAddItem; 
	@FXML private TextField txtSubtitle; 
	@FXML private Label txtFilepath;
	@FXML private void NAV_SignUpView(ActionEvent event) throws IOException { NAV(event, "../View/SignupView.fxml"); }
	@FXML private void NAV_LoginView(ActionEvent event) throws IOException { NAV(event, "../View/LoginView.fxml"); }
	@FXML private void NAV_MainView(ActionEvent event) throws IOException { 

	}
	@FXML private void NAV_TestView(ActionEvent event) throws IOException { NAV(event, "../View/TestView.fxml"); }
	@FXML private void NAV_TestBoardView(ActionEvent event) throws IOException { NAV(event, "../View/TestBoardView.fxml"); }
	@FXML private void NAV_AddTestView(ActionEvent event) throws IOException { NAV_POPUP(event, "../View/AddTestView.fxml"); }
	@FXML // 회원가입 버튼 클릭 시 활성화
	private void login(ActionEvent event) {
		try {
			int i = login.loginCheck(UserId.getText().toString(), sha256.sha256(UserPassword.getText()));
			if(i == 1)
				NAV(event, "../View/MainView.fxml");
			else
				System.out.println("실패시 뷰 구현해주세요");
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
	private void Signup(ActionEvent event) throws IOException, NoSuchAlgorithmException {
		LocalDate localDate = UserAge.getValue();
		// 회원가입 시 정보가  인터페이스됨.
		user = new UserBean();
		user.setUserId(UserId.getText().toString());
		user.setUserPassword(sha256.sha256(UserPassword.getText()));
		user.setUserName(UserName.getText().toString());
		user.setUserAddress(UserAddress.getText().toString());
		user.setUserSchoolName(UserSchoolName.getText().toString());
		user.setUserAge(localDate.toString());
		user.setUserGender(UserGender);
		user.setUserPhone(UserPhone.getText().toString());
		user.setUserFmphone(UserFmphone.getText().toString());
		
		UserJoin join = new UserJoin(user);
		// 회원가입과 함께 Login Page로 이동됨.
		
		
		NAV(event, "../View/LoginView.fxml");
	}
	@FXML
	private void addAction(ActionEvent action){
		listItems.add(txtAddItem.getText());
	}
	@FXML
	private void deleteAction(ActionEvent action){
	  int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
	  listItems.remove(selectedItem);
	}
	@FXML
	private void saveAction(ActionEvent action) {
		board = new BoardBean();
		AddBoard addboard = new AddBoard();
		board.setBoardId(String.valueOf(addboard.lastselect()));
		board.setSubtitle(txtSubtitle.getText().toString());
		board.setFilepath(filePath);
		board.setRadio1(Radio1.getText().toString());
		board.setRadio2(Radio2.getText().toString());
		board.setRadio3(Radio3.getText().toString());
		board.setRadio4(Radio4.getText().toString());
		board.setRadio5(Radio5.getText().toString());
//		Quest1Group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//			public void changed(ObservableValue<? extends Toggle> observable, Toggle old_toggle, Toggle new_toggle) {
//				System.out.println("qwewqe");
//				if(Quest1Group1.getSelectedToggle() != null) {
//					System.out.println("qwrwqr : " + Quest1Group1.getSelectedToggle().toString());
//				}
//			}
//		});
		if(Rb1.isSelected()) {
			board.setFlag(Radio1.getText().toString());
		}else if(Rb2.isSelected()) {
			board.setFlag(Radio2.getText().toString());
		}else if(Rb3.isSelected()) {
			board.setFlag(Radio3.getText().toString());
		}else if(Rb4.isSelected()) {
			board.setFlag(Radio4.getText().toString());
		}else {
			board.setFlag(Radio5.getText().toString());
		}
	    ((Stage) ((Node) action.getSource()).getScene().getWindow()).close(); // 창 닫음.
	    AddBoard Excute = new AddBoard(board);
	    
	}
	@FXML
	private void Quest1Group1Action(ActionEvent action) {
		System.out.println(Quest1Group1.getSelectedToggle().toString());
	}
	@FXML
	private void removeAction(ActionEvent action){
		AddBoard addboard = new AddBoard();
	  int selectedItem = testTableView.getSelectionModel().getSelectedIndex();
	  addboard.delete(Integer.parseInt(testTableView.getItems().get(selectedItem).getarraylist().getValue()));
	  testTableView.setItems(addboard.getaddboard());
	}
	@FXML
	private void modify(ActionEvent action) {
	}
	
	public void radioSelect(ActionEvent action) {
		if(UserGenderMale.isSelected()) { UserGender = UserGenderMale.getText(); }
		if(UserGenderFeMale.isSelected()) { UserGender = UserGenderFeMale.getText(); }
	}
	//fileChoose function
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		if(file != null) {
			fileName = file.getName();
			String Address = file.toString().replaceAll("\\\\", "//");
			new TestImageStore("112233", Address); 
			filePath = Address;
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
			  AddBoard addboard = new AddBoard();
			  ColBoardId.setCellValueFactory(cellData -> cellData.getValue().getarraylist());
			  ColSubtitle.setCellValueFactory(cellData -> cellData.getValue().getarraylist2());
			  testTableView.setItems(addboard.getaddboard());
		  }catch(Exception e) {}
	  }
	public void fileChooserSelect(ActionEvent event) { 
		openFile(); 
		txtFilepath.setText(fileName);
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
