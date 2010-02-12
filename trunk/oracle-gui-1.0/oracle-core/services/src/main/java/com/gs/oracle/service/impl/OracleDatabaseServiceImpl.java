/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.enums.ReadDepthEnum;
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Database;
import com.gs.oracle.model.Schema;
import com.gs.oracle.model.Table;
import com.gs.oracle.service.DatabaseConnectionService;
import com.gs.oracle.service.OracleDatabaseService;

/**
 * @author sabuj.das
 *
 */
public class OracleDatabaseServiceImpl implements OracleDatabaseService {
	
	private OracleDbGrabber oracleDbGrabber;
	private DatabaseConnectionService databaseConnectionService;
	
	public OracleDatabaseServiceImpl() {
		oracleDbGrabber = new OracleDbGrabber();
		databaseConnectionService = new DatabaseConnectionServiceImpl();
	}



	
	protected void finalize() throws Throwable {
		super.finalize();
	}



	
	public Column getColumn(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public List<Column> getColumnList(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public Schema getCompleteSchema(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public Database getDatabase(ConnectionProperties connectionProperties, ReadDepthEnum readDepth)
			throws ApplicationException {
		Database db = null;
		Connection connection = null;
		try {
			connection = connectionProperties.getDataSource().getConnection();
			db = oracleDbGrabber.grabDatabase(connection, 
					connectionProperties.getDatabaseName(), readDepth);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;
	}



	
	public List<Database> getDatabaseList(
			ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public Schema getSchema(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public List<Schema> getSchemaList(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public Table getTable(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	
	public List<Table> getTableList(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	public OracleDbGrabber getOracleDbGrabber() {
		return oracleDbGrabber;
	}

	public void setOracleDbGrabber(OracleDbGrabber oracleDbGrabber) {
		this.oracleDbGrabber = oracleDbGrabber;
	}

	
	
}
