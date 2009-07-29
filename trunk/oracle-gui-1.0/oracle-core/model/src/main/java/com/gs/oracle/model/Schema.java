/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: Schema.java
 *	Type	: com.gs.oracle.model.Schema.java
 *	Date	: Jul 29, 2009	1:26:03 PM
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
 * Type Name	: com.gs.oracle.model.Schema
 *
 */
public class Schema extends BaseDbModel implements Serializable {

	private List<Table> tableList;
	
	
	

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}
	
	
}
