package BS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame{
	private Main main;
	private JButton btnLogin;
	private JPasswordField passText;
	private static JTextField userText;
	private boolean bLoginCheck;
	
	public static void main(String[] args) { }
	
	public LoginView() {
		// Login View Setting
		setTitle(Preference.TITLE_LOGIN);
		setSize(Preference.WIDTH_SIZE, Preference.HEIGHT_SIZE);
		setResizable(true); // LoginView is Resizable
		setLocation(Preference.LOCATION_WIDTH_SIZE, Preference.LOCATION_HEIGHT_SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // LoginView can be closed if CloseOperation is worked
		JPanel panel = new JPanel(); // create panel
		placeLoginPanel(panel);
		add(panel); // Add panel ondLoginView
		setVisible(true); //LoginView is visible
	}
	
	public void placeLoginPanel(JPanel panel){
		panel.setLayout(null);
		panel.setBackground(Color.white);
		//userLabel
		JLabel userLabel = new JLabel(Preference.USER_ID);
		userLabel.setBounds(110,210,80,25);
		panel.add(userLabel);
		//passLabel
		JLabel passLabel = new JLabel(Preference.USER_PASSWORD);
		passLabel.setBounds(110,240,80,25);
		panel.add(passLabel);
		//userText
		userText = new JTextField(20);
		userText.setBounds(200,210,160,25);
		panel.add(userText);
		//passText
		passText = new JPasswordField(20);
		passText.setBounds(200,240,160,25);
		//Execute addActionListener on JPasswordField if Enter is pressed
		passText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
		panel.add(passText);
		//Button of Login
		btnLogin = new JButton(Preference.TITLE_LOGIN);
		btnLogin.setBounds(260,280,100,25);
		panel.add(btnLogin);
		//Check userID+userPassword
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
	}	
	// login ID와 Password를 체크하여 성공하면 SUCCESS + bLoginCheck, 실패하면 FAIL
	public void isLoginCheck() {
		if(userText.getText().equals("temp") && new String(passText.getPassword()).equals("1234")) {
			JOptionPane.showMessageDialog(null, Preference.MESSAGE_SUCCESS);
			bLoginCheck = true; // 로그인 됬음을 알려주는 boolean 값
			
			// 로그인 성공이라면 매니져창 뛰우기
			if(isLogin()) {
				main.showFrameTest();
			}
		}else{
			JOptionPane.showMessageDialog(null, Preference.MESSAGE_FAIL);
		}
	}
	public void setMain(Main main) {
		this.main = main;
	}

	public boolean isLogin() {
		return bLoginCheck;
	}
	public static String getUserText() {
		return userText.getText();
	}
}
