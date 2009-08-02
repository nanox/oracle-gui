/**
 * 
 */
package com.gs.oracle.connection;

/**
 * @author Green Moon
 *
 */
public enum DatabaseTypeEnum {

	ORACLE("ORACLE", "Oracle"),
	MYSQL("MYSQL", "Mysql");
	
	
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
	
	
}
