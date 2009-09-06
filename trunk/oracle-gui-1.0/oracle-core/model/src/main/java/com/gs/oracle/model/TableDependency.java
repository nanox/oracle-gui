/**
 * 
 */
package com.gs.oracle.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author sabuj.das
 *
 */
public class TableDependency implements Serializable{

	private Table currentTable;
	
	private Set<TableRelation> tableRelationSet;
	
	public TableDependency() {
		// TODO Auto-generated constructor stub
	}

	public Table getCurrentTable() {
		return currentTable;
	}

	public void setCurrentTable(Table currentTable) {
		this.currentTable = currentTable;
	}

	public Set<TableRelation> getTableRelationSet() {
		return tableRelationSet;
	}

	public void setTableRelationSet(Set<TableRelation> tableRelationSet) {
		this.tableRelationSet = tableRelationSet;
	}
	
	
}
