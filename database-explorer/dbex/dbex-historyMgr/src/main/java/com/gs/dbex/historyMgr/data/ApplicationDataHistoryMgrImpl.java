/**
 * 
 */
package com.gs.dbex.historyMgr.data;

import java.util.List;

import com.gs.dbex.common.DbexCommonContext;
import com.gs.dbex.historyMgr.ApplicationDataHistoryMgr;
import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public class ApplicationDataHistoryMgrImpl implements ApplicationDataHistoryMgr {

	private static DbexCommonContext dbexCommonContext = DbexCommonContext.getInstance();
	
	/**
	 * This API Loads all the ConnectionProperties into a List from predefined file.
	 */
	public List<ConnectionProperties> loadAllConnectionProperties() {
		return loadAllConnectionProperties(dbexCommonContext.getConnectionConfigFileName());
	}
	
	/**
	 * This API Loads all the ConnectionProperties into a List from a given file.
	 */
	public List<ConnectionProperties> loadAllConnectionProperties(String fileName) {
		return null;
	}

	/**
	 * Reads a ConnectionProperties by connection name.
	 */
	public ConnectionProperties getConnectionProperties(String connectionName) {
		return null;
	}

}
