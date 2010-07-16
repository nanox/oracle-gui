/**
 * 
 */
package com.gs.dbex.historyMgr;

import java.util.List;

import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public interface ApplicationDataHistoryMgr {

	public List<ConnectionProperties> loadAllConnectionProperties();
	
	public List<ConnectionProperties> loadAllConnectionProperties(String fileName);
	
	public ConnectionProperties getConnectionProperties(String connectionName);
	
}
