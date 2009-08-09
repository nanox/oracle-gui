/**
 * 
 */
package com.gs.oracle.service;

import java.sql.Connection;
import java.util.List;

import com.gs.oracle.connection.ConnectionProperties;

/**
 * @author Green Moon
 *
 */
public interface DatabaseConnectionService {

	public Boolean testConnection(ConnectionProperties connectionProperties);
	
	public Connection createConnection(ConnectionProperties connectionProperties);
	
	public Connection disconnect(Connection connection);
	
	public Connection disconnectAll(List<Connection> connectionList);
	
}
