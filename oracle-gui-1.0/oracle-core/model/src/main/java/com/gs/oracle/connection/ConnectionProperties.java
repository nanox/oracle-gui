/**
 * 
 */
package com.gs.oracle.connection;

import java.io.Serializable;
import java.sql.Connection;

import javax.sql.DataSource;

/**
 * @author Green Moon
 * 
 */
public class ConnectionProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6946700888781670434L;

	private String connectionName;
	private String userName;
	private String password;
	private boolean savePassword;
	private DatabaseTypeEnum databaseType;
	private String hostName;
	private int portNumber;
	private String sid;
	private String serviceName;
	private String databaseName;
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ConnectionProperties(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSavePassword() {
		return savePassword;
	}

	public void setSavePassword(boolean savePassword) {
		this.savePassword = savePassword;
	}

	public DatabaseTypeEnum getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(DatabaseTypeEnum databaseType) {
		this.databaseType = databaseType;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((connectionName == null) ? 0 : connectionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionProperties other = (ConnectionProperties) obj;
		if (connectionName == null) {
			if (other.connectionName != null)
				return false;
		} else if (!connectionName.equals(other.connectionName))
			return false;
		return true;
	}

	

}
