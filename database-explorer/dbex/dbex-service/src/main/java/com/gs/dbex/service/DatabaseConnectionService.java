/**
 * 
 */
package com.gs.dbex.service;

import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public interface DatabaseConnectionService {

	
	
	public Boolean connectToDatabase(ConnectionProperties connectionProperties);
	
	public Boolean closeConnection(ConnectionProperties connectionProperties);
	
	
	
}
