/**
 * 
 */
package com.gs.dbex.service.impl;

import com.gs.dbex.common.enums.DatabaseTypeEnum;
import com.gs.dbex.integration.DatabaseMetadataIntegration;
import com.gs.dbex.integration.IntegrationBeanFactory;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.service.DatabaseConnectionService;

/**
 * @author sabuj.das
 *
 */
public class DatabaseConnectionServiceImpl implements DatabaseConnectionService {

	
	

	public Boolean closeConnection(ConnectionProperties connectionProperties) {
		// TODO Auto-generated method stub
		return null;
	}


	public Boolean connectToDatabase(ConnectionProperties connectionProperties) {
		System.out.println("DatabaseConnectionServiceImpl :: connectToDatabase");
		DatabaseMetadataIntegration integration = IntegrationBeanFactory.getBeanFactory()
			.getDatabaseMetadataIntegration(DatabaseTypeEnum.MYSQL);
		if(integration != null){
			integration.readDatabase(null, null);
		}
		return null;
	}

}
