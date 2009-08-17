/**
 * 
 */
package com.gs.oracle.connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import com.gs.oracle.grabber.OracleDbGrabber;

/**
 * @author Green Moon
 *
 */
public class OracleConnectionPool {

	private DataSource dataSource;
	
	private ConnectionProperties connectionProperties;

	public OracleConnectionPool(ConnectionProperties connectionProperties) throws SQLException {
		this.connectionProperties = connectionProperties;
		initDataSource();
	}
	
	private void initDataSource() throws SQLException{
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setDataSourceName(connectionProperties.getConnectionName());
		dataSource.setDatabaseName(connectionProperties.getDatabaseName());
		dataSource.setPassword(connectionProperties.getPassword());
		dataSource.setPortNumber(connectionProperties.getPortNumber());
		dataSource.setServerName(connectionProperties.getHostName());
		dataSource.setUser(connectionProperties.getUserName());
		//dataSource.setURL(arg0);
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}
	
	
	
}
