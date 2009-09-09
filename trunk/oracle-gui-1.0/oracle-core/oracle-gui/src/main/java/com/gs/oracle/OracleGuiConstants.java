/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: OracleGuiConstants.java
 *	Type	: com.gs.oracle.OracleGuiConstants.java
 *	Date	: Aug 3, 2009	12:08:08 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Green Moon
 * 
 */
public interface OracleGuiConstants {

	String IMAGE_PATH = "/images/";
	String QUERY_IMAGE_PATH = "/images/query/";
	String DATA_DIR = "./data/";
	String CONNECTION_DATA_FILE = DATA_DIR
			+ "connection/oracle_connections.xml";
	String CONN_PROPERTIES_MAPPING_FILE = "com/gs/oracle/castor/connection_properties-mapping.xml";

	List<String> SQL_KEYWORD_LIST = new ArrayList<String>();

	String SQL_KEYWORD = "ALL, ALTER, AND, ANY, ARRAY, ARROW, AS, ASC, AT,"
			+ "BEGIN, BETWEEN, BY,CASE, CHECK, CLUSTERS, CLUSTER, COLAUTH, COLUMNS, " +
					"COMPRESS, CONNECT, CRASH, CREATE, CURRENT,DECIMAL, DECLARE, " +
					"DEFAULT, DELETE, DESC, DISTINCT, DROP,ELSE, END, EXCEPTION, " +
					"EXCLUSIVE, EXISTS,FETCH, FORM, FOR, FROM,GOTO, GRANT, GROUP," +
					"HAVING,IDENTIFIED, IF, IN, INDEXES, INDEX, INSERT, INTERSECT, " +
					"INTO, IS,LIKE, LOCK,MINUS, MODE,NOCOMPRESS, NOT, NOWAIT, NULL,OF, " +
					"ON, OPTION, OR, ORDER,OVERLAPS,PRIOR, PROCEDURE, PUBLIC,RANGE, " +
					"RECORD, RESOURCE, REVOKE,SELECT, SHARE, SIZE, SQL, START, SUBTYPE," +
					"TABAUTH, TABLE, THEN, TO, TYPE,UNION, UNIQUE, UPDATE, USE,VALUES, " +
					"VIEW, VIEWS,WHEN, WHERE, WITH";
	
	// constants for dependency
	Color COLUMN_NAMES_BG_COLOR = new Color(232, 242, 254);
	Color COLUMN_NAMES_FG_COLOR = Color.BLACK;
	Color TABLE_BORDER_COLOR = new Color(106,140,203);
	Color TABLE_HEADER_BG_COLOR = new Color(69, 117, 205);
	Color TABLE_HEADER_FG_COLOR = Color.WHITE;
	Color TABLE_LEFT_MARGIN_BG_COLOR = new Color(171, 200, 246);
	Color TABLE_LEFT_MARGIN_FG_COLOR = Color.BLACK;
	Color TABLE_DEPENDENCY_LONE_COLOR = new Color(6, 118, 96);
	int SCALE_100_PERCENT = 100;
	int TABLE_LEFT_MARGIN_WIDTH = 20;
}
