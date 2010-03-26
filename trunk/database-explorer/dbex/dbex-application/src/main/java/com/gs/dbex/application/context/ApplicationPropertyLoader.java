/**
 * 
 */
package com.gs.dbex.application.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author sabuj.das
 *
 */
public class ApplicationPropertyLoader {

	private static Logger logger = Logger.getLogger(ApplicationPropertyLoader.class);
	
	public static Properties loadProperties(String filename){
		if(logger.isDebugEnabled()){
			logger.debug("Load properties ");
		}
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		properties.load(inStream);
		return properties;
	}
}
