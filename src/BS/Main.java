package BS;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {
	/**
	 * Academy Manament Program  
	 */
	private static final long serialVersionUID = 1L;

	public static void main (String[] args){
		new Main().setVisible(true);
	}
	
	private Main() {
		super("Tutorials - WTCHOI");
		setSize(600, 600); // 1024x768, 800x600
		setResizable(true);  // true - Users can be resizable
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JButton button = new JButton("Click Me");
		JButton butto2 = new JButton("ㅗㅗ");
		JButton butto3 = new JButton("ㅗㅗ3");
		JButton butto4 = new JButton("ㅗㅗ3");
		JButton butto5 = new JButton("ㅗㅗ3");
		add(label);
		JTextField text = new JTextField(5);
		add(text); // the button is visible
		add(button); // then button is visible
		add(butto2);
		add(butto3);
		add(butto5);
		add(butto4);
	}
}