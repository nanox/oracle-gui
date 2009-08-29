/**
 * 
 */
package com.gs.oracle.common;

/**
 * @author sabuj.das
 * 
 */
public class StringUtil {

	public static boolean hasValidContent(String str) {
		if (str == null)
			return false;
		return (str.trim().length() > 0);
	}
	
	public static String getFirstWord(String str){
		if(!hasValidContent(str))
			return "";
		str = str.trim();
		return str.split(" ")[0];
	}

}
