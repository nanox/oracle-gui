/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: DbConnectionProperties.java
 *	Type	: com.gs.oracle.dbcon.DbConnectionProperties.java
 *	Date	: Jul 29, 2009	3:23:49 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.dbcon;

import java.io.Serializable;

/**
 * @author Green Moon
 *
 */
public class DbConnectionProperties implements Serializable {

	private String connectionName;
	private String hostName;
	private String port;
	private String userName;
	private String password;
	
	private String sid;

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	
}
