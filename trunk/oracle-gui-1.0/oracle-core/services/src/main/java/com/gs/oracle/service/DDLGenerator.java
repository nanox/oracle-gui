/**
 * 
 */
package com.gs.oracle.service;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.model.Table;

/**
 * @author sabuj.das
 *
 */
public interface DDLGenerator {

	public String generateDDLForTable(ConnectionProperties connectionProperties,
			Table table) throws ApplicationException;
}
