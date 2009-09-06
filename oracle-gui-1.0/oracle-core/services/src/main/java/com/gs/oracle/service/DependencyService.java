/**
 * 
 */
package com.gs.oracle.service;

import java.sql.Connection;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.model.TableDependency;

/**
 * @author Green Moon
 *
 */
public interface DependencyService {

	public TableDependency generateTableDependency(Connection connection,
			String schemaName, String tableName) throws ApplicationException; 
	
}
