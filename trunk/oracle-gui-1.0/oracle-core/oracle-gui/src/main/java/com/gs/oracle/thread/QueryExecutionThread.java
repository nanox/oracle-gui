/**
 * 
 */
package com.gs.oracle.thread;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.QueryTypeEnum;
import com.gs.oracle.SqlQuery;
import com.gs.oracle.comps.ResultSetTableModelFactory;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.service.QueryExecutionService;
import com.gs.oracle.service.impl.QueryExecutionServiceImpl;

/**
 * @author sabuj
 * 
 */
public class QueryExecutionThread<V>
		implements Runnable {

	
	private ConnectionProperties connectionProperties;
	private SqlQuery sqlQuery;
	
	private QueryExecutionService queryExecutionService;

	private V returnedValue;


	public QueryExecutionThread(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public SqlQuery getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(SqlQuery sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public void run() {
		
		if(QueryTypeEnum.SELECT.equals(sqlQuery.getQueryType())){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
					} catch (SQLException ex) {
						
					} catch (Exception ex) {
						
					}
				}
			});
		}
		else {
			queryExecutionService = new QueryExecutionServiceImpl(getConnectionProperties());
			try {
				int row = queryExecutionService.executeNonQuery(sqlQuery);
				
			} catch (ApplicationException ex) {
				
			} catch (Exception ex) {
				
			}
		}
		
		
	}


	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public QueryExecutionService getQueryExecutionService() {
		return queryExecutionService;
	}

	public void setQueryExecutionService(QueryExecutionService queryExecutionService) {
		this.queryExecutionService = queryExecutionService;
	}
	
	

}
