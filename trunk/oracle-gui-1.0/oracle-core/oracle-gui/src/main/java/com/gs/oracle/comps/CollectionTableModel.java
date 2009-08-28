/**
 * 
 */
package com.gs.oracle.comps;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author sabuj.das
 *
 */
public class CollectionTableModel<T> implements TableModel {
	
	private Collection<T> objectCollection;
	private List<String> columnNameList;
	private int columnCount;
	private int rowCount;
	

	public CollectionTableModel(Collection<T> objectCollection) {
		this.objectCollection = objectCollection;
	}
	
	private <T> void prepareModel(){
		
	}
	
	

	
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	public Collection<T> getObjectCollection() {
		return objectCollection;
	}

	public void setObjectCollection(Collection<T> objectCollection) {
		this.objectCollection = objectCollection;
		prepareModel();
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}
