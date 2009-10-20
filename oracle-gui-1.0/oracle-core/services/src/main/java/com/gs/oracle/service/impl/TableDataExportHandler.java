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
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.io.IOUtils;
import com.gs.oracle.jdbc.JdbcUtil;
import com.sun.jmx.snmp.Timestamp;

/**
 * @author sabuj.das
 *
 */
public class TableDataExportHandler {
	
	public static final String INSERT_DATE_FORMAT = "dd-MMM-yy HH.mm.ss.SSS aaa";
	private static final String SQL_DATE_FORMAT = "'DD-MON-RR HH.MI.SS.FF AM'";
	private static final String SQL_DATE_FUNCTION = "to_timestamp";
	
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
					int columnType = metaData.getColumnType(i);
					Object o = resultSet.getObject(i);
					String value = "null";
					if(o != null)
						value = o.toString();
					rowResultBuffer.append(getStringForObject(o, columnType));
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
	
	public static String getStringForObject(Object o, int type){
		String value = "";
		if(o == null){
			return "null";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(INSERT_DATE_FORMAT);
		switch(type){
			case Types.SMALLINT:
			case Types.INTEGER:
			case Types.DECIMAL:
			case Types.BIGINT:
			case Types.REAL:
			case Types.DOUBLE:
			case Types.NUMERIC:
				value = o.toString();
				break;
			
			case Types.DATE:
			case Types.TIME:
			case Types.TIMESTAMP:
				java.sql.Timestamp sqlTimestamp = (java.sql.Timestamp) o;
				java.util.Date utilDate = new java.util.Date();
				utilDate.setTime(sqlTimestamp.getTime());
				value = SQL_DATE_FUNCTION + "('" +
					dateFormat.format(utilDate) + "', " + SQL_DATE_FORMAT + ")";
				break;
			
			default:
				value = "'" + o.toString() + "'";
				break;
		}
		return value;
	}
	
}