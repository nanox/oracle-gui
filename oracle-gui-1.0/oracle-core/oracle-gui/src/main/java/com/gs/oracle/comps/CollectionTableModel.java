/**
 * 
 */
package com.gs.oracle.comps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.gs.oracle.model.ColumnHeader;

/**
 * @author sabuj.das
 *
 */
public class CollectionTableModel implements TableModel {
	
	private Class collectionClass;
	private List objectCollection = new ArrayList();
	private List<String> columnNameList = new ArrayList<String>(100);
	private int columnCount;
	private int rowCount;
	

	public CollectionTableModel(List objectCollection) {
		this.objectCollection = objectCollection;
	}
	
	private void prepareModel(){
		rowCount = objectCollection.size();
		Method[] ms = collectionClass.getMethods();
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(ColumnHeader.class)){
				ColumnHeader ch = method.getAnnotation(ColumnHeader.class);
				if(ch != null){
					System.out.println(ch.title());
					columnNameList.add(ch.index(), ch.title());
					System.out.println(ch.index());
				}
			}
		}
		columnCount = columnNameList.size();
		
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
		Method[] ms = collectionClass.getMethods();
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(ColumnHeader.class)){
				ColumnHeader ch = method.getAnnotation(ColumnHeader.class);
				if(ch != null){
					if(columnIndex == ch.index())
						return method.getReturnType();
				}
			}
		}
		return String.class;
	}

	
	@Override
	public int getColumnCount() {
		return columnCount;
	}

	
	@Override
	public String getColumnName(int columnIndex) {
		Method[] ms = collectionClass.getMethods();
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(ColumnHeader.class)){
				ColumnHeader ch = method.getAnnotation(ColumnHeader.class);
				if(ch != null){
					if(columnIndex == ch.index())
						return ch.title();
				}
			}
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object o = objectCollection.get(rowIndex);
		Method[] ms = o.getClass().getMethods();
		for (Method method : ms) {
			if(method.getName().startsWith("get") && method.isAnnotationPresent(ColumnHeader.class)){
				ColumnHeader ch = method.getAnnotation(ColumnHeader.class);
				if(ch != null){
					if(columnIndex == ch.index())
						return method.getDeclaringClass();
				}
			}
		}
		return "";
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

	public Class getCollectionClass() {
		return collectionClass;
	}

	public void setCollectionClass(Class collectionClass) {
		this.collectionClass = collectionClass;
	}

	public Collection getObjectCollection() {
		return objectCollection;
	}

	public void setObjectCollection(List objectCollection) {
		this.objectCollection = objectCollection;
	}

	public List<String> getColumnNameList() {
		return columnNameList;
	}

	public void setColumnNameList(List<String> columnNameList) {
		this.columnNameList = columnNameList;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	
}
