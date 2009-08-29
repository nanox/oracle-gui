/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.gs.oracle.connection.OracleConnectionUtil;
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Database;
import com.gs.oracle.model.Schema;
import com.gs.oracle.model.Table;
import com.gs.oracle.util.ConnectionUtil;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class ColumnDetailsPanel extends JPanel implements ActionListener,
		OracleGuiConstants {

	private JButton refreshButton, addColumnButton, editColumnButton, dropColumnButton;
	private JTable columDetailsTable;
	private JToolBar columnToolBar;
	private String tableName;
	private ConnectionProperties connectionProperties;
	private ResultSetTableModelFactory factory;
	
	public ColumnDetailsPanel(String tableName, ConnectionProperties connectionProperties) {
		this.tableName = tableName;
		this.connectionProperties = connectionProperties;
		initComponents();
	}
	
	private void initComponents() {
		Icon image = null;
		GridBagConstraints gridBagConstraints = null;
		Insets insets = null;
		
		refreshButton = new JButton();
		addColumnButton = new JButton("+");
		editColumnButton = new JButton("E");
		dropColumnButton = new JButton("-");
		columnToolBar = new JToolBar();
		columDetailsTable = new JTable();

		setLayout(new GridBagLayout());
		
		columnToolBar.setFloatable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshButton.setIcon(image);
		refreshButton.setFocusable(false);
		columnToolBar.add(refreshButton);
		columnToolBar.add(new JToolBar.Separator());
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//addColumnButton.setIcon(image);
		columnToolBar.add(addColumnButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//editColumnButton.setIcon(image);
		columnToolBar.add(editColumnButton);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "execution_obj.gif"));
		//dropColumnButton.setIcon(image);
		columnToolBar.add(dropColumnButton);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(columnToolBar, gridBagConstraints);

		
		
		columDetailsTable.setAutoCreateRowSorter(true);
		columDetailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		columDetailsTable.setAutoscrolls(true);
		
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
			conn = OracleConnectionUtil.getConnection(connectionProperties);//connectionProperties.getDataSource().getConnection();
			Database db = dbGrabber.grabDatabase(conn, connectionProperties.getDatabaseName());
			/*dbGrabber.getColumnList(connectionProperties.getDatabaseName(), 
					tableName, conn)*/
			List<Column> cl = new ArrayList<Column>();
			List<Schema> sL = db.getSchemaList();
			for (Schema s : sL) {
				if(connectionProperties.getDatabaseName().equalsIgnoreCase(s.getModelName())){
					List<Table> lT = s.getTableList();
					for (Table t : lT) {
						if(tableName.equalsIgnoreCase(t.getModelName())){
							cl = t.getColumnlist();
							break;
						}
					}
				}
			}
			
			
			columDetailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			columDetailsTable.setModel(new ColumnDetailsTableModel(cl));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
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
		
		/*columDetailsTable.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				OracleDbGrabber dbGrabber = new OracleDbGrabber();
				try {
					ResultSet rs = dbGrabber.grabColumnDetails(connectionProperties.getDatabaseName(), 
							tableName, connectionProperties.getConnection());
					int colCount = dbGrabber.grabColumnCount(connectionProperties.getDatabaseName(), 
							tableName, connectionProperties.getConnection());
					factory = new ResultSetTableModelFactory(connectionProperties.getConnection());
					columDetailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					columDetailsTable.setModel(factory.getResultSetTableModel(rs, colCount));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});*/
		add(new JScrollPane(columDetailsTable), gridBagConstraints);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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

}
