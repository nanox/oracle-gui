/**
 * 
 */
package com.gs.oracle.common;

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

}
