/**
 * 
 */
package com.gs.dbex.common.enums;

/**
 * @author sabuj.das
 *
 */
public enum DatabaseTypeEnum {

	OTHER("OTHER", "Other Database"),
	ORACLE("ORACLE", "Oracle"),
	MYSQL("MYSQL", "Mysql"),
	MSSQL_2005("MSSQL_2005", "MS SQL Server 2005");
	
	
	private String code;
	private String description;
	private DatabaseTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	
	public static DatabaseTypeEnum getDatabaseTypeEnum(String type){
		if(ORACLE.getCode().equals(type)){
			return ORACLE;
		}
		if(MYSQL.getCode().equals(type)){
			return MYSQL;
		}
		if(MSSQL_2005.getCode().equals(type)){
			return MSSQL_2005;
		}
		return OTHER;
	}
}
