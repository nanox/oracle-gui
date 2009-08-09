/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.Connection;
import java.util.List;

import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.service.DatabaseConnectionService;

/**
 * @author Green Moon
 *
 */
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {
	
	private ConnectionProperties connectionProperties;
	private Connection connection;
	

	/* (non-Javadoc)
	 * @see com.gs.oracle.service.DatabaseConnectionService#createConnection(com.gs.oracle.connection.ConnectionProperties)
	 */
	@Override
	public Connection createConnection(ConnectionProperties connectionProperties) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.oracle.service.DatabaseConnectionService#disconnect(java.sql.Connection)
	 */
	@Override
	public Connection disconnect(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.oracle.service.DatabaseConnectionService#disconnectAll(java.util.List)
	 */
	@Override
	public Connection disconnectAll(List<Connection> connectionList) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.oracle.service.DatabaseConnectionService#testConnection(com.gs.oracle.connection.ConnectionProperties)
	 */
	@Override
	public Boolean testConnection(ConnectionProperties connectionProperties) {
		// TODO Auto-generated method stub
		return null;
	}

}
