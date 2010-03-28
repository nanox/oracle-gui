/**
 * 
 */
package com.gs.dbex.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.model.db.Database;
import com.gs.dbex.model.db.Schema;

/**
 * @author sabuj.das
 * 
 */
public abstract class SchemaGrabber extends DbGrabber {

	public abstract Database grabDatabaseBySchema(Connection connection,
			String databaseName, ReadDepthEnum readDepth) throws SQLException;

	public abstract Schema grabSchema(Connection connection, String schemaName)
			throws SQLException;

	public abstract Set<String> getAvailableSchemaNames(Connection connection)
			throws SQLException;
}
