/**
 * 
 */
package com.gs.oracle.grabber;

/**
 * @author sabuj.das
 *
 */
public interface DatabaseMetaDataConstants {

	/**
	 * TABLE_CAT String => table catalog (may be null)
	 */
	String TABLE_CAT = "TABLE_CAT";
	/**
	 * TABLE_NAME String => table name
	 */
	String TABLE_NAME = "TABLE_NAME";
	/**
	 * COLUMN_NAME String => column name
	 */
	String COLUMN_NAME = "COLUMN_NAME";
	/**
	 * PK_NAME String => primary key name (may be null)
	 */
	String PK_NAME = "PK_NAME";
	/**
	 * TABLE_SCHEM String => schema name
	 */
	String TABLE_SCHEM = "TABLE_SCHEM";
	/**
	 * TABLE_CATALOG String => catalog name (may be null)
	 */
	String TABLE_CATALOG = "TABLE_CATALOG";
	
	// Constant Strings for the resultset of getProcedures
	
	String PROCEDURE_CAT = "";
	String PROCEDURE_SCHEM = "";
	String PROCEDURE_NAME = "";
	
}
