/**
 * 
 */
package com.gs.syntax.mapping;

import java.io.Serializable;

/**
 * @author Sabuj Das
 *
 */
public class WordFont implements Serializable {

	private String fontName;
	private Integer fontSize;
	private FontStyle fontStyle;
	
	public WordFont() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fontName
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * @return the fontSize
	 */
	public Integer getFontSize() {
		return fontSize;
	}

	/**
	 * @return the fontStyle
	 */
	public FontStyle getFontStyle() {
		return fontStyle;
	}

	/**
	 * @param fontName the fontName to set
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @param fontStyle the fontStyle to set
	 */
	public void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}
	
	
}
