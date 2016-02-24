package levelcreator;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class MyListModel extends AbstractListModel {

	private final ArrayList<GridShape> myArrayList = new ArrayList<GridShape>();

	public void addElement(GridShape obj) {
		myArrayList.add(obj);
		fireIntervalAdded(this, myArrayList.size() - 1, myArrayList.size() - 1);
	}

	public void removeElement(GridShape obj) {
		int index = myArrayList.indexOf(obj);
		myArrayList.remove(obj);
		fireIntervalRemoved(this, index, index);
	}

	@Override
	public Object getElementAt(int index) {
		return myArrayList.get(index);
	}

	@Override
	public int getSize() {
		return myArrayList.size();
	}
	
	public boolean contains(GridShape obj) {
		return myArrayList.contains(obj);
	}
}