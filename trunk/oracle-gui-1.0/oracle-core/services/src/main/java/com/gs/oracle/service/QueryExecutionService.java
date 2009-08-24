/**
 * 
 */
package com.gs.oracle.service;

import java.sql.ResultSet;
import java.util.List;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.SqlQuery;

/**
 * @author Green Moon
 *
 */
public interface QueryExecutionService {

	public int executeNonQuery(SqlQuery sqlQuery) throws ApplicationException;
	
	public void executeBatch(List<SqlQuery> queryList) throws ApplicationException;
	
	public ResultSet executeSelect(SqlQuery sqlQuery) throws ApplicationException;
	
}
