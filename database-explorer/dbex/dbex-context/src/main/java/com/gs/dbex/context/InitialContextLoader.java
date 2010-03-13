/**
 * 
 */
package com.gs.dbex.context;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.dbex.common.enums.SupportedDatabaseEnum;


/**
 * @author sabuj.das
 *
 */
public class InitialContextLoader {

	private static String[] oracleAppContextFiles = new String[] {
		"/context/dbex-context.xml",
		"/context/oracle-integration-context.xml",
		"/context/oracle-service-context.xml"
	};
	
	private ApplicationContext applicationContext = null;
	
	public void loadInitialContext(String databaseType){
		if(SupportedDatabaseEnum.ORACLE_9i.getCode().equals(databaseType)){
			applicationContext =
				new ClassPathXmlApplicationContext(oracleAppContextFiles);
		}
	}
	
	public <T> T getBean(String name){
		if(applicationContext != null){
			return (T) applicationContext.getBean(name);
		}
		return null;
	}
	
	public static void main(String[] args) {
		new InitialContextLoader().loadInitialContext("");
	}
}
