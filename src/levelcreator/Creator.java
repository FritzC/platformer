package levelcreator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class Creator {

	public static void main(String args[]) {
		Level level = new Level("");
		
		JFrame frame = new JFrame();
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_T);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		fileMenu.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_T);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		fileMenu.add(saveItem);
		menu.add(fileMenu);

		BaseLayerSettings baseSettings = new BaseLayerSettings(level);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Base", null, baseSettings,
                "Base");
		tabbedPane.addTab("Entities", null, new BaseLayerSettings(level),
                "Entities");
		tabbedPane.addTab("Events", null, new BaseLayerSettings(level),
                "Entities");
		
		CreatorPane panel = new CreatorPane(level, baseSettings);
		frame.setJMenuBar(menu);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(tabbedPane, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setSize(750, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
