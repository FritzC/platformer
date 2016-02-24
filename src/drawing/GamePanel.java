package drawing;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseWheelListener {

	@Override
	public void paint(Graphics g) {
		g.drawRect(1, 1, 100, 100);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
