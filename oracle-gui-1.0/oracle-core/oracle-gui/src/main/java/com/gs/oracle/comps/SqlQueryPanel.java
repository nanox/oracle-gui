/*
 * SqlQueryPanel.java
 *
 * Created on Aug 11, 2009, 3:37:45 PM
 */

package com.gs.oracle.comps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.QueryTypeEnum;
import com.gs.oracle.SqlQuery;
import com.gs.oracle.command.GuiEventHandler;
import com.gs.oracle.common.StringUtil;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.io.IOUtils;
import com.gs.oracle.service.QueryExecutionService;
import com.gs.oracle.service.impl.QueryExecutionServiceImpl;
import com.gs.oracle.sql.SqlDocument;
import com.gs.oracle.util.MenuBarUtil;

/**
 * 
 * @author Green Moon
 */
public class SqlQueryPanel extends javax.swing.JPanel implements ActionListener {

	public static final java.awt.Font DEFAULT_TEXT_FONT =
        new java.awt.Font(java.awt.Font.MONOSPACED,
            java.awt.Font.PLAIN, 12);

	private Font bitstreamFont;
	private JMenuItem runSelectionMenuItem;
	private JButton queryFontButton;
	private QueryExecutionService queryExecutionService;

	/** Creates new form SqlQueryPanel */
	public SqlQueryPanel() {
		try {
			bitstreamFont = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getResourceAsStream("/fonts/VeraMono.ttf"));
			bitstreamFont = new Font(bitstreamFont.getFontName(),
            java.awt.Font.PLAIN, 12);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		queryExecutionService = new QueryExecutionServiceImpl(getConnectionProperties());
		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		queryPopupMenu = new javax.swing.JPopupMenu();
		runQueryMenuItem = new javax.swing.JMenuItem();
		runAllMenuItem = new javax.swing.JMenuItem();
		runSelectionMenuItem = new javax.swing.JMenuItem();
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
		runSelectedQueryButton = new javax.swing.JButton();
		queryFontButton = new  javax.swing.JButton();
		
		Icon image = null;

		runQueryMenuItem.setText("Run Last Query");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		runQueryMenuItem.setIcon(image);
		runQueryMenuItem.addActionListener(this);
		queryPopupMenu.add(runQueryMenuItem);
		
		runSelectionMenuItem.setText("Run Selection");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "sql_execute_selection.gif"));
		runSelectionMenuItem.setIcon(image);
		runSelectionMenuItem.addActionListener(this);
		queryPopupMenu.add(runSelectionMenuItem);
		
		runAllMenuItem.setText("Run All");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "sql_execute.gif"));
		runAllMenuItem.setIcon(image);
		runAllMenuItem.addActionListener(this);
		queryPopupMenu.add(runAllMenuItem);
		queryPopupMenu.add(jSeparator4);

		commitMenuItem.setText("Commit");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "commit.gif"));
		commitMenuItem.setIcon(image);
		commitMenuItem.addActionListener(this);
		queryPopupMenu.add(commitMenuItem);

		rollbackMenuItem.setText("Rollback Last");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "rollback.png"));
		rollbackMenuItem.setIcon(image);
		rollbackMenuItem.addActionListener(this);
		queryPopupMenu.add(rollbackMenuItem);
		queryPopupMenu.add(jSeparator5);

		openMenuItem.setText("Open");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "open.gif"));
		openMenuItem.setIcon(image);
		openMenuItem.addActionListener(this);
		queryPopupMenu.add(openMenuItem);

		saveMenuItem.setText("Save");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "save_edit.gif"));
		saveMenuItem.setIcon(image);
		saveMenuItem.addActionListener(this);
		queryPopupMenu.add(saveMenuItem);

		saveAsMenuItem.setText("Save As...");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "saveas_edit.gif"));
		saveAsMenuItem.setIcon(image);
		saveAsMenuItem.addActionListener(this);
		queryPopupMenu.add(saveAsMenuItem);

		clearMenuItem.setText("Clear");
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "clear_co.gif"));
		clearMenuItem.setIcon(image);
		clearMenuItem.addActionListener(this);
		queryPopupMenu.add(clearMenuItem);
		queryPopupMenu.add(jSeparator6);

		wrapCheckBoxMenuItem.setSelected(true);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "button-word-wrap.gif"));
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
		runQueryButton.setToolTipText("Run the last query. [F9]");
		queryToolBar.add(runQueryButton);

		runSelectedQueryButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "sql_execute_selection.gif"));
		runSelectedQueryButton.setIcon(image);
		runSelectedQueryButton.addActionListener(this);
		runSelectedQueryButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		runSelectedQueryButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		runSelectedQueryButton.setToolTipText("Run selected query. [F10]");
		queryToolBar.add(runSelectedQueryButton);
		runAllButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "sql_execute.gif"));
		runAllButton.setIcon(image);
		runAllButton.addActionListener(this);
		runAllButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		runAllButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		runAllButton.setToolTipText("Run all the queries. [F5]");
		queryToolBar.add(runAllButton);
		queryToolBar.add(jSeparator1);

		commitButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "commit.gif"));
		commitButton.setIcon(image);
		commitButton.addActionListener(this);
		commitButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		commitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(commitButton);

		rollbackButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "rollback.png"));
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
				.getResource(OracleGuiConstants.IMAGE_PATH + "open.gif"));
		openButton.setIcon(image);
		openButton.addActionListener(this);
		openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(openButton);

		saveButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "save_edit.gif"));
		saveButton.setIcon(image);
		saveButton.addActionListener(this);
		saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(saveButton);

		saveAsButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "saveas_edit.gif"));
		saveAsButton.setIcon(image);
		saveAsButton.addActionListener(this);
		saveAsButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		saveAsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(saveAsButton);

		clearButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "clear_co.gif"));
		clearButton.setIcon(image);
		clearButton.addActionListener(this);
		clearButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		clearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		queryToolBar.add(clearButton);
		queryToolBar.add(jSeparator3);

		queryFontButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "font.gif"));
		queryFontButton.setIcon(image);
		queryFontButton.addActionListener(this);
		queryToolBar.add(queryFontButton);
		
		wrapToggleButton.setSelected(true);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "button-word-wrap.gif"));
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

		SqlDocument doc = new SqlDocument();
		//doc.setTextArea(queryTextArea);
		queryTextArea.setColumns(20);
		queryTextArea.setLineWrap(true);
		queryTextArea.setRows(5);
		queryTextArea.setTabSize(4);
		queryTextArea.setMargin(new Insets(2,5,2,5));
		//queryTextArea.setDocument(doc);
		LineNumberedBorder lineNumberedBorder = new LineNumberedBorder(LineNumberedBorder.LEFT_SIDE,
				LineNumberedBorder.RIGHT_JUSTIFY);
		queryTextArea.setFont((null != bitstreamFont) ? bitstreamFont : DEFAULT_TEXT_FONT);
		queryTextArea.setBorder(
            BorderFactory.createCompoundBorder(
              BorderFactory.createEmptyBorder(1, 1, 1, 1),
              BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createCompoundBorder(
                    lineNumberedBorder, BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1),
                        BorderFactory.createEmptyBorder(0, 8, 0, 2)
              ))
              )
              )
              );


		
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
		
		AutoTextComplete atc = new AutoTextComplete(queryTextArea);
		atc.setAlwaysOnTop(true);
		atc.setItems(OracleGuiConstants.SQL_KEYWORD_LIST);
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
		queryResultTabPanel.add(new JScrollPane(queryResultTable),
				BorderLayout.CENTER);
		queryResultTabbedPane.addTab("Result", queryResultTabPanel);

		jPanel2.setLayout(new java.awt.GridBagLayout());

		queryLogToolBar.setRollover(true);
		queryLogToolBar.setFloatable(false);
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
		queryLogTextArea.setFont((null != bitstreamFont) ? bitstreamFont : DEFAULT_TEXT_FONT);
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
	private javax.swing.JButton runSelectedQueryButton;
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
		if (e.getSource().equals(runQueryButton) || e.getSource().equals(runQueryMenuItem)) {
			String query = getLastQuery();
			displayQueryResults(query);
		}
		if (e.getSource().equals(runSelectedQueryButton) || e.getSource().equals(runSelectionMenuItem)) {
			List<String> queryList = getSelectedQueryList();
			if(!queryList.isEmpty())
				displayQueryResults(queryList.get(0));
		}
		if (e.getSource().equals(wrapToggleButton) || e.getSource().equals(wrapCheckBoxMenuItem)) {
			if(wrapToggleButton.isSelected() && wrapCheckBoxMenuItem.isSelected()){
				queryTextArea.setLineWrap(true);
			}else{
				queryTextArea.setLineWrap(false);
			}
		}
	}

	public void displayQueryResults(final String q) {
		SqlQuery sqlQuery = new SqlQuery(q);
		if(QueryTypeEnum.SELECT.equals(sqlQuery.getQueryType())){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						queryResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						queryResultTable.setCellSelectionEnabled(true);
						queryResultTable.setModel(factory.getResultSetTableModel(q));
						queryResultTabbedPane.setSelectedIndex(0);
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, new String[] {
						ex.getClass().getName() + ": ", ex.getMessage() });
					}
				}
			});
		}
		else {
			queryExecutionService = new QueryExecutionServiceImpl(getConnectionProperties());
			try {
				int row = queryExecutionService.executeNonQuery(sqlQuery);
				queryLogTextArea.append("[ " + row + " ] rows updated.\n");
				queryResultTabbedPane.setSelectedIndex(1);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
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

	public List<String> getQueryList() {
		return getQueryList(queryTextArea.getText());
	}

	public List<String> getQueryList(String text) {
		if (!StringUtil.hasValidContent(text))
			return new ArrayList<String>();
		List<String> queryList = new ArrayList<String>();
		BufferedReader br = null;
		boolean hasDelem = false;
		if(text.contains(";"))
			hasDelem = true;
		try {
			br = new BufferedReader(new StringReader(text));
			char[] cBuff = new char[1];
			int count = 0;
			StringBuffer qBuff = new StringBuffer();
			while ((count = br.read(cBuff, 0, cBuff.length)) >= 0) {
				char ch = cBuff[0];
				if (';' != ch) {
					if ('\r' == ch || '\n' == ch)
						qBuff.append(' ');
					else
						qBuff.append(ch);
				} else {
					queryList.add(qBuff.toString());
					qBuff = new StringBuffer();
				}
			}
			if(!hasDelem)
				queryList.add(qBuff.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeReader(br);
		}

		return queryList;
	}

	public String getLastQuery() {
		List<String> queryList = getSelectedQueryList(); 
		if (!queryList.isEmpty()) {
			return queryList.get(0);
		}
		queryList = getQueryList();
		if (!queryList.isEmpty()) {
			return queryList.get(queryList.size() - 1);
		}
		return "";
	}

	public List<String> getSelectedQueryList() {
		return getQueryList(queryTextArea.getSelectedText());
	}
}
