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
public abstract class CatalogGrabber extends DbGrabber {

	public abstract Database grabDatabaseByCatalog(Connection connection,
			String databaseName, ReadDepthEnum readDepth) throws SQLException;

	public abstract Schema grabCatalog(Connection connection, String catalogName)
			throws SQLException;

	public abstract Set<String> getAvailableCatalogNames(Connection connection)
			throws SQLException;
}
