package com.gs.oracle.io;

import java.io.IOException;
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
}
