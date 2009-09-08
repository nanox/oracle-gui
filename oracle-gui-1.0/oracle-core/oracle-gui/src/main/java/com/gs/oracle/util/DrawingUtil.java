/**
 * 
 */
package com.gs.oracle.util;

import java.awt.Graphics;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Table;

/**
 * @author sabuj.das
 *
 */
public class DrawingUtil implements OracleGuiConstants {

	public static void drawTable(Graphics graphics, Table table){
		
	}
	
	private static int calculateTableWidth(Graphics graphics, Table table){
		int charWidth = graphics.getFontMetrics().charWidth('H');
		int maxLength = 0;
		for (Column column : table.getColumnlist()) {
			String colName = column.getModelName();
			int length = colName.length();
			if(length > maxLength){
				maxLength = length;
			}
		}
		return 20 + (charWidth * maxLength);
	}
	
	private static int calculateTableHeight(Graphics graphics, Table table){
		int charHeight = graphics.getFontMetrics().getHeight();
		int lineCount = 1 + table.getColumnlist().size();
		return (charHeight + 4) * lineCount;
	}
}
