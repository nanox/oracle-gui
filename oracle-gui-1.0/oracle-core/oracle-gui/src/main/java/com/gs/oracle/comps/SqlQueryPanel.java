
/*
 * SqlQueryPanel.java
 *
 * Created on Aug 11, 2009, 3:37:45 PM
 */

package com.gs.oracle.comps;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.command.GuiEventHandler;
import com.gs.oracle.common.StringUtil;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.io.IOUtils;
import com.gs.oracle.util.MenuBarUtil;

/**
 * 
 * @author Green Moon
 */
public class SqlQueryPanel extends javax.swing.JPanel implements ActionListener {

	/** Creates new form SqlQueryPanel */
	public SqlQueryPanel() {
		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		queryPopupMenu = new javax.swing.JPopupMenu();
		runQueryMenuItem = new javax.swing.JMenuItem();
		runAllMenuItem = new javax.swing.JMenuItem();
		jSeparator4 = new javax.swing.JSeparator();
		commitMenuItem = new javax.swing.JMenuItem();
		rollbackMenuItem = new javax.swing.JMenuItem();
		jSeparator5 = new javax.swing.JSeparator();
		openMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();
		clearMenuItem = new javax.swing.JMenuItem();
		jSeparator6 = new javax.swing.JSeparator();
		wrapCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
		sqlQuerySplitPane = new javax.swing.JSplitPane();
		queryPanel = new javax.swing.JPanel();
		queryToolBar = new javax.swing.JToolBar();
		runQueryButton = new javax.swing.JButton();
		runAllButton = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JToolBar.Separator();
		commitButton = new javax.swing.JButton();
		rollbackButton = new javax.swing.JButton();
		jSeparator2 = new javax.swing.JToolBar.Separator();
		openButton = new javax.swing.JButton();
		saveButton = new javax.swing.JButton();
		saveAsButton = new javax.swing.JButton();
		clearButton = new javax.swing.JButton();
		jSeparator3 = new javax.swing.JToolBar.Separator();
		wrapToggleButton = new javax.swing.JToggleButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		queryTextArea = new javax.swing.JTextArea();
		queryResultPanel = new javax.swing.JPanel();
		queryResultTabbedPane = new javax.swing.JTabbedPane();
		queryResultTabPanel = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		queryLogToolBar = new javax.swing.JToolBar();
		jButton1 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		queryLogTextArea = new javax.swing.JTextArea();

		Icon image = null;

		runQueryMenuItem.setText("Run");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		runQueryMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,
				InputEvent.CTRL_MASK));
		runQueryMenuItem.setIcon(image);
		runQueryMenuItem.addActionListener(this);
		queryPopupMenu.add(runQueryMenuItem);

		runAllMenuItem.setText("Run All");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		runAllMenuItem.setIcon(image);
		runAllMenuItem.addActionListener(this);
		queryPopupMenu.add(runAllMenuItem);
		queryPopupMenu.add(jSeparator4);

		commitMenuItem.setText("Commit");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		commitMenuItem.setIcon(image);
		commitMenuItem.addActionListener(this);
		queryPopupMenu.add(commitMenuItem);

		rollbackMenuItem.setText("Rollback Last");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		rollbackMenuItem.setIcon(image);
		rollbackMenuItem.addActionListener(this);
		queryPopupMenu.add(rollbackMenuItem);
		queryPopupMenu.add(jSeparator5);

		openMenuItem.setText("Open");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		openMenuItem.setIcon(image);
		openMenuItem.addActionListener(this);
		queryPopupMenu.add(openMenuItem);

		saveMenuItem.setText("Save");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		saveMenuItem.setIcon(image);
		saveMenuItem.addActionListener(this);
		queryPopupMenu.add(saveMenuItem);

		saveAsMenuItem.setText("Save As...");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		saveAsMenuItem.setIcon(image);
		saveAsMenuItem.addActionListener(this);
		queryPopupMenu.add(saveAsMenuItem);

		clearMenuItem.setText("Clear");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		clearMenuItem.setIcon(image);
		clearMenuItem.addActionListener(this);
		queryPopupMenu.add(clearMenuItem);
		queryPopupMenu.add(jSeparator6);

		wrapCheckBoxMenuItem.setSelected(true);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		wrapCheckBoxMenuItem.setIcon(image);
		wrapCheckBoxMenuItem.addActionListener(this);
		wrapCheckBoxMenuItem.setText("Wrap");
		queryPopupMenu.add(wrapCheckBoxMenuItem);

		setLayout(new java.awt.BorderLayout());

		sqlQuerySplitPane.setDividerLocation(250);
		sqlQuerySplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

		queryPanel.setLayout(new java.awt.GridBagLayout());

		queryToolBar.setFloatable(false);
		queryToolBar.setRollover(true);

		runQueryButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		runQueryButton.setIcon(image);
		runQueryButton.addActionListener(this);
		runQueryButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		runQueryButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(runQueryButton);

		runAllButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		runAllButton.setIcon(image);
		runAllButton.addActionListener(this);
		runAllButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		runAllButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(runAllButton);
		queryToolBar.add(jSeparator1);

		commitButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		commitButton.setIcon(image);
		commitButton.addActionListener(this);
		commitButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		commitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(commitButton);

		rollbackButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		rollbackButton.setIcon(image);
		rollbackButton.addActionListener(this);
		rollbackButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		rollbackButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(rollbackButton);
		queryToolBar.add(jSeparator2);

		openButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		openButton.setIcon(image);
		openButton.addActionListener(this);
		openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(openButton);

		saveButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		saveButton.setIcon(image);
		saveButton.addActionListener(this);
		saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(saveButton);

		saveAsButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		saveAsButton.setIcon(image);
		saveAsButton.addActionListener(this);
		saveAsButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		saveAsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(saveAsButton);

		clearButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		clearButton.setIcon(image);
		clearButton.addActionListener(this);
		clearButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		clearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(clearButton);
		queryToolBar.add(jSeparator3);

		wrapToggleButton.setSelected(true);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + ""));
		wrapToggleButton.setIcon(image);
		wrapToggleButton.addActionListener(this);
		wrapToggleButton.setFocusable(false);
		wrapToggleButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		wrapToggleButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(wrapToggleButton);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		queryPanel.add(queryToolBar, gridBagConstraints);

		queryTextArea.setColumns(20);
		queryTextArea.setLineWrap(true);
		queryTextArea.setRows(5);
		queryTextArea.setTabSize(4);
		queryTextArea.setComponentPopupMenu(queryPopupMenu);
		queryTextArea.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_F9 == e.getKeyCode()) {
					String query = getLastQuery();
					displayQueryResults(query);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

		});
		jScrollPane1.setViewportView(queryTextArea);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		queryPanel.add(jScrollPane1, gridBagConstraints);

		sqlQuerySplitPane.setTopComponent(queryPanel);

		queryResultPanel.setLayout(new java.awt.GridBagLayout());

		queryResultTabPanel.setLayout(new java.awt.BorderLayout());
		queryResultTable = new JTable();
		queryResultTable.setAutoCreateRowSorter(true);
		queryResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		queryResultTable.setAutoscrolls(true);
		queryResultTabPanel.add(new JScrollPane(queryResultTable), BorderLayout.CENTER);
		queryResultTabbedPane.addTab("Result", queryResultTabPanel);

		jPanel2.setLayout(new java.awt.GridBagLayout());

		queryLogToolBar.setRollover(true);

		jButton1.setText("jButton1");
		jButton1.setFocusable(false);
		jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryLogToolBar.add(jButton1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jPanel2.add(queryLogToolBar, gridBagConstraints);

		queryLogTextArea.setColumns(20);
		queryLogTextArea.setRows(5);
		jScrollPane2.setViewportView(queryLogTextArea);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel2.add(jScrollPane2, gridBagConstraints);

		queryResultTabbedPane.addTab("Log", jPanel2);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		queryResultPanel.add(queryResultTabbedPane, gridBagConstraints);

		sqlQuerySplitPane.setRightComponent(queryResultPanel);

		add(sqlQuerySplitPane, java.awt.BorderLayout.CENTER);
	}

	private javax.swing.JButton clearButton;
	private javax.swing.JButton commitButton;
	private javax.swing.JButton jButton1;
	private javax.swing.JCheckBoxMenuItem wrapCheckBoxMenuItem;
	private javax.swing.JMenuItem runQueryMenuItem;
	private javax.swing.JMenuItem runAllMenuItem;
	private javax.swing.JMenuItem commitMenuItem;
	private javax.swing.JMenuItem rollbackMenuItem;
	private javax.swing.JMenuItem openMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem clearMenuItem;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JToolBar.Separator jSeparator1;
	private javax.swing.JToolBar.Separator jSeparator2;
	private javax.swing.JToolBar.Separator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JSeparator jSeparator5;
	private javax.swing.JSeparator jSeparator6;
	private javax.swing.JButton openButton;
	private javax.swing.JTextArea queryLogTextArea;
	private javax.swing.JToolBar queryLogToolBar;
	private javax.swing.JPanel queryPanel;
	private javax.swing.JPopupMenu queryPopupMenu;
	private javax.swing.JPanel queryResultPanel;
	private javax.swing.JPanel queryResultTabPanel;
	private javax.swing.JTabbedPane queryResultTabbedPane;
	private javax.swing.JTextArea queryTextArea;
	private javax.swing.JToolBar queryToolBar;
	private javax.swing.JButton rollbackButton;
	private javax.swing.JButton runAllButton;
	private javax.swing.JButton runQueryButton;
	private javax.swing.JButton saveAsButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JSplitPane sqlQuerySplitPane;
	private javax.swing.JToggleButton wrapToggleButton;
	private JTable queryResultTable;
	private ResultSetTableModelFactory factory;

	private GuiEventHandler guiEventHandler = new GuiEventHandler();
	private ConnectionProperties connectionProperties;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(clearButton)
				|| e.getSource().equals(clearMenuItem)) {
			queryTextArea.setText("");
		}
		if (e.getSource().equals(runQueryButton)) {
			String query = getLastQuery();
			displayQueryResults(query);
		}
	}

	public void displayQueryResults(final String q) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					queryResultTable
							.setModel(factory.getResultSetTableModel(q));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, new String[] { // Display
																		// a
																		// 2-line
																		// message
							ex.getClass().getName() + ": ", ex.getMessage() });
				}
			}
		});
	}

	public javax.swing.JTextArea getQueryTextArea() {
		return queryTextArea;
	}

	public void setQueryResultTable(JTable queryResultTable) {
		this.queryResultTable = queryResultTable;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(
			ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public ResultSetTableModelFactory getFactory() {
		return factory;
	}

	public void setFactory(ResultSetTableModelFactory factory) {
		this.factory = factory;
	}
	
	public List<String> getQueryList(){
		if(!StringUtil.hasValidContent(queryTextArea.getText()))
			return new ArrayList<String>();
		List<String> queryList = new ArrayList<String>();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new StringReader(queryTextArea.getText()));
			char[] cBuff = new char[1];
			int count = 0;
			StringBuffer qBuff = new StringBuffer();
			while((count = br.read(cBuff, 0, cBuff.length)) >= 0){
				char ch = cBuff[0];
				if('\r' == ch || '\n' == ch)
					continue;
				if(';' != ch){
					qBuff.append(ch);
				}else{
					queryList.add(qBuff.toString());
					qBuff = new StringBuffer();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			IOUtils.closeReader(br);
		}
		
		return queryList;
	}
	
	public String getLastQuery(){
		if(!"".equals(getSelectedQuery())){
			return getSelectedQuery();
		}
		List<String> queryList = getQueryList();
		if(!queryList.isEmpty()){
			return queryList.get(queryList.size() - 1);
		}
		return "";
	}

	public String getSelectedQuery(){
		String full = queryTextArea.getText();
		if(!StringUtil.hasValidContent(full)){
			return "";
		}
		return queryTextArea.getSelectedText();
	}
}
