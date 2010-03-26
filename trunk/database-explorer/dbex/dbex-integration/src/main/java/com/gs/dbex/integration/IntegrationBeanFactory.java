/**
 * 
 */
package com.gs.dbex.integration;

import com.gs.dbex.common.enums.DatabaseTypeEnum;

/**
 * @author sabuj.das
 *
 */
public final class IntegrationBeanFactory {

	private static IntegrationBeanFactory instance;
	
	private DatabaseMetadataIntegration oracleDatabaseMetadataIntegration;
	private DatabaseMetadataIntegration mysqlDatabaseMetadataIntegration;
	
	private IntegrationBeanFactory() {
		
	}
	
	public static IntegrationBeanFactory getBeanFactory(){
		if(instance == null)
			instance = new IntegrationBeanFactory();
		return instance;
	}
	
	public DatabaseMetadataIntegration getDatabaseMetadataIntegration(DatabaseTypeEnum databaseTypeEnum){
		if(DatabaseTypeEnum.ORACLE.equals(databaseTypeEnum)){
			return getOracleDatabaseMetadataIntegration();
		} else if(DatabaseTypeEnum.MYSQL.equals(databaseTypeEnum)){
			return getMysqlDatabaseMetadataIntegration();
		}
		return null;
	}

	public DatabaseMetadataIntegration getOracleDatabaseMetadataIntegration() {
		return oracleDatabaseMetadataIntegration;
	}

	public void setOracleDatabaseMetadataIntegration(
			DatabaseMetadataIntegration oracleDatabaseMetadataIntegration) {
		this.oracleDatabaseMetadataIntegration = oracleDatabaseMetadataIntegration;
	}

	public DatabaseMetadataIntegration getMysqlDatabaseMetadataIntegration() {
		return mysqlDatabaseMetadataIntegration;
	}

	public void setMysqlDatabaseMetadataIntegration(
			DatabaseMetadataIntegration mysqlDatabaseMetadataIntegration) {
		this.mysqlDatabaseMetadataIntegration = mysqlDatabaseMetadataIntegration;
	}
	
	
}
