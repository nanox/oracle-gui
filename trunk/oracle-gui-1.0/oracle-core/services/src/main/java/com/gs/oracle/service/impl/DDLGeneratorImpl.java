/**
 * 
 */
package com.gs.oracle.service.impl;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.model.Table;
import com.gs.oracle.service.DDLGenerator;

/**
 * @author sabuj.das
 *
 */
public class DDLGeneratorImpl implements DDLGenerator {

	
	public String generateDDLForTable(
			ConnectionProperties connectionProperties, Table table)
			throws ApplicationException {
		
		return null;
	}

}
