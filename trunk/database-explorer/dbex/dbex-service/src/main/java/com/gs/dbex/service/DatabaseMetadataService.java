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

	
	public Set<Schema> getSchemaDetails();
	
	public ConnectionProperties getConnectionProperties(); 
	public void setConnectionProperties(ConnectionProperties connectionProperties);
	
}
