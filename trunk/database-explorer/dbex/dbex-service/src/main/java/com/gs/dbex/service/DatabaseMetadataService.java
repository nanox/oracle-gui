/**
 * 
 */
package com.gs.dbex.service;

import java.util.Set;

import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Schema;

/**
 * @author sabuj.das
 *
 */
public interface DatabaseMetadataService {

	String BEAN_NAME = "databaseMetadataService";
	
	public Set<Schema> getSchemaDetails(ConnectionProperties connectionProperties);
	
	
	
}
