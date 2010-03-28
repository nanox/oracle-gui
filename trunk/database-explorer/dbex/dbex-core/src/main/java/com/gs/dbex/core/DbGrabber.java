/**
 * 
 */
package com.gs.dbex.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.model.db.Column;
import com.gs.dbex.model.db.ForeignKey;
import com.gs.dbex.model.db.PrimaryKey;
import com.gs.dbex.model.db.Table;

/**
 * @author sabuj.das
 * 
 */
public abstract class DbGrabber {

	public abstract String grabSqlKeyWords(Connection connection)
			throws SQLException;


	public abstract Table grabTable(Connection connection, String schemaName,
			String tableName, ReadDepthEnum readDepth);

	public abstract List<Column> getColumnList(Table table,
			Connection connection, ReadDepthEnum readDepth) throws SQLException;

	public abstract List<Column> getColumnList(String schemaName,
			String tableName, Connection connection, ReadDepthEnum readDepth)
			throws SQLException;

	public abstract List<PrimaryKey> grabPrimaryKeys(Connection connection,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException;

	public abstract List<ForeignKey> grabImportedKeys(Connection connection,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException;

	public abstract List<ForeignKey> grabExportedKeys(Connection connection,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException;

	
}
