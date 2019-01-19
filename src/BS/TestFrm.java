package BS;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestFrm extends JFrame{
	public TestFrm() {
		JPanel MainView = new JPanel();
		MainView.setLayout(new BorderLayout());
		JButton[] bt = new JButton[5];
		JButton[] bt1 = new JButton[5];
		BorderLayout BL1 = new BorderLayout();
		BL1.setVgap(5);
		BL1.setHgap(5);
		MainView.setLayout(BL1);
		for(int i=0; i< bt.length;i++) {
			bt[i] = new JButton("Button" + i);
		}
		for(int i=0; i< bt.length;i++) {
			bt1[i] = new JButton("Button" + i);
		}
		JPanel EASTPanel = new JPanel();
		EASTPanel.setLayout(new GridLayout());
		GridLayout GL1 = new GridLayout(4,1);
		GL1.setVgap(50);
		EASTPanel.setLayout(GL1);
		EASTPanel.add(bt1[1]);
		EASTPanel.add(bt1[2]);
		EASTPanel.add(bt1[3]);
		EASTPanel.add(bt1[4]);
		
		JLabel Username = new JLabel(LoginView.getUserText());
		MainView.add(Username,BorderLayout.NORTH);
		MainView.add(bt[1],BorderLayout.EAST);
		MainView.add(bt[2],BorderLayout.CENTER);
		MainView.add(bt[3],BorderLayout.SOUTH);
		MainView.add(EASTPanel,BorderLayout.WEST);
		setSize(Preference.WIDTH_SIZE, Preference.HEIGHT_SIZE);
		setLocation(Preference.LOCATION_WIDTH_SIZE, Preference.LOCATION_HEIGHT_SIZE);
		add(MainView);
		setVisible(true);
	}
}
