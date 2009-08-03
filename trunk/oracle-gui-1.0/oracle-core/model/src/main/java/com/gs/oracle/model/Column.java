/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: Column.java
 *	Type	: com.gs.oracle.model.Column.java
 *	Date	: Jul 29, 2009	1:06:46 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.model;

import java.io.Serializable;

import com.gs.oracle.BaseDbModel;

/**
 * Type Name	: com.gs.oracle.model.Column
 *
 */
public class Column<T> extends BaseDbModel implements Serializable {

	private int columnID;
	private boolean nullable;
	private String dataType;
	private int size;
	private int precision;
	private T defaultValue;
	private boolean primaryKey;
	
	
	public int getColumnID() {
		return columnID;
	}
	public void setColumnID(int columnID) {
		this.columnID = columnID;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public T getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	
	@Override
	public String toString() {
		return getModelName() + " [ " + dataType + ", (" + size +")" + 
			((nullable) ? "NULL" : "NOTNULL") + " ]";
	}
}
