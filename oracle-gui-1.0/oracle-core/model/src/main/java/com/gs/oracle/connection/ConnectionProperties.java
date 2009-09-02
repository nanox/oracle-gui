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
public class ConnectionProperties implements Serializable, Comparable<ConnectionProperties> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6946700888781670434L;

	private String connectionName;
	private String userName;
	private String password;
	private Boolean savePassword;
	private String hostName;
	private Integer portNumber;
	private String sid;
	private String serviceName;
	private String databaseName;
	private Integer displayOrder = 0;
	private Boolean isModified = Boolean.TRUE;
	
	private transient DataSource dataSource;
	
	
	public ConnectionProperties() {
		setConnectionName("UN-NAMED");
	}
	public ConnectionProperties(String connectionName) {
		this.connectionName = connectionName;
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

	@Override
	public int compareTo(ConnectionProperties o) {
		return this.displayOrder.compareTo(o.getDisplayOrder());
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
	public Boolean getSavePassword() {
		return savePassword;
	}
	public void setSavePassword(Boolean savePassword) {
		this.savePassword = savePassword;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public Integer getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(Integer portNumber) {
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
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public Boolean getIsModified() {
		return isModified;
	}
	public void setIsModified(Boolean isModified) {
		this.isModified = isModified;
	}

	

}
