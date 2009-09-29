/**
 * 
 */
package com.gs.oracle.iframe;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import oracle.jdbc.pool.OracleDataSource;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.comps.ButtonTabComponent;
import com.gs.oracle.comps.DatabaseDirectoryPanel;
import com.gs.oracle.comps.DatabaseDirectoryTree;
import com.gs.oracle.comps.ResultSetTableModelFactory;
import com.gs.oracle.comps.SqlQueryPanel;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.model.Database;
import com.gs.oracle.model.Schema;
import com.gs.oracle.model.Table;
import com.gs.oracle.service.OracleDatabaseService;
import com.gs.oracle.service.impl.OracleDatabaseServiceImpl;

/**
 * @author sabuj.das
 *
 */
public class DatabaseViewerInternalFrame extends JInternalFrame implements WindowListener{
	
	private JFrame parentFrame;
	
	private ConnectionProperties connectionProperties;

	private JSplitPane outterSplitPane, innerSplitPane;
	private JPanel mainPanel;
	private JTabbedPane dbDetailsTabbedPane;
	
	private OracleDatabaseService service;
	private JTabbedPane dbViewerTabbedPane;
	
	private List<String> schemaNameList = new ArrayList<String>();
	private List<String> tableNameList = new ArrayList<String>();
	
	public DatabaseViewerInternalFrame() {
		service = new OracleDatabaseServiceImpl();
		Database database = getDataBaseInformation();
		initComponents(database);
	}
	
	
	
	public DatabaseViewerInternalFrame(
			ConnectionProperties connectionProperties) {
		service = new OracleDatabaseServiceImpl();
		this.connectionProperties = connectionProperties;
		Database database = getDataBaseInformation();
		initComponents(database);
	}



	private Database getDataBaseInformation() {
		Database db = null;
		if(connectionProperties != null){
			try {
				db = service.getDatabase(connectionProperties);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		if(db != null){
			List<Schema> schemaList = db.getSchemaList();
			for (Schema schema : schemaList) {
				schemaNameList.add(schema.getModelName());
				OracleGuiConstants.SQL_KEYWORD_LIST.add(schema.getModelName().toLowerCase());
				List<Table> tablelList = schema.getTableList();
				for (Table table : tablelList) {
					tableNameList.add(table.getModelName());
					OracleGuiConstants.SQL_KEYWORD_LIST.add(table.getModelName().toLowerCase());
				}
			}
 		}
		return db;
	}

	/**
	 * @return the schemaNameList
	 */
	public List<String> getSchemaNameList() {
		return schemaNameList;
	}



	/**
	 * @return the tableNameList
	 */
	public List<String> getTableNameList() {
		return tableNameList;
	}



	public void refreshConnectionScheduler(ConnectionProperties connectionProperties){
		
	}
	
	public void refreshConnection(ConnectionProperties connectionProperties){
		
	}
	
	private void initComponents(Database database){
		if(connectionProperties != null)
			setTitle(connectionProperties.getUserName() + " @ " + connectionProperties.getHostName());
		
		setLocation(0, 0);
		setSize(600, 450);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		outterSplitPane = new JSplitPane();
		outterSplitPane.setDividerLocation(150);
		outterSplitPane.setContinuousLayout(true);
		outterSplitPane.setOneTouchExpandable(true);
		outterSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		DatabaseDirectoryPanel directoryPanel = new DatabaseDirectoryPanel(
				new DatabaseDirectoryTree(database), getConnectionProperties());
		directoryPanel.setParentComponent(this);
		outterSplitPane.setLeftComponent(directoryPanel);
		mainPanel.add(outterSplitPane, BorderLayout.CENTER);
		
		
		dbDetailsTabbedPane = new JTabbedPane();
		dbDetailsTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		SqlQueryPanel panel = new SqlQueryPanel();
		panel.setParentFrame(parentFrame);
		panel.setConnectionProperties(getConnectionProperties());
		ResultSetTableModelFactory factory = null;
		try {
			factory = new ResultSetTableModelFactory(
					getConnectionProperties().getDataSource().getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.setFactory(factory);
		dbDetailsTabbedPane.addTab("SQL", panel);
		int n = dbDetailsTabbedPane.getTabCount();
		dbDetailsTabbedPane.setTabComponentAt(n - 1,
                new ButtonTabComponent(dbDetailsTabbedPane, new ImageIcon(DatabaseViewerInternalFrame.class
        				.getResource(OracleGuiConstants.IMAGE_PATH + "executesql.gif"))));
		dbDetailsTabbedPane.setSelectedIndex(n - 1);
		outterSplitPane.setRightComponent(dbDetailsTabbedPane);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		//pack();
	}


	public JTabbedPane getDbDetailsTabbedPane() {
		return dbDetailsTabbedPane;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}


	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JSplitPane getOutterSplitPane() {
		return outterSplitPane;
	}

	public void setOutterSplitPane(JSplitPane outterSplitPane) {
		this.outterSplitPane = outterSplitPane;
	}

	public JSplitPane getInnerSplitPane() {
		return innerSplitPane;
	}

	public void setInnerSplitPane(JSplitPane innerSplitPane) {
		this.innerSplitPane = innerSplitPane;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		closeWindow();
	}
	
	public void closeWindow(){
		if(connectionProperties.getDataSource() != null){
			try {
				if(connectionProperties.getDataSource() instanceof OracleDataSource){
					((OracleDataSource)connectionProperties.getDataSource()).close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	public JFrame getParentFrame() {
		return parentFrame;
	}



	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}
}
