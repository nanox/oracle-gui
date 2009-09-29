/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
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
	
	public static final String FILE_MENU_TEXT = "File";
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
	
// File menu Items
	public static final String NEW_CONN_FILE_MENU_ITEM = "NEW_CONN_FILE_MENU_ITEM";
	public static final String NEW_CONN_W_FILE_MENU_ITEM = "NEW_CONN_W_FILE_MENU_ITEM";
	public static final String DISCONN_DB_FILE_MENU_ITEM = "DISCONN_DB_FILE_MENU_ITEM";
	public static final String DISCONN_ALL_DB_FILE_MENU_ITEM = "DISCONN_ALL_DB_FILE_MENU_ITEM";
	public static final String EXIT_FILE_MENU_ITEM = "EXIT_FILE_MENU_ITEM";
	//public static final String _FILE_MENU_ITEM = "";
	public static final String NEW_QUERY_TAB_FILE_MENU_ITEM = "NEW_QUERY_TAB_FILE_MENU_ITEM";
	public static final String OPEN_QUERY_FILE_MENU_ITEM = "OPEN_QUERY_FILE_MENU_ITEM";
	public static final String OPEN_RESS_MENU_ITEM = "OPEN_RESS_MENU_ITEM";
	public static final String SAVE_QUERY_FILE_MENU_ITEM = "SAVE_QUERY_FILE_MENU_ITEM";
	public static final String SAVE_AS_QUERY_FILE_MENU_ITEM = "SAVE_AS_QUERY_FILE_MENU_ITEM";
	public static final String NEW_QUERY_BUILDER_FILE_MENU_ITEM = "NEW_QUERY_BUILDER_FILE_MENU_ITEM";
	public static final String NEW_SCHEMA_DESIGNER_FILE_MENU_ITEM = "NEW_SCHEMA_DESIGNER_FILE_MENU_ITEM";
	public static final String OPEN_RECENT_SQL_FILE_MENU_ITEM = "OPEN_RECENT_SQL_FILE_MENU_ITEM";
	public static final String OPEN_RECENT_SCHEMA_FILE_MENU_ITEM = "OPEN_RECENT_SCHEMA_FILE_MENU_ITEM";
	
// Edit menu Items
	public static final String _EDIT_MENU_ITEM = "";
// View menu Items
	public static final String SHOW_TOOLBAR_VIEW_MENU_ITEM = "SHOW_TOOLBAR_VIEW_MENU_ITEM";
	public static final String SHOW_STATBAR_VIEW_MENU_ITEM = "SHOW_STATBAR_VIEW_MENU_ITEM";
	public static final String SYS_LnF_VIEW_MENU_ITEM = "SYS_LnF_VIEW_MENU_ITEM";
	public static final String METAL_LnF_VIEW_MENU_ITEM = "METAL_LnF_VIEW_MENU_ITEM";
	public static final String OFFICE_2003_VIEW_MENU_ITEM = "OFFICE_2003_VIEW_MENU_ITEM";
	public static final String FOREST_VIEW_MENU_ITEM = "FOREST_VIEW_MENU_ITEM";
	/*public static final String _VIEW_MENU_ITEM = "";
	public static final String _VIEW_MENU_ITEM = "";
	public static final String _VIEW_MENU_ITEM = "";
	public static final String _VIEW_MENU_ITEM = "";
	public static final String _VIEW_MENU_ITEM = "";*/
// Windows menu items
	public static final String CASCADE_WINDOWS_MENU_ITEM = "CASCADE_WINDOWS_MENU_ITEM";
	public static final String TILE_WINDOWS_MENU_ITEM = "TILE_WINDOWS_MENU_ITEM";
