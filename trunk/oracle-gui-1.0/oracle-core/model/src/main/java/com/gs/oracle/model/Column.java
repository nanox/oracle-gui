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
public class Column extends BaseDbModel implements Serializable {

	@ColumnHeader(columnName="COLUMN_ID")
	private int columnID;
	private boolean nullable;
	private int dataType;
	private String typeName;
	private int size;
	private int precision;
	private Object defaultValue;
	private boolean primaryKey;
	private boolean foreignKey;
	
	
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
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
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
	public Object getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Object defaultValue) {
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
		return getModelName() + " [ " + typeName + ", (" + size +")" + 
			((nullable) ? "NULL" : "NOTNULL") + " ]";
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public boolean isForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}
}
