/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: ToolbarButtons.java
 *	Type	: com.gs.oracle.comps.ToolbarButtons.java
 *	Date	: Aug 3, 2009	12:23:26 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.comps;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class ToolbarButtons {
	public static final String _TOOLBAR_BUTTON = "";
	
	public static final String NEW_CONNECTION_TOOLBAR_BUTTON = "NEW_CONNECTION_TOOLBAR_BUTTON";
	public static final String DISCONNECT_TOOLBAR_BUTTON = "DISCONNECT_TOOLBAR_BUTTON";
	public static final String DB_SYNC_TOOLBAR_BUTTON = "DB_SYNC_TOOLBAR_BUTTON";
	
	public static final String EXECUITE_CURRENT_SQL_TOOLBAR_BUTTON = "EXECUITE_CURRENT_SQL_TOOLBAR_BUTTON";
	public static final String EXECUITE_ALL_SQL_TOOLBAR_BUTTON = "EXECUITE_ALL_SQL_TOOLBAR_BUTTON";
//	public static final String _TOOLBAR_BUTTON = "";
//	public static final String _TOOLBAR_BUTTON = "";
//	public static final String _TOOLBAR_BUTTON = "";
//	public static final String _TOOLBAR_BUTTON = "";
//	public static final String _TOOLBAR_BUTTON = "";
//	public static final String _TOOLBAR_BUTTON = "";

	private Map<String, JButton> toolbarButtonsMap;
	
	public ToolbarButtons() {
		toolbarButtonsMap = new HashMap<String, JButton>();
	}
	
	public static JButton createToolbarButton(String text, String command, String imageName){
		JButton button = new JButton(text);
		button.setActionCommand(command);
		if(null != imageName){
			Icon image = new ImageIcon(MenuBarUtil.class.getResource(OracleGuiConstants.IMAGE_PATH + imageName));
			button.setIcon(image);
		}
		return button;
	}
	
	public void addButton(String name, JButton button){
		toolbarButtonsMap.put(name, button);
	}
	public JButton getButton(String name){
		return toolbarButtonsMap.get(name);
	}
}