// Help menu items
	public static final String _HELP_MENU_ITEM = "";
	
	
	
	private Map<String, JMenu> menuMap = new HashMap<String, JMenu>();
	private Map<String, JMenuItem> menuItemMap = new HashMap<String, JMenuItem>();
	private ButtonGroup lnfButtonGroup = new ButtonGroup();
	
	private JFrame parentFrame;
	
	public MenuBarItems(JFrame parent) {
		parentFrame = parent;
		createAllMenus();
	}
	
	private void createAllMenus() {
		addMenu(FILE_MENU_NAME, FILE_MENU_TEXT);
		addMenu(EDIT_MENU_NAME, EDIT_MENU_TEXT);
		addMenu(VIEW_MENU_NAME, VIEW_MENU_TEXT);
		addMenu(FAVORITES_MENU_NAME, FAVORITES_MENU_TEXT);
		addMenu(DB_MENU_NAME, DB_MENU_TEXT);
		addMenu(TABLE_MENU_NAME, TABLE_MENU_TEXT);
		addMenu(OBJECTS_MENU_NAME, OBJECTS_MENU_TEXT);
		addMenu(TOOLS_MENU_NAME, TOOLS_MENU_TEXT);
		addMenu(POWER_TOOLS_MENU_NAME, POWER_TOOLS_MENU_TEXT);
		addMenu(WINDOW_MENU_NAME, WINDOW_MENU_TEXT);
		addMenu(HELP_MENU_NAME, HELP_MENU_TEXT);
		
		createFileMenu();
		createEditMenu();
		createViewMenu();
		createWindowsMenu();
		createHelpMenu();
	}
	
	private void createHelpMenu() {
		// TODO Auto-generated method stub
		
	}

	private void createWindowsMenu() {
		addMenuItem(WINDOW_MENU_NAME, CASCADE_WINDOWS_MENU_ITEM, "Cascade Windows", 
				CASCADE_ACT_CMD, "application_cascade.png");
		addMenuItem(WINDOW_MENU_NAME, TILE_WINDOWS_MENU_ITEM, "Tile Windows", 
				TILES_2003_ACT_CMD, "application_view_tile.png");
	}

	private void createViewMenu() {
		getMenu(VIEW_MENU_NAME).add(
				createCheckBoxMenuItem(SHOW_TOOLBAR_VIEW_MENU_ITEM,
						"Show Toolbar", SHOW_TOOLBAR_ACT_CMD, null, true));
		getMenu(VIEW_MENU_NAME).add(
				createCheckBoxMenuItem(SHOW_STATBAR_VIEW_MENU_ITEM,
						"Show Statusbar", SHOW_STATBAR_ACT_CMD, null, true));
		getMenu(VIEW_MENU_NAME).addSeparator();
		JMenu lNfMenu = new JMenu("Look 'n' Feel");
		JRadioButtonMenuItem sysLnfItel = createRadioButtonMenuItem(SYS_LnF_VIEW_MENU_ITEM,
						"System", SYS_LnF_ACT_CMD, null);
		sysLnfItel.setSelected(true);
		JRadioButtonMenuItem metalLnfItel = createRadioButtonMenuItem(METAL_LnF_VIEW_MENU_ITEM,
				"Metal", METAL_ACT_CMD, null);
		JRadioButtonMenuItem forestLnfItel = createRadioButtonMenuItem(FOREST_VIEW_MENU_ITEM,
				"Forest", FOREST_ACT_CMD, null);
		JRadioButtonMenuItem office2003LnfItel = createRadioButtonMenuItem(OFFICE_2003_VIEW_MENU_ITEM,
				"Office 2003", OFFICE_2003_ACT_CMD, null);
		JRadioButtonMenuItem LnfItel = createRadioButtonMenuItem(SYS_LnF_VIEW_MENU_ITEM,
				"System", SYS_LnF_VIEW_MENU_ITEM, null);
		lnfButtonGroup.add(sysLnfItel);
		lnfButtonGroup.add(metalLnfItel);
		lnfButtonGroup.add(forestLnfItel);
		lnfButtonGroup.add(office2003LnfItel);
		
		lNfMenu.add(sysLnfItel);
		lNfMenu.addSeparator();
		lNfMenu.add(metalLnfItel);
		lNfMenu.add(forestLnfItel);
		lNfMenu.add(office2003LnfItel);
		
		getMenu(VIEW_MENU_NAME).add(lNfMenu);
	}

	private void createEditMenu() {
		// TODO Auto-generated method stub
		
	}

	private void createFileMenu(){
		addMenuItem(FILE_MENU_NAME, NEW_CONN_FILE_MENU_ITEM, "New Connection", 
				NEW_CONNECTION_ACT_CMD, "new_connection.gif", KeyEvent.VK_N, InputEvent.CTRL_MASK);
		addMenuItem(FILE_MENU_NAME, NEW_CONN_W_FILE_MENU_ITEM, "Connect using current setup", 
				NEW_CONN_W_CURR_ACT_CMD, "New database.png", KeyEvent.VK_N, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK);
		getMenu(FILE_MENU_NAME).addSeparator();
		addMenuItem(FILE_MENU_NAME, DISCONN_DB_FILE_MENU_ITEM, "Disconnect", 
				DISCONN_DB__ACT_CMD, "disconnect-16x16.png", KeyEvent.VK_F4, InputEvent.CTRL_MASK);
		addMenuItem(FILE_MENU_NAME, DISCONN_ALL_DB_FILE_MENU_ITEM, "Disconnect All", 
				NEW_CONN_W_CURR_ACT_CMD, "disconnect-16x16.png");
		getMenu(FILE_MENU_NAME).addSeparator();
		addMenuItem(FILE_MENU_NAME, NEW_QUERY_TAB_FILE_MENU_ITEM, "New Query Tab", 
				NEW_QUERY_TAB_ACT_CMD, "new_untitled_text_file.gif", KeyEvent.VK_T, InputEvent.CTRL_MASK);
		addMenuItem(FILE_MENU_NAME, NEW_QUERY_BUILDER_FILE_MENU_ITEM, "New Query Builder", 
				NEW_QUERY_BUILDER_ACT_CMD, "query-builder.png", KeyEvent.VK_K, InputEvent.CTRL_MASK);
		addMenuItem(FILE_MENU_NAME, NEW_SCHEMA_DESIGNER_FILE_MENU_ITEM, "New Schema Designer", 
				NEW_SCHEMA_DESIGNER_ACT_CMD, "schema_designer.gif", KeyEvent.VK_T, InputEvent.CTRL_MASK);
		getMenu(FILE_MENU_NAME).addSeparator();
		addMenuItem(FILE_MENU_NAME, OPEN_QUERY_FILE_MENU_ITEM, "Open", 
				OPEN_QUERY_FILE_ACT_CMD, "open.gif", KeyEvent.VK_O, InputEvent.CTRL_MASK);
		addMenuItem(FILE_MENU_NAME, OPEN_RESS_MENU_ITEM, "Open Resource", 
				OPEN_RESS_ACT_CMD, "openResource.gif", KeyEvent.VK_R, InputEvent.CTRL_MASK+InputEvent.SHIFT_MASK);
		addMenuItem(FILE_MENU_NAME, OPEN_RECENT_SQL_FILE_MENU_ITEM, "Open Recent SQL Files", 
				OPEN_RECENT_SQL_FILE_ACT_CMD);
		addMenuItem(FILE_MENU_NAME, OPEN_RECENT_SCHEMA_FILE_MENU_ITEM, "Open Recent Schema Designs", 
				OPEN_RECENT_SCHEMA_D_ACT_CMD);
		getMenu(FILE_MENU_NAME).addSeparator();
		addMenuItem(FILE_MENU_NAME, SAVE_QUERY_FILE_MENU_ITEM, "Save", 
				SAVE_QUERY_FILE_ACT_CMD, "save_edit.gif", KeyEvent.VK_S, InputEvent.CTRL_MASK);
		addMenuItem(FILE_MENU_NAME, SAVE_AS_QUERY_FILE_MENU_ITEM, "Save As...", 
				SAVE_AS_QUERY_FILE_ACT_CMD, "saveas_edit.gif");
		addMenuItem(FILE_MENU_NAME, SAVE_AS_QUERY_FILE_MENU_ITEM, "Save All...", 
				SAVE_AS_QUERY_FILE_ACT_CMD, "saveall_edit.gif", KeyEvent.VK_S, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK);
		getMenu(FILE_MENU_NAME).addSeparator();
		addMenuItem(FILE_MENU_NAME, EXIT_FILE_MENU_ITEM, "Exit", 
				EXIT_APP_ACT_CMD, "exit.gif", KeyEvent.VK_F4, InputEvent.ALT_MASK);
	}
	
	public void addMenusToMenuBar(JMenuBar menuBar){
		menuBar.add(menuMap.get(FILE_MENU_NAME));
		menuBar.add(menuMap.get(EDIT_MENU_NAME));
		menuBar.add(menuMap.get(VIEW_MENU_NAME));
		//menuBar.add(menuMap.get(FAVORITES_MENU_NAME));
		//menuBar.add(menuMap.get(DB_MENU_NAME));
		//menuBar.add(menuMap.get(TABLE_MENU_NAME));
		//menuBar.add(menuMap.get(OBJECTS_MENU_NAME));
		menuBar.add(menuMap.get(TOOLS_MENU_NAME));
		//menuBar.add(menuMap.get(POWER_TOOLS_MENU_NAME));
		menuBar.add(menuMap.get(WINDOW_MENU_NAME));
		menuBar.add(menuMap.get(HELP_MENU_NAME));
	}
	
	public void addSeparator(JMenu menu){
		menu.addSeparator();
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
		if(keyCode != -1){
			KeyStroke stroke = KeyStroke.getKeyStroke(keyCode, modifiers);
			if(stroke != null)
				item.setAccelerator(stroke);
		}
		return item;
	}
	
	private JCheckBoxMenuItem createCheckBoxMenuItem(String name, String txt, String actCmd, String imageName, boolean selected){
		return createCheckBoxMenuItem(name, txt, actCmd, imageName, -1, -1, selected);
	}
	
	private JCheckBoxMenuItem createCheckBoxMenuItem(String name, String txt, String actCmd, String imageName, int keyCode, int modifiers, boolean selected){
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(txt);
		item.setSelected(selected);
		item.setName(name);
		item.setActionCommand(actCmd);
		item.addActionListener(this);
		if (null != imageName) {
			Icon image = new ImageIcon(MenuBarUtil.class
					.getResource(OracleGuiConstants.IMAGE_PATH + imageName));
			item.setIcon(image);
		}
		if(keyCode != -1){
			KeyStroke stroke = KeyStroke.getKeyStroke(keyCode, modifiers);
			if(stroke != null)
				item.setAccelerator(stroke);
		}
		return item;
	}
	
	private JRadioButtonMenuItem createRadioButtonMenuItem(String name, String txt, String actCmd, String imageName){
		return createRadioButtonMenuItem(name, txt, actCmd, imageName, -1, -1);
	}
	
	private JRadioButtonMenuItem createRadioButtonMenuItem(String name, String txt, String actCmd, String imageName, int keyCode, int modifiers){
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(txt);
		item.setName(name);
		item.setActionCommand(actCmd);
		item.addActionListener(this);
		if (null != imageName) {
			Icon image = new ImageIcon(MenuBarUtil.class
					.getResource(OracleGuiConstants.IMAGE_PATH + imageName));
			item.setIcon(image);
		}
		if(keyCode != -1){
			KeyStroke stroke = KeyStroke.getKeyStroke(keyCode, modifiers);
			if(stroke != null)
				item.setAccelerator(stroke);
		}
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
