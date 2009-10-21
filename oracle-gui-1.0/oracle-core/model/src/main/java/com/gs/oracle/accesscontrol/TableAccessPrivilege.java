/**
 * 
 */
package com.gs.oracle.accesscontrol;

/**
 * @author Sabuj Das
 *
 */
public class TableAccessPrivilege {

	private String tableName;
	private DatabaseAccessPrivilege databaseAccessPrivilege;
	
	public TableAccessPrivilege() {
		// TODO Auto-generated constructor stub
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public DatabaseAccessPrivilege getDatabaseAccessPrivilege() {
		return databaseAccessPrivilege;
	}

	public void setDatabaseAccessPrivilege(
			DatabaseAccessPrivilege databaseAccessPrivilege) {
		this.databaseAccessPrivilege = databaseAccessPrivilege;
	}

}

