/**
 * 
 */
package com.gs.dbex.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sabuj.das
 *
 */
public final class DbexCommonContext implements DbexCommonConstants{

	private static DbexCommonContext instance;
	
	private DbexCommonContext() {
		initContext();
	}

	public static DbexCommonContext getInstance() {
		if(instance == null)
			instance = new DbexCommonContext();
		return instance;
	}
	
	/* ------------  Context Variables  ------------------------------------ */
	
	public final Map<String, String> APP_PROPERTIES_MAP = new HashMap<String, String>();
	
	
	/* -----------  Utility methods  --------------------------------------- */
	private void initContext() {
		APP_PROPERTIES_MAP.put(EXTERNAL_DATA_PATH_KEY, DEFAULT_EXTERNAL_DATA_PATH);
	}

	
	/* -----------  get-set methods  --------------------------------------- */
	
	public String getDataDirName(){
		return APP_PROPERTIES_MAP.get(EXTERNAL_DATA_PATH_KEY);
	}
	
	public void setDataDirName(String dir){
		APP_PROPERTIES_MAP.put(EXTERNAL_DATA_PATH_KEY, dir);
	}

	public String getApplicationDataDir() {
		return getDataDirName() + FILE_SEPARATOR + "application";
	}

	public String getConnectionConfigFileName() {
		return getApplicationDataDir() + FILE_SEPARATOR + "connection-properties.xml";
	}

	public String getLocalHistoryPath() {
		return getDataDirName() + FILE_SEPARATOR + "localHistory";
	}

	public String getUserDataPath() {
		return getDataDirName() + FILE_SEPARATOR + "user";
	}
	
}
