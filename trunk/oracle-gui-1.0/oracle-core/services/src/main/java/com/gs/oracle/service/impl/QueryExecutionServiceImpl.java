/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.SqlQuery;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.service.QueryExecutionService;

/**
 * @author Green Moon
 *
 */
public class QueryExecutionServiceImpl implements QueryExecutionService {

	private ConnectionProperties connectionProperties;
	
	public QueryExecutionServiceImpl(ConnectionProperties properties) {
		connectionProperties = properties;
	}
	
	@Override
	public int executeNonQuery(SqlQuery sqlQuery) throws ApplicationException {
		if(connectionProperties == null){
			return 0;
		}
		int update = 0;
		Connection con = null;
		try{
			con = getConnectionProperties().getDataSource().getConnection();
			Statement stmt = con.createStatement();
			update =  stmt.executeUpdate(sqlQuery.getQuery());
			if(stmt != null){
				stmt.close();
			}
		}catch(SQLException e){
			throw new ApplicationException("sdasd", e.getMessage());
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return update;
	}

	@Override
	public void executeBatch(List<SqlQuery> queryList)
			throws ApplicationException {
		if(connectionProperties == null){
			return ;
		}
		
	}

	@Override
	public ResultSet executeSelect(SqlQuery sqlQuery)
			throws ApplicationException {
		
		return null;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	
}
