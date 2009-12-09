/**
 * 
 */
package com.gs.syntax.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sabuj.das
 *
 */
public class WordStyles implements Serializable {

	private List<WordStyle> wordStyleList;
	
	public WordStyles() {
		wordStyleList = new ArrayList<WordStyle>();
	}

	/**
	 * @return the wordStyleList
	 */
	public List<WordStyle> getWordStyleList() {
		return wordStyleList;
	}

	/**
	 * @param wordStyleList the wordStyleList to set
	 */
	public void setWordStyleList(List<WordStyle> wordStyleList) {
		this.wordStyleList = wordStyleList;
	}
	
	
}
