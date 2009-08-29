/**
 * 
 */
package com.gs.oracle.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;

/**
 * @author Green Moon
 *
 */
public interface DatabaseConnectionService {

	public Boolean testConnection(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public Connection createConnection(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public Connection disconnect(Connection connection) throws ApplicationException;
	
	public Connection disconnectAll(List<Connection> connectionList) throws ApplicationException;
	
	public DataSource getDataSource(ConnectionProperties connectionProperties);
	
}
