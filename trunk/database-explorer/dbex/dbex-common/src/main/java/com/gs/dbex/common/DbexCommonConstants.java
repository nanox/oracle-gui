/**
 * 
 */
package com.gs.dbex.common;

/**
 * @author sabuj.das
 *
 */
public interface DbexCommonConstants {

	String FILE_SEPARATOR = System.getProperty("file.separator");
	
	String EXTERNAL_DATA_PATH_KEY = "EXTERNAL_DATA_PATH";
	String EXTERNAL_DATA_PATH = "." + FILE_SEPARATOR + "data";
	
	/*String APP_DATA_PATH = EXTERNAL_DATA_PATH + FILE_SEPARATOR + "application";
	String USER_DATA_PATH = EXTERNAL_DATA_PATH + FILE_SEPARATOR + "user";
	String LOCAL_HISTORY_PATH = EXTERNAL_DATA_PATH + FILE_SEPARATOR + "localHistory";
	
	
	String CONNECTION_PROPS_FILE = APP_DATA_PATH + FILE_SEPARATOR + "ConnectionDetails.xml";*/
	int MAX_CONNECTION_PROPS_LIMIT = 100;
	
	
}
