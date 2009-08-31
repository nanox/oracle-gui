/**
 * 
 */
package com.gs.oracle.comps;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.gs.oracle.jdbc.JdbcUtil;

/**
 * @author sabuj.das
 *
 */
public class ResultSetTableModel implements TableModel{
	
	private ResultSet resultSet;
	private ResultSetMetaData resultSetMetaData;
	private int columnCount;
	private int rowCount;
	
	public ResultSetTableModel(ResultSet resultSet) throws SQLException {
		setResultSet(resultSet);
	}

	public ResultSetTableModel(ResultSet resultSet, int rows) throws SQLException {
		rowCount = rows;
		setResultSet(resultSet);
	}

	public void setResultSet(ResultSet rs) throws SQLException {
		// set the resultset
		this.resultSet = rs;
		// get the resultset metadata from the resultset
		this.resultSetMetaData = rs.getMetaData();
		// get column count from the metadata
		this.columnCount = resultSetMetaData.getColumnCount();
		// move to the last row of the resultset
		if(ResultSet.TYPE_SCROLL_INSENSITIVE == rs.getType()){
			rs.last();
			this.rowCount = rs.getRow();
		}
		
	}
	
	

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class type = String.class;
		try {
			int columnType = resultSetMetaData.getColumnType(columnIndex + 1);
			type = JdbcUtil.getJavaClassForSqlType(columnType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public String getColumnName(int columnIndex) {
		try {
			return resultSetMetaData.getColumnLabel(columnIndex + 1);
		} catch (SQLException e) {
			return e.toString();
		}
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			if(ResultSet.TYPE_SCROLL_INSENSITIVE == getResultSet().getType()){
				getResultSet().absolute(rowIndex + 1); // Go to the specified row
			}else{
				int i = 0;
				while(getResultSet().next()){
					if(i == rowIndex)
						break;
					i++;
				}
			}
			
			Object o = resultSet.getObject(columnIndex + 1); // Get value of the column
			if (o == null)
				return null;
			else if(o instanceof Blob){
				return JdbcUtil.readFromBlob((Blob)o).toString();
			}
			else
				return o;
		} catch (SQLException e) {
			return e.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	@Override
	protected void finalize() throws Throwable {
		closeResultSet();
	}

	public void closeResultSet(){
		try{
			if(resultSet.getStatement() != null){
				resultSet.getStatement().close();
			}
		}catch(SQLException sqle){
			
		}
	}

}
