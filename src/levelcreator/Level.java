package levelcreator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Level {

	public List<GridShape> shapes = new ArrayList<GridShape>();
	public List<GridShape> selectedShapes = new ArrayList<GridShape>();
	
	public int width, height;
	
	public final static int BASE_LAYER = 0,
			ENTITY_LAYER = 1,
			EVENT_LAYER = 2;
	
	public Level(String filePath) {
		width = 50;
		height = 25;
		GridShape shape1 = new GridShape(new Point(3, 4), new Point(3, 6), new Point(5,
				6), new Point(5, 3));
		shape1.setMovements(new Point(4, 5), new Point(4, 8), new Point(9, 2));
		shape1.setName("Test1111");
		shapes.add(shape1);
		shapes.add(new GridShape(new Point(10, 7), new Point(12, 5), new Point(13,
				10)));
	}

	public void addShape(GridShape shape) {
		shapes.add(shape);
	}
	
	public void drawLevel(Point offset, Point mousePos, int size, int layer, Graphics g) {
		switch(layer) {
		case BASE_LAYER:
			for (GridShape shape : shapes)
				shape.draw(g, mousePos, offset, size, layer, selectedShapes.contains(shape));
			break;
		case ENTITY_LAYER:
			break;
		case EVENT_LAYER:
			break;
		}
		g.setColor(Color.BLACK);
	}

}
