/**
 * 
 */
package com.gs.oracle.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
