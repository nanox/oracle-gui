/**
 * 
 */
package com.gs.dbex.model.cfg;

import java.io.Serializable;

import javax.sql.DataSource;

/**
 * @author sabuj.das
 *
 */
public class ConnectionProperties implements Serializable, Comparable<ConnectionProperties> {

	/**
	 * serialVersionUID = 2717753646686919478L;
	 */
	private static final long serialVersionUID = 2717753646686919478L;
	
	private String connectionName;
	private String userName;
	private String password;
	private String connectionUrl;
	private String databaseType;
	
	private transient DataSource dataSource;

	public int compareTo(ConnectionProperties o) {
		return connectionName.compareTo(o.getConnectionName());
	}

	protected void finalize() throws Throwable {
		super.finalize();
		dataSource = null;
	}

	/**
	 * @return the connectionName
	 */
	public String getConnectionName() {
		return connectionName;
	}

	/**
	 * @param connectionName the connectionName to set
	 */
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the connectionUrl
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * @param connectionUrl the connectionUrl to set
	 */
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	/**
	 * @return the databaseType
	 */
	public String getDatabaseType() {
		return databaseType;
	}

	/**
	 * @param databaseType the databaseType to set
	 */
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
}
