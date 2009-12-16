package com.gs.oracle.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

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
	
	public static void closeWriter(Writer writer){
		if(writer != null){
			try {
				writer.close();
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
	
	public static void writeAsText(File file, String message){
		BufferedWriter bw=null;
		try{
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(message);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			IOUtils.closeWriter(bw);
		}
	}
	
	public static String readAsText(File file){
		StringBuffer buffer = new StringBuffer();
		
		if(file.exists()){
			BufferedReader reader = null;
			try {
				String line = "";
				reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null){
					buffer.append(line + "\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			closeReader(reader);
		}
		
		return buffer.toString();
	}
}
