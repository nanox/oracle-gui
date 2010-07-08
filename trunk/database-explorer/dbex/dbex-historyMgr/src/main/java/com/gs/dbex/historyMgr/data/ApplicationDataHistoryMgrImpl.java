/**
 * 
 */
package com.gs.dbex.historyMgr.data;

import java.util.List;

import com.gs.dbex.historyMgr.ApplicationDataHistoryMgr;
import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public class ApplicationDataHistoryMgrImpl implements ApplicationDataHistoryMgr {

	/**
	 * This API Loads all the ConnectionProperties into a List
	 */
	public List<ConnectionProperties> loadAllConnectionProperties() {
		return null;
	}

	/**
	 * Reads a ConnectionProperties by connection name.
	 */
	public ConnectionProperties getConnectionProperties(String connectionName) {
		return null;
	}

}
