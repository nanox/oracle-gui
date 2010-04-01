/**
 * 
 */
package com.gs.dbex.common.enums;

/**
 * @author sabuj.das
 *
 */
public enum DatabaseStorageTypeEnum {

	CATALOG_STORAGE("CATALOG_STORAGE", "Store as Catalog"),
	SCHEMA_STORAGE("SCHEMA_STORAGE", "Store as Schema"),
	UNKNOWN("UNKNOWN", "Unknown");
	
	private String code;
	private String description;

	private DatabaseStorageTypeEnum(String code, String description) {
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
