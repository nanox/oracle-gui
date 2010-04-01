/**
 * 
 */
package com.gs.dbex.core.schema.grabber;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.core.SchemaGrabber;
import com.gs.dbex.model.db.Column;
import com.gs.dbex.model.db.Database;
import com.gs.dbex.model.db.ForeignKey;
import com.gs.dbex.model.db.PrimaryKey;
import com.gs.dbex.model.db.Schema;
import com.gs.dbex.model.db.Table;

/**
 * @author sabuj.das
 *
 */
public class SchemaDBGrabberImpl implements SchemaGrabber {

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#getAvailableSchemaNames(java.sql.Connection)
	 */
	public Set<String> getAvailableSchemaNames(Connection connection)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#getColumnList(com.gs.dbex.model.db.Table, java.sql.Connection, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public List<Column> getColumnList(Table table, Connection connection,
			ReadDepthEnum readDepth) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#getColumnList(java.lang.String, java.lang.String, java.sql.Connection, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public List<Column> getColumnList(String schemaName, String tableName,
			Connection connection, ReadDepthEnum readDepth) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#grabDatabaseBySchema(java.sql.Connection, java.lang.String, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public Database grabDatabaseBySchema(Connection connection,
			String databaseName, ReadDepthEnum readDepth) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#grabExportedKeys(java.sql.Connection, java.lang.String, java.lang.String, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public List<ForeignKey> grabExportedKeys(Connection connection,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#grabImportedKeys(java.sql.Connection, java.lang.String, java.lang.String, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public List<ForeignKey> grabImportedKeys(Connection connection,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#grabPrimaryKeys(java.sql.Connection, java.lang.String, java.lang.String, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public List<PrimaryKey> grabPrimaryKeys(Connection connection,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#grabSchema(java.sql.Connection, java.lang.String)
	 */
	public Schema grabSchema(Connection connection, String schemaName)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.SchemaGrabber#grabTable(java.sql.Connection, java.lang.String, java.lang.String, com.gs.dbex.common.enums.ReadDepthEnum)
	 */
	public Table grabTable(Connection connection, String schemaName,
			String tableName, ReadDepthEnum readDepth) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gs.dbex.core.DbGrabber#grabSqlKeyWords(java.sql.Connection)
	 */
	public String grabSqlKeyWords(Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
