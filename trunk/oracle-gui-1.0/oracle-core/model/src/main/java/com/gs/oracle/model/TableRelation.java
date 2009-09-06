/**
 * 
 */
package com.gs.oracle.model;

import java.io.Serializable;

/**
 * @author sabuj.das
 *
 */
public class TableRelation implements Serializable {

	private String relationTitle;
	private String relationType;
	
	private ForeignKey importedKey;
	private ForeignKey exportedKey;
	
	private Table importedTable, exportedTable;

	
	public TableRelation() {
		// TODO Auto-generated constructor stub
	}
	
	public String getRelationTitle() {
		return relationTitle;
	}

	public void setRelationTitle(String relationTitle) {
		this.relationTitle = relationTitle;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public ForeignKey getImportedKey() {
		return importedKey;
	}

	public void setImportedKey(ForeignKey importedKey) {
		this.importedKey = importedKey;
	}

	public ForeignKey getExportedKey() {
		return exportedKey;
	}

	public void setExportedKey(ForeignKey exportedKey) {
		this.exportedKey = exportedKey;
	}

	public Table getImportedTable() {
		return importedTable;
	}

	public void setImportedTable(Table importedTable) {
		this.importedTable = importedTable;
	}

	public Table getExportedTable() {
		return exportedTable;
	}

	public void setExportedTable(Table exportedTable) {
		this.exportedTable = exportedTable;
	}
	
	
}
