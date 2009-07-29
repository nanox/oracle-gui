/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: Table.java
 *	Type	: com.gs.oracle.model.Table.java
 *	Date	: Jul 29, 2009	1:23:04 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.model;

import java.io.Serializable;
import java.util.List;

import com.gs.oracle.BaseDbModel;

/**
 * Type Name	: com.gs.oracle.model.Table
 *
 */
public class Table extends BaseDbModel implements Serializable {

	private List<Column<?>> columnlist;
	
	/**
	 * Returns the primary key column.
	 * @return
	 */
	public Column<?> getPrimaryKeyColumn(){
		for (Column<?> column : columnlist) {
			if(column.isPrimaryKey()){
				return column;
			}
		}
		return null;
	}
	
	/**
	 * Returns true if the table has any primary key.
	 * @return
	 */
	public boolean hasPrimaryKey(){
		for (Column<?> column : columnlist) {
			if(column.isPrimaryKey()){
				return true;
			}
		}
		return false;
	}

	public List<Column<?>> getColumnlist() {
		return columnlist;
	}

	public void setColumnlist(List<Column<?>> columnlist) {
		this.columnlist = columnlist;
	}
	
	
	
}
