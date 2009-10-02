/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import oracle.sql.ROWID;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.dlg.QuickEditDialog;
import com.gs.oracle.dlg.TableDataEditorDialog;
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.model.Table;
import com.gs.oracle.util.DisplayTypeEnum;
import com.gs.oracle.util.DisplayUtils;
import com.gs.oracle.util.MenuBarUtil;
import com.gs.oracle.vo.QuickEditVO;

/**
 * @author Green Moon
 *
 */
public class TableDataPanel extends JPanel implements ActionListener{

	private JButton refreshButton, addRecordButton, editRecordButton, deleteRecordButton,
		filterDataButton;
	private JTable dataTable;
	private JToolBar dataToolBar;
	private String tableName, schemaName;
	private ConnectionProperties connectionProperties;
	private ResultSetTableModelFactory resultSetTableModelFactory;
	private String queryString;
	private String currentFilter = "";
	private JFrame parentFrame;
	
	public TableDataPanel(JFrame parent, String schemaName, String tableName,
			ConnectionProperties connectionProperties) {
		this.parentFrame = parent;
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.connectionProperties = connectionProperties;
		try {
			this.resultSetTableModelFactory = new ResultSetTableModelFactory(connectionProperties.getDataSource().getConnection());
		} catch (SQLException e) {
			DisplayUtils.displayMessage(getParentFrame(), e.getMessage(), DisplayTypeEnum.ERROR);
		}
		queryString = "SELECT * FROM " + schemaName + "." + tableName.toUpperCase();
		initComponent();
		showTableData(queryString);
	}


	private void showTableData(String query) {
		OracleDbGrabber dbGrabber = new OracleDbGrabber();
		try {
			dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dataTable.setCellSelectionEnabled(true);
			dataTable.setModel(resultSetTableModelFactory.getResultSetTableModel(query));
		} catch (SQLException e) {
			DisplayUtils.displayMessage(getParentFrame(), e.getMessage(), DisplayTypeEnum.ERROR);
		} catch(Exception e){
			e.printStackTrace();
		}
		
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

		
		dataTable.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(e.getClickCount() == 2){
						com.gs.oracle.vo.QuickEditVO vo = new com.gs.oracle.vo.QuickEditVO();
						vo.setTableName(tableName);
						vo.setSchemaName(schemaName);
						int columnIndex = dataTable.getSelectedColumn();
						int rowIndex = dataTable.getSelectedRow();
						int columnCount = dataTable.getColumnCount();
						String q = "SELECT ROWID, ORA_ROWSCN FROM " + getSchemaName() + "." + getTableName() + " WHERE ";
						for (int i = 0; i < columnCount; i++) {
							if(dataTable.getModel().getValueAt(rowIndex, i) == null)
								continue;
							Class clazz = dataTable.getColumnClass(i);
							if(clazz.getCanonicalName().equalsIgnoreCase("java.util.Date") 
									|| clazz.getCanonicalName().equalsIgnoreCase("java.sql.Date"))
								continue;
							q += dataTable.getModel().getColumnName(i) + " = '"
								+ (
								(dataTable.getModel().getValueAt(rowIndex, i) != null)
								? dataTable.getModel().getValueAt(rowIndex, i).toString() : "") + "' ";
							if(i != columnCount-1){
								q += "AND ";
							}
						}
						Connection con = null;
						try{
							con = getConnectionProperties().getDataSource().getConnection();
							Statement stmt = con.prepareStatement(q);
							ResultSet rs = stmt.executeQuery(q);
							if(rs == null)
								return;
							while(rs.next()){
								ROWID rid = (ROWID) rs.getObject("ROWID");
								if(rid != null){
									vo.setRowid(rid.stringValue());
								}
								String x = rs.getString("ORA_ROWSCN");
								if(x != null){
									vo.setOraRowscn(x);
								}
							}
						}catch(Exception ex){
							return;
						}finally{
							if(con != null){
								try {
									con.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
						vo.setCurrentColumnName(dataTable.getModel().getColumnName(columnIndex));
						vo.setCurrentColumnValue(dataTable.getModel().getValueAt(rowIndex, columnIndex).toString());
						vo.setConnectionProperties(getConnectionProperties());
						openQuickEditDialog(vo);
					}
				}
			}


			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		refreshButton.setToolTipText("Refresh");
		addRecordButton.setToolTipText("Add Record");
		editRecordButton.setToolTipText("Edit Record");
		deleteRecordButton.setToolTipText("Delete Record");
		filterDataButton.setToolTipText("Apply Filter");
		
		setLayout(new GridBagLayout());
		
		dataToolBar.setFloatable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshButton.setIcon(image);
		refreshButton.addActionListener(this);
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
		editRecordButton.addActionListener(this);
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
		filterDataButton.addActionListener(this);
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


	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource().equals(refreshButton)){
			showTableData(queryString);
		} else if(evt.getSource().equals(filterDataButton)){
			applyFilter();
		} else if(evt.getSource().equals(editRecordButton)){
			editRecord();
		} 
	}


	private void applyFilter() {
		ResultFilterDialog filterDialog = new ResultFilterDialog(getParentFrame(), true);
		filterDialog.setFilterQuery(currentFilter);
		filterDialog.setInputQuery(queryString);
		filterDialog.setAlwaysOnTop(true);
		filterDialog.setLocation(100, 100);
		int opt = filterDialog.showFilterDialog();
		if(opt == OracleGuiConstants.APPLY_OPTION){
			currentFilter  = filterDialog.getFilterQuery();
			showTableData(filterDialog.getOutputQuery());
		}
	}

	public void editRecord(){
		TableDataEditorDialog dataEditorDialog = new TableDataEditorDialog(getParentFrame(), dataTable);
		dataEditorDialog.setSchemaName(schemaName);
		dataEditorDialog.setTableName(tableName);
		dataEditorDialog.setConnectionProperties(connectionProperties);
		dataEditorDialog.setLocation(100, 100);
		int opt = dataEditorDialog.showEditorDialog();
		if(opt == OracleGuiConstants.APPLY_OPTION){
			
		}
	}
	
	public void openQuickEditDialog(QuickEditVO vo) {
		QuickEditDialog editDialog = new QuickEditDialog(getParentFrame(), vo);
		int opt = editDialog.showDialog();
		if(opt == OracleGuiConstants.APPLY_OPTION){
			showTableData(queryString);
		}
	}

	/**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

}
