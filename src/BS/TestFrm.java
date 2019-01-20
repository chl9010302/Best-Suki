package BS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
		MainView.setLayout(new BorderLayout());
		JButton[] bt = new JButton[5];
		JButton[] bt1 = new JButton[3];
		BorderLayout BL1 = new BorderLayout();
		BL1.setVgap(5);
		BL1.setHgap(5);
		MainView.setLayout(BL1);
		for(int i=0; i< bt.length;i++) {
			bt[i] = new JButton("Button" + i);
		}
		bt1[0] = new JButton("Test");
		bt1[1] = new JButton("Video");
		bt1[2] = new JButton("Stastics");
		JPanel EASTPanel = new JPanel();
		GridLayout GL1 = new GridLayout(3,1);
		GL1.setVgap(50);
		EASTPanel.setLayout(GL1);
		EASTPanel.add(bt1[0]);
		EASTPanel.add(bt1[1]);
		EASTPanel.add(bt1[2]);
		
		JPanel TestPanel = new JPanel(); // Create TestPanel
		GridLayout FL1 = new GridLayout(1,1);
		TestPanel.setLayout(FL1);
		String string = "This is TestPanel";
		String string2 = "This is TestPanel2";
		JLabel Test = new JLabel(string);
		TestPanel.add(Test);
		
		JPanel VideoPanel = new JPanel();
		VideoPanel.setLayout(new FlowLayout());
		JLabel Video = new JLabel("This is VideoPanel");
		VideoPanel.add(Video);
		
		JPanel StasticsPanel = new JPanel();
		StasticsPanel.setLayout(new FlowLayout());
		JLabel Stastics = new JLabel("This is Stastics");
		StasticsPanel.add(Stastics);
		
		MainView.add(TestPanel,BorderLayout.CENTER);
		JLabel Username = new JLabel(LoginView.getUserText(), SwingConstants.RIGHT); // JLabel 오른쪽 정렬
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Test.setText(string2);
			}
		};
		bt1[0].addActionListener(listener);
		
		
		Username.setBorder(new EmptyBorder(0,0,0,10)); // JLabel내부 여백 설정
		Username.setFont(new Font("나눔고딕", Font.PLAIN, 15)); // JLabel 폰트,크기 설정
		MainView.add(Username,BorderLayout.NORTH);
		
		MainView.add(EASTPanel,BorderLayout.WEST);
		setSize(Preference.WIDTH_SIZE, Preference.HEIGHT_SIZE);
		setLocation(Preference.LOCATION_WIDTH_SIZE, Preference.LOCATION_HEIGHT_SIZE);
		add(MainView);
		setVisible(true);
		
		
		
	}
}
