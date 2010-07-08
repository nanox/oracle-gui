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
	
	private final String applicationDataDir = getDataDirName() + FILE_SEPARATOR + "application";
	private final String connectionConfigFileName = getApplicationDataDir() + FILE_SEPARATOR + "connection-properties.xml";
	
	
	/* -----------  Utility methods  --------------------------------------- */
	private void initContext() {
		APP_PROPERTIES_MAP.put(EXTERNAL_DATA_PATH_KEY, EXTERNAL_DATA_PATH);
	}

	/* -----------  get-set methods  --------------------------------------- */
	
	public String getDataDirName(){
		return APP_PROPERTIES_MAP.get(EXTERNAL_DATA_PATH_KEY);
	}

	public String getApplicationDataDir() {
		return applicationDataDir;
	}

	public String getConnectionConfigFileName() {
		return connectionConfigFileName;
	}
	
}
