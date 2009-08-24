/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.ResultSet;
import java.util.List;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.SqlQuery;
import com.gs.oracle.service.QueryExecutionService;

/**
 * @author Green Moon
 *
 */
public class QueryExecutionServiceImpl implements QueryExecutionService {

	
	@Override
	public int executeNonQuery(SqlQuery sqlQuery) throws ApplicationException {
		
		return 0;
	}

	@Override
	public void executeBatch(List<SqlQuery> queryList)
			throws ApplicationException {
		
		
	}

	@Override
	public ResultSet executeSelect(SqlQuery sqlQuery)
			throws ApplicationException {
		
		return null;
	}

	
}
