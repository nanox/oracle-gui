/**
 * 
 */
package com.gs.oracle.enums;

/**
 * @author sabuj.das
 *
 */
public enum TableDataExportTypeEnum {

	INSERT_STATEMENT(1001, "SQL Insert Statement", ".sql"),
	SQL_LOADER(1002, "Oracle SQL*Loader", ".ldr"),
	CSV(1003, "Coma Separated Values", ".csv"),
	HTML(1004, "Single page HTML (Table View)", ".html"),
	TEXT(1005, "Plain Text File", ".txt"),
	EXCEL(1006, "MS Excel File", ".xls"),
	XML(1007, "XML", ".xml");
	
	private int code;
	private String description;
	private String extension;
	
	private TableDataExportTypeEnum(int code, String description,
			String extension) {
		this.code = code;
		this.description = description;
		this.extension = extension;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	
	
}
