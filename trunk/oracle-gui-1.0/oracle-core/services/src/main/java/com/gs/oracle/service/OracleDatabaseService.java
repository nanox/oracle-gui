/**
 * 
 */
package com.gs.oracle.service;

import java.util.List;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.enums.ReadDepthEnum;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Database;
import com.gs.oracle.model.Schema;
import com.gs.oracle.model.Table;

/**
 * @author sabuj.das
 *
 */
public interface OracleDatabaseService {

	public Schema getSchema(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public List<Schema> getSchemaList(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public Table getTable(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public List<Table> getTableList(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public Column getColumn(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public List<Column> getColumnList(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public Schema getCompleteSchema(ConnectionProperties connectionProperties) throws ApplicationException;
	
	public Database getDatabase(ConnectionProperties connectionProperties, ReadDepthEnum readDepth) throws ApplicationException;
	
	public List<Database> getDatabaseList(ConnectionProperties connectionProperties) throws ApplicationException;
}
