package drawing;

import javax.swing.JFrame;

public class Frame {

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		GamePanel panel = new GamePanel();
		frame.setContentPane(panel);
		frame.setSize(750, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
