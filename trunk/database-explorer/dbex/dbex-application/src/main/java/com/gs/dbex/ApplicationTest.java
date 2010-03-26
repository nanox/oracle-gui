/**
 * 
 */
package com.gs.dbex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.dbex.common.exception.DbexException;
import com.gs.dbex.service.DatabaseConnectionService;
import com.gs.dbex.service.DbexServiceBeanFactory;

/**
 * @author sabuj.das
 * 
 */
public class ApplicationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					new String[]{"/context/dbex-service-context.xml", "/context/dbex-integration-context.xml"});

			DatabaseConnectionService connectionService = DbexServiceBeanFactory.getBeanFactory().getDatabaseConnectionService();
			if (connectionService != null) {
				System.out.println("service done");
				connectionService.connectToDatabase(null);
			}
		} catch (Exception e) {
			if(e instanceof DbexException){
				DbexException de = (DbexException) e;
				if(de.getExceptionCode() != null){
					System.out.println("CODE: "+de.getExceptionCode());
				} else {
					System.out.println("Message: "+ de.getExceptionMessage());
				}
			}
		}
	}

}
