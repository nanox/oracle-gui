/**
 * 
 */
package com.gs.oracle.accesscontrol;

import java.io.Serializable;

/**
 * @author Sabuj Das
 *
 */
public class ColumnPrivilege  implements Serializable {

	private String columnName;
	private DatabasePrivilege databasePrivilege;
	
	public ColumnPrivilege() {
		// TODO Auto-generated constructor stub
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public DatabasePrivilege getDatabasePrivilege() {
		return databasePrivilege;
	}

	public void setDatabasePrivilege(DatabasePrivilege databasePrivilege) {
		this.databasePrivilege = databasePrivilege;
	}
	
	
}
