/**
 * 
 */
package com.gs.oracle.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.BinaryRefAddr;

/**
 * @author Green Moon
 *
 */
public class JdbcUtil {

	
	/**
	 * Closes the connection and print the stack-trace if there is any exception.
	 * @param connection
	 */
	public static void closeConnection(Connection connection){
		closeConnection(connection, false);
	}
	/**
	 * Closes the connection and print the stack-trace if there is any exception 
	 * and the boolean variable <code>silent</code> is <code><b>true</b></code>.
	 * 
	 * @param connection
	 * @param silent
	 */
	public static void closeConnection(Connection connection, boolean silent){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				if(!silent){
					//TODO: add logger statement
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Closes the resultSet and print the stack-trace if there is any exception.
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet){
		closeResultSet(resultSet, false);
	}
	/**
	 * Closes the resultSet and print the stack-trace if there is any exception 
	 * and the boolean variable <code>silent</code> is <code><b>true</b></code>.
	 * 
	 * @param resultSet
	 * @param silent
	 */
	public static void closeResultSet(ResultSet resultSet, boolean silent){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				if(!silent){
					//TODO: add logger statement
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Class getJavaClassForSqlType(int type){
		Class clazz = null;
		switch(type){
			case Types.SMALLINT:
				clazz =  java.lang.Short.class;
				break;
			case Types.INTEGER:
				clazz =  java.lang.Integer.class;
				break;
			case Types.DECIMAL:
				clazz =  java.math.BigDecimal.class;
				break;
			case Types.BIGINT:
				clazz =  java.lang.Long.class;
				break;
			case Types.REAL:
				clazz =  java.lang.Float.class;
				break;
			case Types.DOUBLE:
				clazz =  java.lang.Double.class;
				break;
			case Types.CHAR:
			case Types.VARCHAR:
				clazz =  java.lang.String.class;
				break;
			case Types.DATE:
				clazz =  java.sql.Date.class;
				break;
			case Types.TIME:
				clazz =  java.sql.Time.class;
				break;
			case Types.TIMESTAMP:
				clazz =  java.sql.Timestamp.class;
				break;
			case Types.NUMERIC:
				clazz =  java.lang.Long.class;
				break;
			case Types.BLOB:
				clazz =  java.sql.Blob.class;
				break;
			case Types.CLOB:
				clazz =  java.sql.Clob.class;
				break;
			
			default:
				clazz =  java.lang.String.class;
				break;
		}
		return clazz;
	}
	public static String readFromBlob(Blob data) throws SQLException, IOException {
		if(data == null){
			return "";
		}
		StringBuffer ddlBuffer = new StringBuffer();
		BufferedInputStream bi = new BufferedInputStream(
				data.getBinaryStream());
		int count = 0;
		byte[] cbuf = new byte[100];
		while((count = bi.read(cbuf, 0, cbuf.length)) >= 0){
			ddlBuffer.append(new String(cbuf, 0, count));
		}
		return null;
	}
	
}
