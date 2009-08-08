/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.command.GuiCommandConstants;
import com.gs.oracle.command.GuiEventHandler;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author Green Moon
 *
 */
public class MenuBarItems implements ActionListener, GuiCommandConstants{

	public static final String FILE_MENU_NAME = "FILE_MENU_NAME";
	public static final String FILE_MENU_TEXT = "File";
	public static final String NEW_CONN_FILE_MENU_ITEM = "NEW_CONN_FILE_MENU_ITEM";
	
	
	public static final String EDIT_MENU_NAME = "EDIT_MENU_NAME";
	public static final String FAVORITES_MENU_NAME = "FAVORITES_MENU_NAME";
	public static final String DB_MENU_NAME = "DB_MENU_NAME";
	public static final String TABLE_MENU_NAME = "TABLE_MENU_NAME";
	public static final String OBJECTS_MENU_NAME = "OBJECTS_MENU_NAME";
	public static final String TOOLS_MENU_NAME = "TOOLS_MENU_NAME";
	public static final String POWER_TOOLS_MENU_NAME = "POWER_TOOLS_MENU_NAME";
	public static final String WINDOW_MENU_NAME = "wINDOW_MENU_NAME";
	public static final String VIEW_MENU_NAME = "VIEW_MENU_NAME";
	public static final String HELP_MENU_NAME = "HELP_MENU_NAME";
	
	public static final String EDIT_MENU_TEXT = "Edit";
	public static final String FAVORITES_MENU_TEXT = "Favorites";
	public static final String DB_MENU_TEXT = "Database";
	public static final String TABLE_MENU_TEXT = "Table";
	public static final String OBJECTS_MENU_TEXT = "Objects";
	public static final String TOOLS_MENU_TEXT = "Tools";
	public static final String POWER_TOOLS_MENU_TEXT = "Powertools";
	public static final String WINDOW_MENU_TEXT = "Window";
	public static final String VIEW_MENU_TEXT = "View";
	public static final String HELP_MENU_TEXT = "Help";
	
	private Map<String, JMenu> menuMap = new HashMap<String, JMenu>();
	private Map<String, JMenuItem> menuItemMap = new HashMap<String, JMenuItem>();
	
	private JFrame parentFrame;
	
	public MenuBarItems(JFrame parent) {
		parentFrame = parent;
		createAllMenus();
	}
	
	private void createAllMenus() {
		addMenu(FILE_MENU_NAME, FILE_MENU_TEXT);
		addMenu(EDIT_MENU_NAME, EDIT_MENU_TEXT);
		addMenu(FAVORITES_MENU_NAME, FAVORITES_MENU_TEXT);
		addMenu(DB_MENU_NAME, DB_MENU_TEXT);
		addMenu(TABLE_MENU_NAME, TABLE_MENU_TEXT);
		addMenu(OBJECTS_MENU_NAME, OBJECTS_MENU_TEXT);
		addMenu(TOOLS_MENU_NAME, TOOLS_MENU_TEXT);
		addMenu(POWER_TOOLS_MENU_NAME, POWER_TOOLS_MENU_TEXT);
		addMenu(WINDOW_MENU_NAME, WINDOW_MENU_TEXT);
		addMenu(VIEW_MENU_NAME, VIEW_MENU_TEXT);
		addMenu(HELP_MENU_NAME, HELP_MENU_TEXT);
		// add items to file menu
		addMenuItem(FILE_MENU_NAME, NEW_CONN_FILE_MENU_ITEM, "New Connection", NEW_CONNECTION_ACT_CMD);
	}
	
	public void addMenusToMenuBar(JMenuBar menuBar){
		menuBar.add(menuMap.get(FILE_MENU_NAME));
		menuBar.add(menuMap.get(EDIT_MENU_NAME));
		menuBar.add(menuMap.get(FAVORITES_MENU_NAME));
		menuBar.add(menuMap.get(DB_MENU_NAME));
		menuBar.add(menuMap.get(TABLE_MENU_NAME));
		menuBar.add(menuMap.get(OBJECTS_MENU_NAME));
		menuBar.add(menuMap.get(TOOLS_MENU_NAME));
		menuBar.add(menuMap.get(POWER_TOOLS_MENU_NAME));
		menuBar.add(menuMap.get(WINDOW_MENU_NAME));
		menuBar.add(menuMap.get(VIEW_MENU_NAME));
		menuBar.add(menuMap.get(HELP_MENU_NAME));
	}

	private JMenu createMenu(String name, String txt){
		JMenu menu = new JMenu(txt);
		menu.setName(name);
		return menu;
	}
	
	private JMenuItem createMenuItem(String name, String txt, String actCmd, String imageName, int keyCode, int modifiers){
		JMenuItem item = new JMenuItem(txt);
		item.setName(name);
		item.setActionCommand(actCmd);
		item.addActionListener(this);
		if (null != imageName) {
			Icon image = new ImageIcon(MenuBarUtil.class
					.getResource(OracleGuiConstants.IMAGE_PATH + imageName));
			item.setIcon(image);
		}
		KeyStroke stroke = KeyStroke.getKeyStroke(keyCode, modifiers);
		if(stroke != null)
			item.setAccelerator(stroke);
		return item;
	}
	
	public void addMenu(String menuName, String text){
		menuMap.put(menuName, createMenu(menuName, text));
	}
	
	public JMenu getMenu(String menuName){
		return menuMap.get(menuName);
	}
	
	public void addMenuItem(String menuName, String itemName, String text){
		addMenuItem(menuName, itemName, text, "");
	}
	
	public void addMenuItem(String menuName, String itemName, String text, String actCmd){
		addMenuItem(menuName, itemName, text, actCmd, "");
	}
	
	public void addMenuItem(String menuName, String itemName, String text, String actCmd, String imageName){
		addMenuItem(menuName, itemName, text, actCmd, imageName, -1, -1);
	}
	
	/**
	 * 
	 * @param menuName Name of the menu
	 * @param itemName Current item name
	 * @param text current item text
	 * @param actCmd current item action command
	 * @param imageName current item image name
	 * @param keyCode KeyEvent.VK_* (key code)
	 * @param maskCode Ctrl, Alt, Shift
	 */
	public void addMenuItem(String menuName, String itemName, String text, String actCmd, String imageName, int keyCode, int maskCode){
		JMenuItem item = createMenuItem(itemName, text, actCmd, imageName, keyCode, maskCode);
		getMenu(menuName).add(item);
		menuItemMap.put(itemName, item);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		GuiEventHandler handler = new GuiEventHandler();
		handler.setParent(parentFrame);
		handler.actionPerformed(evt);
	}
}
