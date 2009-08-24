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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.ldap.HasControls;

import com.gs.oracle.model.Column;
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
				if(null != databaseName && !"".equals(databaseName))
					if(!databaseName.equalsIgnoreCase(cat)){
						continue;
					}
				Schema s = new Schema();
				s.setModelName(cat);
				ResultSet ret = databaseMetaData.getTables("", s.getModelName(), "%", new String[] {"TABLE"});
				while(ret.next()){
					String tn = ret.getString("TABLE_NAME");
					
					Table t = new Table();
					t.setModelName(tn);
					if(tn.startsWith("BIN$"))
						t.setDeleted(true);
					try{
						String pkColName = ""; 
						ResultSet pkRs = databaseMetaData.getPrimaryKeys("", s.getModelName(), tn);
						while(pkRs.next()){
							pkColName = pkRs.getString("COLUMN_NAME");
						}
						if(pkRs != null){
							pkRs.close();
						}
						Set<String> fkColSet = new HashSet<String>();
						ResultSet fkRs = databaseMetaData.getImportedKeys("", s.getModelName(), tn);
						while(fkRs.next()){
							fkColSet.add(fkRs.getString("FKCOLUMN_NAME"));
						}
						if(fkRs != null){
							fkRs.close();
						}
						
						ResultSet cet = databaseMetaData.getColumns("", s.getModelName(), t.getModelName(), "%");
						while(cet.next()){
							Column c = new Column();
							c.setModelName(cet.getString("COLUMN_NAME"));
							if(c.getModelName().equalsIgnoreCase(pkColName)){
								c.setPrimaryKey(true);
							}
							if(fkColSet.contains(c.getModelName())){
								c.setForeignKey(true);
							}
							c.setTypeName(cet.getString("TYPE_NAME"));
							String nulAble = cet.getString("IS_NULLABLE");
							if("YES".equalsIgnoreCase(nulAble)){
								c.setNullable(true);
							}else{
								c.setNullable(false);
							}
							
							t.getColumnlist().add(c);
						}
						if(cet != null){
							cet.close();
						}
					}catch(Exception e){
						
					}
					s.getTableList().add(t);
				}
				if(ret != null){
					ret.close();
				}
				schemaList.add(s);
				
			}
			if(rs != null){
				rs.close();
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
