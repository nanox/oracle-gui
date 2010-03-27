/**
 * 
 */
package com.gs.dbex.integration.impl.oracle;

import java.sql.SQLException;

import com.gs.dbex.common.exception.DbexException;
import com.gs.dbex.integration.helper.DatabaseConnectionHelper;
import com.gs.dbex.integration.helper.oracle.OracleDatabaseConnectionHelper;
import com.gs.dbex.integration.impl.DatabaseConnectionIntegrationImpl;
import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public class OracleDatabaseConnectionIntegrationImpl extends
		DatabaseConnectionIntegrationImpl {

	private DatabaseConnectionHelper databaseConnectionHelper;
	
	
	
	public Boolean closeConnection(ConnectionProperties connectionProperties) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean connectToDatabase(ConnectionProperties connectionProperties)
			throws DbexException {
		try {
			if(getDatabaseConnectionHelper().testConnection(connectionProperties)){
				connectionProperties.setDataSource(getDatabaseConnectionHelper().createDataSource(connectionProperties));
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @return the databaseConnectionHelper
	 */
	public DatabaseConnectionHelper getDatabaseConnectionHelper() {
		return databaseConnectionHelper;
	}

	/**
	 * @param databaseConnectionHelper the databaseConnectionHelper to set
	 */
	public void setDatabaseConnectionHelper(
			DatabaseConnectionHelper databaseConnectionHelper) {
		this.databaseConnectionHelper = databaseConnectionHelper;
	}
	
	

}
