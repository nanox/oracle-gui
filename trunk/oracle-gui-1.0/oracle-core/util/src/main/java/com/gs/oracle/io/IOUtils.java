package com.gs.oracle.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class IOUtils {

	
	public static void closeReader(Reader reader){
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				//e.printStackTrace();
				// ignore
			}
		}
	}
	
	/**
	 * Loads a file from CLASSPATH
	 * @param fileName : String file name
	 * @return : Returns the input stream of the resource
	 */
	public static InputStream getResourceAsStream(String fileName) {
		InputStream inputStream = IOUtils.class.getClassLoader().getResourceAsStream(fileName);
		if (inputStream == null) {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		}
		if (inputStream == null) {
			inputStream = IOUtils.class.getResourceAsStream(fileName);
		}
		if (inputStream == null) {
			inputStream = Object.class.getClassLoader().getResourceAsStream(fileName);
		}
		return inputStream;
	}
	
	public static void mkdirs(String directory){
		File dir = new File(directory);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	
	public static File mkfile(String fileName){
		File file = new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
