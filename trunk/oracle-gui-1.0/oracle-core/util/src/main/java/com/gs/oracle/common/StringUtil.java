/**
 * 
 */
package com.gs.oracle.common;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	public static String mostCommonWord(String input){
		if(null == input || input.trim().length() == 0)
			return null;
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		String[] splitted = input.split(" ");
		for (String s : splitted) {
			if(wordCountMap.containsKey(s)){
				Integer v = wordCountMap.get(s);
				v = v + 1;
				wordCountMap.put(s, v);
			}else{
				wordCountMap.put(s, 1);
			}
		}
		String commonWord = "";
		Integer maxCount = 0;
		Set<String> keySet = wordCountMap.keySet();
		for (String key : keySet) {
			Integer v = wordCountMap.get(key);
			if(v.intValue() > maxCount.intValue()){
				maxCount = v;
				commonWord = key;
			}
		}
		return commonWord;
	}

	public static String encodeColor(Color color){
		char[] hex = new char[7];
		hex[0] = '#';
		String r = Integer.toHexString(color.getRed());
		if(r.length() <= 0)
			r = "00";
		if(r.length() >= 2){
			hex[1] = r.charAt(0);
			hex[2] = r.charAt(1);
		} else if(r.length() == 1){
			hex[1] = '0';
			hex[2] = r.charAt(0);
		}
		String g = Integer.toHexString(color.getGreen());
		if(g.length() <= 0)
			g = "00";
		if(g.length() >= 2){
			hex[3] = g.charAt(0);
			hex[4] = g.charAt(1);
		} else if(g.length() == 1){
			hex[3] = '0';
			hex[4] = g.charAt(0);
		}
		String b = Integer.toHexString(color.getBlue());
		if(b.length() <= 0)
			b = "00";
		if(b.length() >= 2){
			hex[5] = b.charAt(0);
			hex[6] = b.charAt(1);
		} else if(b.length() == 1){
			hex[5] = '0';
			hex[6] = b.charAt(0);
		}
		return new String(hex);
	}
}
