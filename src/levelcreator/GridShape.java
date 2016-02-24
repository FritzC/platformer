package levelcreator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class GridShape {
	
	public final static String TYPES[] = {"Solid", "Top solid", "Hazard", "Water"};
	
	public final static String SURFACE[] = {"Normal", "Ice"};

	private Point[] vertices;
	private Point[] movements;
	private Polygon poly;
	
	private String name = "GridObject";

	public GridShape(Point ... pos) {
		vertices = pos;
	}
	
	public void setMovements(Point ... pos) {
		movements = pos;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void moveShape(int horizontal, int vertical) {
		for (int i = 0; i < vertices.length; i++) {
			Point newPos = new Point((int) (vertices[i].getX() + horizontal),
					(int) (vertices[i].getY() + vertical));
			vertices[i] = newPos;
		}
		if (movements != null
				&& movements.length > 0) {
			for (int i = 0; i < movements.length; i++) {
				Point newPos = new Point((int) (movements[i].getX() + horizontal),
						(int) (movements[i].getY() + vertical));
				movements[i] = newPos;
			}
		}
	}
	
	public Polygon getPoly(Point offset, int size) {
		int xCoords[] = new int[vertices.length];
		int yCoords[] = new int[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			xCoords[i] = (int) (vertices[i].getX() * size + offset.getX());
			yCoords[i] = (int) (vertices[i].getY() * size + offset.getY());
		}
		poly = new Polygon(xCoords, yCoords, vertices.length);
		return poly;
	}
	
	public Color getColor(Point mousePos, boolean border, boolean selected, boolean hovered) {
		float off = 0;
		if (selected)
			off -= 0.2;
		if (border)
			off -= 0.2;
		if (hovered)
			off -= 0.05;
		return new Color(0.5f + off, 0.5f + off, 0.5f + off, 0.8f);
	}

	public void draw(Graphics g, Point mousePos, Point offset, int size,
			int layer, boolean selected) {
		getPoly(offset, size);
		boolean hovered = false;
		if (mousePos != null)
			hovered = poly.contains(mousePos);
		g.setColor(getColor(mousePos, false, selected, hovered));
		g.fillPolygon(poly);
		g.setColor(getColor(mousePos, true, selected, hovered));
		g.drawPolygon(poly);
		if (movements != null
				&& movements.length > 1) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(1.0f, 0f, 0f, 0.35f));
            if (selected)
                g2.setColor(new Color(1.0f, 0f, 0f, 0.50f));
            g2.setStroke(new BasicStroke(size / 5));
			for (int i = 0; i < movements.length - 1; i++) {
				Point pos = movements[i];
				Point pos2 = movements[i + 1];
				int x1 = (int) (pos.getX() * size + offset.getX());
				int y1 = (int) (pos.getY() * size + offset.getY());
				int x2 = (int) (pos2.getX() * size + offset.getX());
				int y2 = (int) (pos2.getY() * size + offset.getY());
				g2.drawLine(x1, y1, x2, y2);
			}
			g2.setStroke(new BasicStroke(0));
			for (Point pos : movements) {
				int x1 = (int) (pos.getX() * size + offset.getX());
				int y1 = (int) (pos.getY() * size + offset.getY());
				g2.fillOval(x1 - size / 5, y1 - size / 5, 2 * size / 5, 2 * size / 5);
			}
		}
	}
}
