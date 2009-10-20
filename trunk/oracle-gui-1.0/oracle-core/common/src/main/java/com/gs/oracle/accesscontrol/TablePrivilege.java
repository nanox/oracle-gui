/**
 * 
 */
package com.gs.oracle.accesscontrol;

import java.io.Serializable;

/**
 * @author Sabuj Das
 *
 */
public class TablePrivilege implements Serializable {

	private String tableName;
	private DatabasePrivilege databasePrivilege;
	
	public TablePrivilege() {
		// TODO Auto-generated constructor stub
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public DatabasePrivilege getDatabasePrivilege() {
		return databasePrivilege;
	}

	public void setDatabasePrivilege(DatabasePrivilege databasePrivilege) {
		this.databasePrivilege = databasePrivilege;
	}


}
