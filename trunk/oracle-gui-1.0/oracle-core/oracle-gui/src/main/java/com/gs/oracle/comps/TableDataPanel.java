/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author Green Moon
 *
 */
public class TableDataPanel extends JPanel {

	private JButton refreshButton, addRecordButton, editRecordButton, deleteRecordButton,
		filterDataButton;
	private JTable dataTable;
	private JToolBar dataToolBar;
	private String tableName, schemaName;
	private ConnectionProperties connectionProperties;
	private ResultSetTableModelFactory resultSetTableModelFactory;
	
	
	public TableDataPanel(String schemaName, String tableName,
			ConnectionProperties connectionProperties) {
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.connectionProperties = connectionProperties;
		try {
			this.resultSetTableModelFactory = new ResultSetTableModelFactory(connectionProperties.getDataSource().getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponent();
	}


	private void initComponent() {
		Icon image = null;
		GridBagConstraints gridBagConstraints = null;
		Insets insets = null;
		
		refreshButton = new JButton();
		addRecordButton = new JButton();
		editRecordButton = new JButton();
		deleteRecordButton = new JButton();
		filterDataButton = new JButton();
		dataToolBar = new JToolBar();
		dataTable = new JTable();

		setLayout(new GridBagLayout());
		
		dataToolBar.setFloatable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshButton.setIcon(image);
		refreshButton.setFocusable(false);
		dataToolBar.add(refreshButton);
		dataToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "add_plus.png"));
		addRecordButton.setIcon(image);
		addRecordButton.setFocusable(false);
		dataToolBar.add(addRecordButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "editor_area.gif"));
		editRecordButton.setIcon(image);
		editRecordButton.setFocusable(false);
		dataToolBar.add(editRecordButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "delete_edit.gif"));
		deleteRecordButton.setIcon(image);
		deleteRecordButton.setFocusable(false);
		dataToolBar.add(deleteRecordButton);
		dataToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "systemfilterpool.gif"));
		filterDataButton.setIcon(image);
		filterDataButton.setFocusable(false);
		dataToolBar.add(filterDataButton);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(dataToolBar, gridBagConstraints);

		
		
		dataTable.setAutoCreateRowSorter(true);
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dataTable.setAutoscrolls(true);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		insets = new Insets(1,1,1,1);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = insets;
		
		
		OracleDbGrabber dbGrabber = new OracleDbGrabber();
		try {
			String query = "SELECT * FROM " + schemaName + "." + tableName.toUpperCase();
			dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dataTable.setCellSelectionEnabled(true);
			dataTable.setModel(resultSetTableModelFactory.getResultSetTableModel(query));
			/*JTextField textField = new JTextField();
			//textField.setEditable(false);
	        textField.setBorder(BorderFactory.createEmptyBorder());
	        DefaultCellEditor editor = new DefaultCellEditor(textField);
	        editor.setClickCountToStart(1);
			dataTable.setCellEditor(new StringActionTableCellEditor(editor));*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		add(new JScrollPane(dataTable), gridBagConstraints);

	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}


	public String getSchemaName() {
		return schemaName;
	}


	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}


}
