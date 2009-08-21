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

}
