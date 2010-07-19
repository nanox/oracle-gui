/**
 * 
 */
package com.gs.dbex.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

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
	private int defaultPortNumber = 1521;
	private String defaultHostName = "localhost";
	public ApplicationContext applicationSpringContext;
	
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
	
	public String getConnectionConfigMappingFileName() {
		return CASTOR_MAPPING_DIR + FILE_SEPARATOR + "connection-properties-mapping.xml";
	}

	public String getLocalHistoryPath() {
		return getDataDirName() + FILE_SEPARATOR + "localHistory";
	}

	public String getUserDataPath() {
		return getDataDirName() + FILE_SEPARATOR + "user";
	}

	public int getDefaultPortNumber() {
		return defaultPortNumber;
	}

	public void setDefaultPortNumber(int defaultPortNumber) {
		this.defaultPortNumber = defaultPortNumber;
	}

	public String getDefaultHostName() {
		return defaultHostName;
	}

	public void setDefaultHostName(String defaultHostName) {
		this.defaultHostName = defaultHostName;
	}
	
	public String getApplicationPropertiesDir(){
		return APPLICATION_DATA_DIR + FILE_SEPARATOR + APPLICATION_PROPERTIES_DIR_NAME;
	}
	
	public String getErrorMsgConstFileName() {
		return getApplicationPropertiesDir() + FILE_SEPARATOR + "ErrorMessage.properties";
	}
	
}
