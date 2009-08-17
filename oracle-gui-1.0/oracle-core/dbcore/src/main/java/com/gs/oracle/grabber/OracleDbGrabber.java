/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: OracleDbGrabber.java
 *	Type	: com.gs.oracle.grabber.OracleDbGrabber.java
 *	Date	: Jul 29, 2009	3:21:27 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.grabber;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.oracle.model.Database;
import com.gs.oracle.model.Schema;
import com.gs.oracle.model.Table;

/**
 * @author Green Moon
 *
 */
public class OracleDbGrabber {
	
	public OracleDbGrabber() {
		// TODO Auto-generated constructor stub
	}
	
	public Database grabDatabase(Connection connection, String databaseName) throws SQLException{
		if(connection == null){
			return null;
		}
		Database db = new Database();
		db.setModelName(databaseName);
		List<Schema> schemaList = new ArrayList<Schema>();
		
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		if(databaseMetaData != null){
			ResultSet rs = databaseMetaData.getSchemas();
			while(rs.next()){
				String cat = rs.getString("TABLE_SCHEM");
				Schema s = new Schema();
				s.setModelName(cat);
				ResultSet ret = databaseMetaData.getTables("", s.getModelName(), "%", new String[] {"TABLE"});
				while(ret.next()){
					Table t = new Table();
					t.setModelName(ret.getString("TABLE_NAME"));
					s.getTableList().add(t);
				}
				schemaList.add(s);
			}
		}
		db.setSchemaList(schemaList);
		return db;
	}

	public Schema grabSchema(Connection connection, String schemaName) throws SQLException{
		if(connection == null)
			return null;
		Schema schema = new Schema();
		
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		if(databaseMetaData != null){
			ResultSet rs = databaseMetaData.getCatalogs();
			while(rs.next()){
				String cat = rs.getString("TABLE_CAT");
				if(cat.equalsIgnoreCase(schemaName)){
					schema.setModelName(schemaName);
					break;
				}
			}
		}
		return schema;
	}
	
}
