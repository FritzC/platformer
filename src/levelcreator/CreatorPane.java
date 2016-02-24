package levelcreator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CreatorPane extends JPanel implements MouseWheelListener, MouseListener {
	
	private int scale = 20;
	
	private int dragX = -1, dragY = -1, dragType = -1;
	private int offX = 0, offY = 0;
	
	private int activeLayer = Level.BASE_LAYER;
	
	private Level level;
	
	private BaseLayerSettings baseSettings;

	public CreatorPane(Level level, BaseLayerSettings baseSettings) {
		addMouseWheelListener(this);
		addMouseListener(this);
		this.level = level;
		this.baseSettings = baseSettings;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		drawGrid(g);
	}
	
	public void drawGrid(Graphics g) {
		for (int x = 0; x <= level.width; x ++) {
			g.drawLine(x * scale + offX, 0 + offY, x * scale + offX, level.height * scale + offY);
		}
		for (int y = 0; y <= level.height; y++) {
			g.drawLine(0 + offX, y * scale + offY, level.width * scale + offX, y * scale + offY);
		}
		level.drawLevel(new Point(offX, offY), getMousePosition(), scale, 0, g);
		if (dragX > -1 && dragY > -1) {
			Point mousePos = getMousePosition();
			if (mousePos != null) {
				switch (dragType) {
				case 1:
					offX += (int) (getMousePosition().getX() - dragX);
					offY += (int) (getMousePosition().getY() - dragY);
					dragX = (int) getMousePosition().getX();
					dragY = (int) getMousePosition().getY();
					break;
				case 2:
					int xMove = (int) ((getMousePosition().getX() - dragX) / scale);
					int yMove = (int) ((getMousePosition().getY() - dragY) / scale);
					if (xMove != 0) {
						for (int i = 0; i < level.selectedShapes.size(); i++) {
							level.selectedShapes.get(i).moveShape(xMove, 0);
							dragX = (int) getMousePosition().getX();
						}
					}
					if (yMove != 0) {
						for (int i = 0; i < level.selectedShapes.size(); i++) {
							level.selectedShapes.get(i).moveShape(0, yMove);
							dragY = (int) getMousePosition().getY();
						}
					}
					break;
				}
			} else
				dragX = dragY = dragType = -1;
		}
		repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation() * -5;
		scale += notches;
		if (scale <= 1)
			scale = 2;
		if (scale > 100)
			scale = 100;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isShiftDown() || SwingUtilities.isMiddleMouseButton(e)) {
			dragType = 1;
			switch (activeLayer) {
			case Level.BASE_LAYER:
				for (GridShape shape : level.shapes)
					if (shape.getPoly(new Point(offX, offY), scale).contains(
							e.getPoint())
							&& level.selectedShapes.contains(shape)) {
						dragType = 2;
						break;
					}
				break;
			case Level.ENTITY_LAYER:
				break;
			case Level.EVENT_LAYER:
				break;
			}
			dragX = e.getX();
			dragY = e.getY();
			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		} else {
			switch (activeLayer) {
			case Level.BASE_LAYER:
				if (!e.isControlDown())
					level.selectedShapes.clear();
				for (GridShape shape : level.shapes) 
					if (shape.getPoly(new Point(offX, offY), scale).contains(e.getPoint()))
						if (level.selectedShapes.contains(shape))
							level.selectedShapes.remove(shape);
						else
							level.selectedShapes.add(shape);
				baseSettings.updateList();
				break;
			case Level.ENTITY_LAYER:
				break;
			case Level.EVENT_LAYER:
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragX = dragY = dragType = -1;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
