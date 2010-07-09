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
	String USER_HOME = System.getProperty("user.home");
	
	String EXTERNAL_DATA_PATH_KEY = "EXTERNAL_DATA_PATH";
	String DEFAULT_EXTERNAL_DATA_PATH = USER_HOME + FILE_SEPARATOR + "dbex-data";
	
	int MAX_SAVED_CONNECTIONS = 100;
	
	
}
