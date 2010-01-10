/**
 * 
 */
package com.gs.oracle.service.impl;

import java.io.BufferedReader;
import java.sql.Clob;
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

	
	public void executeBatch(List<SqlQuery> queryList)
			throws ApplicationException {
		if(connectionProperties == null){
			return ;
		}
		
	}

	
	public ResultSet executeSelect(SqlQuery sqlQuery)
			throws ApplicationException {
		
		return null;
	}
	
	public String generateDdlForTable(String tableName) throws ApplicationException{
		if(getConnectionProperties() == null){
			throw new ApplicationException("Cannot process : insufficient details.");
		}
		String query = "SELECT DBMS_METADATA.GET_DDL('TABLE','" + tableName
			+"') FROM dual";
		Connection con = null;
		StringBuffer ddlBuffer = new StringBuffer();
		try{
			con = getConnectionProperties().getDataSource().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			Clob clob = null;
			while(rs.next()){
				clob = rs.getClob(1);
			}
			if(clob != null){
				BufferedReader br = new BufferedReader(
						clob.getCharacterStream());
				int count = 0;
				char[] cbuf = new char[100];
				while((count = br.read(cbuf, 0, cbuf.length)) >= 0){
					ddlBuffer.append(cbuf, 0, count);
				}
			}
		}catch(SQLException e){
			throw new ApplicationException(e.getMessage());
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ddlBuffer.toString();
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	
}
