/**
 * 
 */
package com.gs.oracle.iframe;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.comps.DatabaseDirectoryPanel;
import com.gs.oracle.comps.DatabaseDirectoryTree;
import com.gs.oracle.comps.ResultSetTableModelFactory;
import com.gs.oracle.comps.SqlQueryPanel;
import com.gs.oracle.comps.TableDetailsPanel;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.model.Database;
import com.gs.oracle.service.OracleDatabaseService;
import com.gs.oracle.service.impl.OracleDatabaseServiceImpl;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class DatabaseViewerInternalFrame extends JInternalFrame implements WindowListener{
	
	private ConnectionProperties connectionProperties;
	private Connection connection;

	private JSplitPane outterSplitPane, innerSplitPane;
	private JPanel mainPanel;
	private JTabbedPane dbDetailsTabbedPane;
	
	private OracleDatabaseService service;
	private JTabbedPane dbViewerTabbedPane;
	
	public DatabaseViewerInternalFrame() {
		service = new OracleDatabaseServiceImpl();
		Database database = getDataBaseInformation();
		initComponents(database);
	}
	
	
	
	public DatabaseViewerInternalFrame(
			ConnectionProperties connectionProperties) {
		service = new OracleDatabaseServiceImpl();
		this.connectionProperties = connectionProperties;
		this.connection = this.connectionProperties.getConnection();
		Database database = getDataBaseInformation();
		initComponents(database);
	}



	private Database getDataBaseInformation() {
		Database db = null;
		if(connection != null && connectionProperties != null){
			try {
				connectionProperties.setConnection(connection);
				db = service.getDatabase(connectionProperties);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		return db;
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
		
		DatabaseDirectoryPanel directoryPanel = new DatabaseDirectoryPanel(new DatabaseDirectoryTree(database));
		outterSplitPane.setLeftComponent(directoryPanel);
		mainPanel.add(outterSplitPane, BorderLayout.CENTER);
		
		
		dbDetailsTabbedPane = new JTabbedPane();
		SqlQueryPanel panel = new SqlQueryPanel();
		panel.setConnectionProperties(getConnectionProperties());
		ResultSetTableModelFactory factory = new ResultSetTableModelFactory(getConnection());
		panel.setFactory(factory);
		dbDetailsTabbedPane.addTab("SQL", new ImageIcon(DatabaseViewerInternalFrame.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "executesql.gif")), panel);
		
		dbDetailsTabbedPane.addTab("TABLE_NAME", new ImageIcon(DatabaseViewerInternalFrame.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "table.gif")),
				new TableDetailsPanel());
		
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

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
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
		if(connection != null){
			try {
				connection.close();
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
}
