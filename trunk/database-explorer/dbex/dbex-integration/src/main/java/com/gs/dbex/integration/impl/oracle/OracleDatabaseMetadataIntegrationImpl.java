/**
 * 
 */
package com.gs.dbex.integration.impl.oracle;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.integration.impl.DatabaseMetadataIntegrationImpl;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Database;
import com.gs.dbex.model.db.Schema;
import com.gs.dbex.model.db.Table;

/**
 * @author Sabuj.das
 *
 */
public class OracleDatabaseMetadataIntegrationImpl extends
		DatabaseMetadataIntegrationImpl {

	
	public Database readDatabase(ConnectionProperties connectionProperties,
			ReadDepthEnum readDepthEnum) {
		System.out.println("Reading OracleDatabaseMetadata");
		return null;
	}

	
	public Schema readSchema(ConnectionProperties connectionProperties,
			String schemaName, ReadDepthEnum readDepthEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Table readTable(ConnectionProperties connectionProperties,
			String schemaName, String tableName, ReadDepthEnum readDepthEnum) {
		// TODO Auto-generated method stub
		return null;
	}

}
