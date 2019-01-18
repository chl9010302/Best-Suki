package BS;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestFrm extends JFrame{
	public TestFrm() {
		JPanel MainView = new JPanel();
		MainView.setLayout(new BorderLayout());
		JButton[] bt = new JButton[5];
		BorderLayout GL1 = new BorderLayout();
		MainView.setLayout(GL1);
		for(int i=0; i< bt.length;i++) {
			bt[i] = new JButton("Button" + i);
		}
		JLabel Username = new JLabel(LoginView.getUserText());
		MainView.add(Username,BorderLayout.NORTH);
		MainView.add(bt[1],BorderLayout.EAST);
		MainView.add(bt[2],BorderLayout.CENTER);
		MainView.add(bt[3],BorderLayout.SOUTH);
		MainView.add(bt[4],BorderLayout.WEST);
		setSize(Preference.WIDTH_SIZE, Preference.HEIGHT_SIZE);
		setLocation(Preference.LOCATION_WIDTH_SIZE, Preference.LOCATION_HEIGHT_SIZE);
		add(MainView);
		setVisible(true);
	}
}
