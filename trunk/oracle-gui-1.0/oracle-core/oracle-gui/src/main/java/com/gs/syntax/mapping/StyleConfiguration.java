/**
 * 
 */
package com.gs.syntax.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabuj Das
 *
 */
public class StyleConfiguration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3212545444521071784L;
	
	private List<SyntaxStyle> syntaxStyleList;
	
	public StyleConfiguration() {
		syntaxStyleList = new ArrayList<SyntaxStyle>();
	}

	/**
	 * @return the syntaxStyleList
	 */
	public List<SyntaxStyle> getSyntaxStyleList() {
		return syntaxStyleList;
	}

	/**
	 * @param syntaxStyleList the syntaxStyleList to set
	 */
	public void setSyntaxStyleList(List<SyntaxStyle> syntaxStyleList) {
		this.syntaxStyleList = syntaxStyleList;
	}
	
	

}
