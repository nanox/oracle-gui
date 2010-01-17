/**
 * 
 */
package com.gs.oracle.dbtool;

import java.sql.Connection;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.ApplicationExceptionCodes;

/**
 * @author sabuj.das
 * 
 */
public class DDLGenerator implements ApplicationExceptionCodes{

	public String generateDDLForTable(Connection connection, String schemaName,
			String tableName) throws ApplicationException {
		if(connection == null){
			throw new ApplicationException(NOT_CONNECTED_TO_DB);
		}
		return "";
	}
	
	
}
