/**
 * 
 */
package com.gs.oracle.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.pool.OracleDataSource;

/**
 * @author Green Moon
 * 
 */
public class OracleConnectionUtil {

	public static String formConnectionURL(
			ConnectionProperties connectionProperties) {
		StringBuffer url = new StringBuffer("jdbc:oracle:thin:");
		if (null != connectionProperties.getUserName()
				&& !connectionProperties.getUserName().equals("")) {
			url.append(connectionProperties.getUserName()).append("/");
		}
		if (null != connectionProperties.getPassword()
				&& !connectionProperties.getPassword().equals("")) {
			url.append(connectionProperties.getPassword()).append("@");
		}
		if (null != connectionProperties.getHostName()
				&& !connectionProperties.getHostName().equals("")) {
			url.append(connectionProperties.getHostName()).append(":");
		}
		url.append(connectionProperties.getPortNumber()).append("/");
		if (null != connectionProperties.getSid()
				&& !connectionProperties.getSid().equals("")) {
			url.append(connectionProperties.getSid());
		}

		return url.toString();
	}

	public static Connection getConnection(
			ConnectionProperties connectionProperties)
			throws ClassNotFoundException, SQLException {
		if (connectionProperties == null)
			return null;
		Connection connection = null;
		Class.forName(oracle.jdbc.driver.OracleDriver.class.getCanonicalName());
		String URL = formConnectionURL(connectionProperties);

		connection = DriverManager.getConnection(URL);
		return connection;
	}

	public static boolean testConnection(
			ConnectionProperties connectionProperties) {
		if (connectionProperties == null)
			return false;
		boolean connected = false;
		Connection conn = null;
		try {
			conn = getConnection(connectionProperties);
			if (conn != null) {
				connected = true;
			}
		} catch (ClassNotFoundException e) {
			connected = false;
			e.printStackTrace();
		} catch (SQLException e) {
			connected = false;
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return connected;
	}

	public static DataSource createDataSource(
			ConnectionProperties connectionProperties)
			throws ClassNotFoundException, SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setDriverType("thin");
		ds.setServerName(connectionProperties.getHostName());
		ds.setPortNumber(connectionProperties.getPortNumber());
		ds.setDatabaseName(connectionProperties.getSid()); // sid
		ds.setUser(connectionProperties.getUserName());
		ds.setPassword(connectionProperties.getPassword());
		
		return ds;
	}
}
