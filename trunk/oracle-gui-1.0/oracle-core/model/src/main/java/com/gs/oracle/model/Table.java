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
import java.util.ArrayList;
import java.util.List;

import com.gs.oracle.BaseDbModel;

/**
 * Type Name	: com.gs.oracle.model.Table
 *
 */
public class Table extends BaseDbModel implements Serializable {

	private PrimaryKey primaryKey;
	private List<Column> columnlist;
	private List<ForeignKey> importedKeys;
	private List<ForeignKey> exportedKeys;
	private String schemaName;
	
	public Table() {
		columnlist = new ArrayList<Column>();
		importedKeys = new ArrayList<ForeignKey>();
		exportedKeys = new ArrayList<ForeignKey>();
	}
	/**
	 * Returns the primary key column.
	 * @return
	 */
	public Column getPrimaryKeyColumn(){
		for (Column column : columnlist) {
			if(column.getPrimaryKey()){
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
		for (Column column : columnlist) {
			if(column.getPrimaryKey()){
				return true;
			}
		}
		return false;
	}

	public List<Column> getColumnlist() {
		return columnlist;
	}

	public void setColumnlist(List<Column> columnlist) {
		this.columnlist = columnlist;
	}
	
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	@Override
	public String toString() {
		return super.getModelName();
	}
	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}
	public List<ForeignKey> getImportedKeys() {
		return importedKeys;
	}
	public void setImportedKeys(List<ForeignKey> importedKeys) {
		this.importedKeys = importedKeys;
	}
	public List<ForeignKey> getExportedKeys() {
		return exportedKeys;
	}
	public void setExportedKeys(List<ForeignKey> exportedKeys) {
		this.exportedKeys = exportedKeys;
	}
	
}
