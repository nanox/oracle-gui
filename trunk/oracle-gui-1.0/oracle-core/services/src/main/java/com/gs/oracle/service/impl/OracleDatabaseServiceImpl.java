/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;
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



	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}



	@Override
	public Column getColumn(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Column> getColumnList(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Schema getCompleteSchema(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Database getDatabase(ConnectionProperties connectionProperties)
			throws ApplicationException {
		Database db = null;
		try {
			db = oracleDbGrabber.grabDatabase(connectionProperties.getConnection(), 
					connectionProperties.getDatabaseName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}



	@Override
	public List<Database> getDatabaseList(
			ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Schema getSchema(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Schema> getSchemaList(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Table getTable(ConnectionProperties connectionProperties)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
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
