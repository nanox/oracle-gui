/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Constraint;
import com.gs.oracle.model.Table;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class ConstraintsDetailsPanel extends JPanel {

	private String schemaName; 
	private String tableName;
	private ConnectionProperties connectionProperties;
	
	private JButton refreshButton, addConstraintButton, editConstraintButton, dropConstraintButton;
	private JTable constraintDetailsTable;
	private JToolBar constraintToolBar;
	private ResultSetTableModelFactory resultSetTableModelFactory;
	
	public ConstraintsDetailsPanel(String schemaName, String tableName,
			ConnectionProperties connectionProperties) {
		this.schemaName = schemaName; 
		this.tableName = tableName;
		this.connectionProperties = connectionProperties;
		
		try {
			this.resultSetTableModelFactory = new ResultSetTableModelFactory(
					connectionProperties.getDataSource().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initComponents();
	}

	private void initComponents() {
		Icon image = null;
		GridBagConstraints gridBagConstraints = null;
		Insets insets = null;
		
		refreshButton = new JButton();
		addConstraintButton = new JButton("+");
		editConstraintButton = new JButton("E");
		dropConstraintButton = new JButton("-");
		constraintToolBar = new JToolBar();
		constraintDetailsTable = new JTable();

		setLayout(new GridBagLayout());
		
		constraintToolBar.setFloatable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshButton.setIcon(image);
		refreshButton.setFocusable(false);
		constraintToolBar.add(refreshButton);
		constraintToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//addColumnButton.setIcon(image);
		constraintToolBar.add(addConstraintButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//editColumnButton.setIcon(image);
		constraintToolBar.add(editConstraintButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//dropColumnButton.setIcon(image);
		constraintToolBar.add(dropConstraintButton);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(constraintToolBar, gridBagConstraints);

		
		
		constraintDetailsTable.setAutoCreateRowSorter(true);
		constraintDetailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		constraintDetailsTable.setCellSelectionEnabled(true);
		constraintDetailsTable.setAutoscrolls(true);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		insets = new Insets(1,1,1,1);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = insets;
		
		
		OracleDbGrabber dbGrabber = new OracleDbGrabber();
		Connection conn = null;
		try {
			String query = "select * from all_constraints where owner='"+
				schemaName +"' and TABLE_NAME = '" + tableName +"'";
			
			constraintDetailsTable.setModel(resultSetTableModelFactory.getResultSetTableModel(query));
			
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ 
		}catch(Exception e){
			e.printStackTrace();
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
		
		
		add(new JScrollPane(constraintDetailsTable), gridBagConstraints);
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

	
}
