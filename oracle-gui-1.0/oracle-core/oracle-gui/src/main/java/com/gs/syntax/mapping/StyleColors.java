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
public class StyleColors implements Serializable {

	private List<StyleColor> colorList = new ArrayList<StyleColor>();
	
	public StyleColors() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the colorList
	 */
	public List<StyleColor> getColorList() {
		return colorList;
	}

	/**
	 * @param colorList the colorList to set
	 */
	public void setColorList(List<StyleColor> colorList) {
		this.colorList = colorList;
	}
	
	
}
