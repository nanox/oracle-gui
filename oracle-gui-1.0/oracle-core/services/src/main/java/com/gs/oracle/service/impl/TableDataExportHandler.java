/**
 * 
 */
package com.gs.oracle.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.io.IOUtils;

/**
 * @author sabuj.das
 *
 */
public class TableDataExportHandler {
	
	private ConnectionProperties connectionProperties;
	private String schemaName, tableName;
	
	public TableDataExportHandler(ConnectionProperties cp) {
		connectionProperties = cp;
	}

	/**
	 * @return the connectionProperties
	 */
	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	/**
	 * @param connectionProperties the connectionProperties to set
	 */
	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	/**
	 * @return the schemaName
	 */
	public String getSchemaName() {
		return schemaName;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param schemaName the schemaName to set
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void exportToInsertStatement(String exportQuery, File outputFile){
		Connection connection = null;
		ResultSet resultSet = null;
		List<StringBuffer> insertStmtList = new ArrayList<StringBuffer>();
		StringBuffer resultBuffer = new StringBuffer("INSERT INTO ")
		.append(schemaName).append(".").append(tableName).append(" ( ");
		try{
			connection = getConnectionProperties().getDataSource().getConnection();
			PreparedStatement ps = connection.prepareStatement(exportQuery,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = ps.executeQuery();
			
			if(resultSet == null){
				return;
			}
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			for(int i = 1; i<= columnCount; i++){
				resultBuffer.append(metaData.getColumnName(i));
				if(i != columnCount){
					resultBuffer.append(", ");
				}
			}
			resultBuffer.append(" ) VALUES ( "); 
			resultSet.first();
			while(resultSet.next()){
				StringBuffer rowResultBuffer = new StringBuffer(resultBuffer.toString());
				for(int i = 1; i<= columnCount; i++){
					Object o = resultSet.getObject(i);
					String value = "";
					if(o != null)
						value = o.toString();
					rowResultBuffer.append("'").append(value).append("'");
					if(i != columnCount){
						rowResultBuffer.append(", ");
					}else{
						rowResultBuffer.append(" );");
					}
				}
				insertStmtList.add(rowResultBuffer);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(insertStmtList.size() > 0){
			StringBuffer b = new StringBuffer();
			for (StringBuffer sb : insertStmtList) {
				b.append(sb).append("\n");
			}
			IOUtils.writeAsText(outputFile, b.toString());
			
		}
	}
}
