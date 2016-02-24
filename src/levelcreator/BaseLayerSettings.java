package levelcreator;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BaseLayerSettings extends JPanel {

	Level level;
	JList<String> shapeList;
	DefaultListModel<String> shapeListModel;

	public BaseLayerSettings(Level level2) {
		this.level = level2;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		shapeListModel = new DefaultListModel<String>();
		shapeList = new JList<String>(shapeListModel);
		for (int i = 0; i < level.shapes.size(); i++)
			shapeListModel.addElement(i + ": " + level.shapes.get(i).getName());
		JScrollPane listScrollPane = new JScrollPane(shapeList);
		shapeList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					level.selectedShapes.clear();
					for (int index : shapeList.getSelectedIndices())
						level.selectedShapes.add(level.shapes.get(index));
				}
			}
		});
		JPanel listPane = new JPanel();
		listPane.add(new JLabel("Grid Objects"));
		listPane.add(Box.createRigidArea(new Dimension(0,5)));
		listPane.add(listScrollPane);
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		JPanel properties = new JPanel();
		JTextField shapeName = new JTextField();
		properties.setLayout(new BoxLayout(properties, BoxLayout.PAGE_AXIS));
		properties.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		properties.add(new JLabel("Name"));
		properties.add(shapeName);
		properties.add(new JLabel("Type"));
		JComboBox types = new JComboBox(GridShape.TYPES);
		properties.add(types);
		properties.add(new JLabel("Friction"));
		JComboBox friction = new JComboBox(GridShape.SURFACE);
		properties.add(friction);
		JCheckBox shapeMoving = new JCheckBox("Moving");
		properties.add(shapeMoving);
		JButton setPath = new JButton("Set path");
		properties.add(setPath);
		properties.add(new JLabel("Speed"));
		JSpinner speedSelecter = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		properties.add(speedSelecter);
		
		add(listPane);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		add(properties);
	}
	
	public void updateList() {
		int selected[] = new int[level.selectedShapes.size()];
		for (int i = 0; i < selected.length; i++)
			selected[i] = level.shapes.indexOf(level.selectedShapes.get(i));
		shapeList.setSelectedIndices(selected);
	}
}
