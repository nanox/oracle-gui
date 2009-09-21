/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.util.DisplayTypeEnum;
import com.gs.oracle.util.DisplayUtils;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author Green Moon
 *
 */
public class TableContentPanel extends JPanel implements ActionListener{
	private String schemaName; 
	private String tableName;
	private ConnectionProperties connectionProperties;
	
	private JButton refreshButton, addRecordButton, editRecordButton, deleteRecordButton,
		filterDataButton;
	private JTable sampleContentTable;
	private JToolBar contentToolBar;
	private ResultSetTableModelFactory resultSetTableModelFactory;
	private String queryString;
	private String currentFilter = "";
	
	public TableContentPanel(String schemaName, String tableName,
			ConnectionProperties connectionProperties) {
		this.schemaName = schemaName; 
		this.tableName = tableName;
		this.connectionProperties = connectionProperties;
		
		try {
			this.resultSetTableModelFactory = new ResultSetTableModelFactory(
					connectionProperties.getDataSource().getConnection());
		} catch (SQLException e) {
			DisplayUtils.displayMessage(null, e.getMessage(), DisplayTypeEnum.ERROR);
		}
		queryString = "SELECT * FROM " + schemaName + "." + tableName.toUpperCase();
		initComponents();
		
		showContent(queryString);
	}

	private void showContent(String query) {
		OracleDbGrabber dbGrabber = new OracleDbGrabber();
		Connection conn = null;
		try {
			sampleContentTable.setModel(resultSetTableModelFactory.getResultSetTableModel(query));
		}catch(Exception e){
			DisplayUtils.displayMessage(null, e.getMessage(), DisplayTypeEnum.ERROR);
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private void initComponents() {
		Icon image = null;
		GridBagConstraints gridBagConstraints = null;
		Insets insets = null;
		
		refreshButton = new JButton();
		addRecordButton = new JButton();
		editRecordButton = new JButton();
		deleteRecordButton = new JButton();
		filterDataButton = new JButton();
		contentToolBar = new JToolBar();
		sampleContentTable = new JTable();

		setLayout(new GridBagLayout());
		
		refreshButton.setToolTipText("Refresh");
		addRecordButton.setToolTipText("Add Record");
		editRecordButton.setToolTipText("Edit Record");
		deleteRecordButton.setToolTipText("Delete Record");
		filterDataButton.setToolTipText("Apply Filter");
		
		refreshButton.addActionListener(this);
		addRecordButton.addActionListener(this);
		editRecordButton.addActionListener(this);
		deleteRecordButton.addActionListener(this);
		filterDataButton.addActionListener(this);
		
		contentToolBar.setFloatable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshButton.setIcon(image);
		refreshButton.setFocusable(false);
		contentToolBar.add(refreshButton);
		contentToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "add_plus.png"));
		addRecordButton.setIcon(image);
		addRecordButton.setFocusable(false);
		contentToolBar.add(addRecordButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "editor_area.gif"));
		editRecordButton.setIcon(image);
		editRecordButton.setFocusable(false);
		contentToolBar.add(editRecordButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "delete_edit.gif"));
		deleteRecordButton.setIcon(image);
		deleteRecordButton.setFocusable(false);
		contentToolBar.add(deleteRecordButton);
		contentToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "systemfilterpool.gif"));
		filterDataButton.setIcon(image);
		filterDataButton.setFocusable(false);
		contentToolBar.add(filterDataButton);
		
		contentToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//addColumnButton.setIcon(image);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//editColumnButton.setIcon(image);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//dropColumnButton.setIcon(image);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(contentToolBar, gridBagConstraints);

		
		
		sampleContentTable.setAutoCreateRowSorter(true);
		sampleContentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sampleContentTable.setCellSelectionEnabled(true);
		sampleContentTable.setAutoscrolls(true);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		insets = new Insets(1,1,1,1);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = insets;
		
		add(new JScrollPane(sampleContentTable), gridBagConstraints);
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource().equals(refreshButton)){
			showContent(queryString);
		} else if(evt.getSource().equals(filterDataButton)){
			applyFilter();
		} 
	}

	private void applyFilter() {
		ResultFilterDialog filterDialog = new ResultFilterDialog(null, true);
		filterDialog.setFilterQuery(currentFilter);
		filterDialog.setInputQuery(queryString);
		filterDialog.setAlwaysOnTop(true);
		filterDialog.setLocation(100, 100);
		int opt = filterDialog.showFilterDialog();
		if(opt == ResultFilterDialog.APPLY_OPTION){
			currentFilter = filterDialog.getFilterQuery();
			showContent(filterDialog.getOutputQuery());
		}
	}

}
