/**
 * 
 */
package com.gs.dbex.application.frame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import com.gs.dbex.application.constants.ImageConstants;
import com.gs.dbex.application.menu.MenuBarItems;
import com.gs.dbex.application.panels.StatusBar;
import com.gs.dbex.application.toolbar.ToolbarButtons;
import com.gs.dbex.service.DatabaseConnectionService;
import com.gs.utils.swing.window.WindowUtil;

/**
 * @author sabuj.das
 *
 */
public class DatabaseExplorerFrame extends JFrame implements ActionListener {

	private DatabaseConnectionService databaseConnectionService;
	
	private JDesktopPane dbexDesktopPane;
    private JMenuBar dbexMenuBar;
    private JPanel mainPanel;
    private JToolBar mainToolBar;
    private ToolbarButtons toolbarButtons;
    private MenuBarItems menuBarItems;
    
    private JComboBox dbNamesComboBox;
    
    private StatusBar statusBar;
    
    
	public DatabaseExplorerFrame() {
		initComponents();
		setInitialProperties();
	}
	
	private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new JPanel();
        mainToolBar = new JToolBar();
        dbexDesktopPane = new JDesktopPane();
        dbexMenuBar = new JMenuBar();
        dbNamesComboBox = new JComboBox();
        
        statusBar = new StatusBar();
        toolbarButtons = new ToolbarButtons(this);
        menuBarItems = new MenuBarItems(this);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setName("OracleGuiMainFrame"); 

        mainPanel.setName("mainPanel"); 
        mainPanel.setLayout(new GridBagLayout());

        dbNamesComboBox.setMaximumSize(dbNamesComboBox.getPreferredSize());
        mainToolBar.setRollover(true);
        mainToolBar.setName("mainToolBar"); 
        mainToolBar.setFloatable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        //addToolbarComponents();
        
        mainPanel.add(mainToolBar, gridBagConstraints);

        dbexDesktopPane.setName("jDesktopPane1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(dbexDesktopPane, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        mainPanel.add(statusBar, gridBagConstraints);
        
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        dbexMenuBar.setName("mainMenuBar"); 
        addMenubarComponents();
        
        setJMenuBar(dbexMenuBar);

        pack();
    }
	
	private void addMenubarComponents() {
		//mainMenuBar.add(menuBarItems.getMenu(MenuBarItems.FILE_MENU_NAME));
		menuBarItems.addMenusToMenuBar(dbexMenuBar);
	}

	private void addToolbarComponents() {
		mainToolBar.add(toolbarButtons.getButton(ToolbarButtons.NEW_CONNECTION_TOOLBAR_BUTTON));
		mainToolBar.add(toolbarButtons.getButton(ToolbarButtons.REFRESH_DATABASE_TOOLBAR_BUTTON));
		mainToolBar.add(dbNamesComboBox);
		mainToolBar.addSeparator();
		mainToolBar.add(toolbarButtons.getButton(ToolbarButtons.BACKUP_DATABSE_TOOLBAR_BUTTON));
		mainToolBar.add(toolbarButtons.getButton(ToolbarButtons.EXECUITE_CURRENT_SQL_TOOLBAR_BUTTON));
		mainToolBar.add(toolbarButtons.getButton(ToolbarButtons.DB_SYNC_TOOLBAR_BUTTON));
	}

	
	private void setInitialProperties(){
		setSize(880, 600);
		setTitle("Database Explorer");
		setIconImage((new ImageIcon(DatabaseExplorerFrame.class
				.getResource(ImageConstants.APPLICATION_IMG_LOC + ImageConstants.MAIN_FRAME_ICON_IMG_NAME )))
				.getImage());
		WindowUtil.bringToCenter(this);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the databaseConnectionService
	 */
	public DatabaseConnectionService getDatabaseConnectionService() {
		return databaseConnectionService;
	}

	/**
	 * @param databaseConnectionService the databaseConnectionService to set
	 */
	public void setDatabaseConnectionService(
			DatabaseConnectionService databaseConnectionService) {
		this.databaseConnectionService = databaseConnectionService;
	}

	
	
}
