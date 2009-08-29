/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.connection.OracleConnectionPool;
import com.gs.oracle.connection.OracleConnectionUtil;
import com.gs.oracle.service.DatabaseConnectionService;

/**
 * @author Green Moon
 *
 */
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {
	
	private ConnectionProperties connectionProperties;
	
	public DatabaseConnectionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
		

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}
	
	
	public DataSource getDataSource(ConnectionProperties connectionProperties){
		try {
			return OracleConnectionUtil.createDataSource(connectionProperties);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Connection createConnection(ConnectionProperties connectionProperties) {
		try {
			return OracleConnectionUtil.getConnection(connectionProperties);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Connection disconnect(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Connection disconnectAll(List<Connection> connectionList) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Boolean testConnection(ConnectionProperties connectionProperties) throws ApplicationException{
		return OracleConnectionUtil.testConnection(connectionProperties);
	}

}
