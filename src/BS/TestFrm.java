package BS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TestFrm extends JFrame{
	public TestFrm() {
		JPanel MainView = new JPanel();
		setSize(Preference.WIDTH_SIZE, Preference.HEIGHT_SIZE);
		MainView.setBackground(Color.WHITE);
		setLocation(Preference.LOCATION_WIDTH_SIZE, Preference.LOCATION_HEIGHT_SIZE);
		
		JButton[] bt1 = new JButton[3];
		
		BorderLayout BL1 = new BorderLayout();
		MainView.setLayout(BL1);
		BL1.setVgap(5);
		BL1.setHgap(5);
		bt1[0] = new JButton(Preference.TEST);
		bt1[1] = new JButton(Preference.VIDEO);
		bt1[2] = new JButton(Preference.STASTICS);
		JPanel EASTPanel = new JPanel();
		GridLayout GL1 = new GridLayout(10,1);
		GL1.setVgap(20);
		EASTPanel.setLayout(GL1);
		
		//Add button in EASTPanel
		for(int i = 0 ; i<3; i++) {
			EASTPanel.add(bt1[i]);
		}
		
		JPanel TestPanel = new JPanel(); // Create TestPanel
		TestPanel.setLayout(new GridLayout(1,1));
		String string = "This is Panel";
		JLabel Test = new JLabel(string);
		TestPanel.add(Test);
		
		JLabel Username = new JLabel(LoginView.getUserText(), SwingConstants.RIGHT); // JLabel 오른쪽 정렬
		
		MainView.add(TestPanel,BorderLayout.CENTER);
		MainView.add(Username,BorderLayout.NORTH);
		MainView.add(EASTPanel,BorderLayout.WEST);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Test.setText("This is " + bt1[0].getText() + " Panel"); // change PanelName
			}
		};
		bt1[0].addActionListener(listener);
		
		ActionListener listener2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Test.setText("This is " + bt1[1].getText() + " Panel"); // change PanelName
			}
		};
		bt1[1].addActionListener(listener2);
		
		ActionListener listener3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Test.setText("This is " + bt1[2].getText() + " Panel"); // change PanelName
			}
		};
		bt1[2].addActionListener(listener3);
		
		Username.setBorder(new EmptyBorder(0,0,0,10)); // JLabel내부 여백 설정
		Username.setFont(new Font("나눔고딕", Font.PLAIN, 15)); // JLabel 폰트,크기 설정
		
		
		
		
		add(MainView);
		setVisible(true);
		
		
		
	}
}
