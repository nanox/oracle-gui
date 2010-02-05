/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: ConnectionDialog.java
 *	Type	: com.gs.oracle.dlg.ConnectionDialog.java
 *	Date	: Jul 30, 2009	9:48:50 AM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.oracle.dlg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.command.GuiCommandConstants;
import com.gs.oracle.command.GuiEventHandler;
import com.gs.oracle.common.StringUtil;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.connection.ConnectionPropertiesCatalog;
import com.gs.oracle.io.IOUtils;
import com.gs.oracle.io.XmlRWUtils;
import com.gs.oracle.util.ConnectionPropertiesRWUtil;
import com.gs.oracle.util.DisplayTypeEnum;
import com.gs.oracle.util.DisplayUtils;

/**
 * @author Sabuj Das
 * 
 */
public class ConnectionDialog extends JDialog {

	/**
	 * serialVersionUID = -2433429146983917444L;
	 */
	private static final long serialVersionUID = -2433429146983917444L;
	
	private static final Logger logger = Logger.getLogger(ConnectionDialog.class);
	
	private JTextField schemaNameTextField;
	private ConnectionPropertiesCatalog catalog; 
	private Vector<String> connectionNames = new Vector<String>();
	private JFrame parentFrame;
	
	public ConnectionDialog(JFrame parent, boolean modal) {
		super(parent, modal);
		if(logger.isDebugEnabled())
			logger.debug("Opening connection dialog.");
		parentFrame = parent;
		
		InputStream mappingStream = IOUtils.getResourceAsStream(OracleGuiConstants.CONN_PROPERTIES_MAPPING_FILE);
		File dataFile = IOUtils.mkfile(OracleGuiConstants.CONNECTION_DATA_FILE);
		
		catalog = XmlRWUtils.readUsingCastor(dataFile, mappingStream);
		ConnectionProperties connectionProperties = null;
		if(catalog != null ){
			if(catalog.getConnectionPropertiesList() != null
				&& catalog.getConnectionPropertiesList().size() > 0){
				if(logger.isDebugEnabled())
					logger.debug("[ " + catalog.getConnectionPropertiesList().size() + " ] saved connection(s) found.");
				connectionProperties = catalog.getConnectionPropertiesList().first();
				int i = 0;
				for (ConnectionProperties p : catalog.getConnectionPropertiesList()) {
					connectionNames.add(p.getConnectionName());
					i++;
				}
			}
		} else{
			if(logger.isDebugEnabled())
				logger.debug("No saved connections found.");
		}
		setSize(400, 300);
		initComponents();
		setResizable(false);
		setTitle("Connect to Oracle Database");
		getRootPane().setDefaultButton(connectButton);
		if(connectionProperties != null){
			if(logger.isDebugEnabled())
				logger.debug("Populating default connection details.");
			populateConnectionProperties(connectionProperties);
		}
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		jLabel6 = new JLabel();
		buttonGroup1 = new ButtonGroup();
		parentPanel = new JPanel();
		jLabel1 = new JLabel();
		newConnectionButton = new JButton();
		saveConnectionButton = new JButton();
		deleteConnectionButton = new JButton();
		jLabel2 = new JLabel();
		connectionNamesComboBox = new JComboBox();
		editConnNameButton = new JButton();
		settingsTabbedPane = new JTabbedPane();
		httpHostPanel = new JPanel();
		jLabel3 = new JLabel();
		hostAddrTextField = new JTextField();
		jLabel4 = new JLabel();
		userNameTextField = new JTextField();
		jLabel5 = new JLabel();
		pwdPasswordField = new JPasswordField();
		savePwdCheckBox = new JCheckBox();
		jLabel7 = new JLabel();
		portTextField = new JTextField();
		jLabel8 = new JLabel();
		charSetComboBox = new JComboBox();
		sidTextField = new JTextField();
		SIDRadioButton = new JRadioButton();
		serviceNameRadioButton = new JRadioButton();
		serviceNameTextField = new JTextField();
		jPanel2 = new JPanel();
		jSeparator1 = new JSeparator();
		connectButton = new JButton();
		cancelButton = new JButton();
		testConnectionButton = new JButton();

		jLabel6.setText("jLabel6");

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		parentPanel.setLayout(new GridBagLayout());

		jLabel1.setIcon(new ImageIcon(getClass().getResource(
				"/images/Oracle Img.PNG")));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		parentPanel.add(jLabel1, gridBagConstraints);

		newConnectionButton.setText("New ...");
		newConnectionButton.setFocusable(false);
		newConnectionButton.setActionCommand(GuiCommandConstants.NEW_CONNECTION_ACT_CMD);
		newConnectionButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						newConnectionButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(newConnectionButton, gridBagConstraints);

		saveConnectionButton.setText("Save");
		saveConnectionButton.setEnabled(false);
		saveConnectionButton.setFocusable(false);
		saveConnectionButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						saveConnectionButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(saveConnectionButton, gridBagConstraints);

		deleteConnectionButton.setText("Delete");
		deleteConnectionButton.setFocusable(false);
		deleteConnectionButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						deleteConnectionButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(deleteConnectionButton, gridBagConstraints);

		jLabel2.setText("Saved Connections");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(jLabel2, gridBagConstraints);

		connectionNamesComboBox.setModel(new DefaultComboBoxModel(connectionNames));
		
		connectionNamesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				connectionNamesComboBoxActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(connectionNamesComboBox, gridBagConstraints);

		editConnNameButton.setText("Edit ...");
		editConnNameButton.setFocusable(false);
		editConnNameButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						editConnNameButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(editConnNameButton, gridBagConstraints);

		httpHostPanel.setLayout(new GridBagLayout());

		jLabel3.setText("Host Address");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(jLabel3, gridBagConstraints);

		hostAddrTextField.setText("localhost");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(hostAddrTextField, gridBagConstraints);

		jLabel4.setText("User Name");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(jLabel4, gridBagConstraints);

		userNameTextField.setText("user_name");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(userNameTextField, gridBagConstraints);

		jLabel5.setText("Password");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(jLabel5, gridBagConstraints);

		pwdPasswordField.setText("password");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(pwdPasswordField, gridBagConstraints);

		savePwdCheckBox.setText("Save Password");
		savePwdCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				savePwdCheckBoxActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(savePwdCheckBox, gridBagConstraints);

		jLabel7.setText("Port");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(jLabel7, gridBagConstraints);

		portTextField.setText("1521");
//		portTextField.setPreferredSize(new Dimension(55, 20));
//		portTextField.setMinimumSize(portTextField.getPreferredSize());
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(portTextField, gridBagConstraints);

		jLabel8.setText("Character Set");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(jLabel8, gridBagConstraints);

		charSetComboBox.setModel(new DefaultComboBoxModel(
				new String[] {"Default"}));
		charSetComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				charSetComboBoxActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		//gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(charSetComboBox, gridBagConstraints);

		sidTextField.setText("XE");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(sidTextField, gridBagConstraints);

		buttonGroup1.add(SIDRadioButton);
		SIDRadioButton.setSelected(true);
		SIDRadioButton.setText("SID");
		SIDRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SIDRadioButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(SIDRadioButton, gridBagConstraints);

		buttonGroup1.add(serviceNameRadioButton);
		serviceNameRadioButton.setText("Service Name");
		serviceNameRadioButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						serviceNameRadioButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(serviceNameRadioButton, gridBagConstraints);

		serviceNameTextField.setText("");
		serviceNameTextField.setEnabled(serviceNameRadioButton.isSelected() );
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(serviceNameTextField, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(new JLabel("Schema Name"), gridBagConstraints);
		schemaNameTextField = new JTextField("POS_OWNER");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		httpHostPanel.add(schemaNameTextField, gridBagConstraints);

		settingsTabbedPane.addTab("Host Settings", httpHostPanel);

		jPanel2.setLayout(new GridBagLayout());
		settingsTabbedPane.addTab("Tunnel", jPanel2);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(settingsTabbedPane, gridBagConstraints);
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(2, 1, 2, 1);
		parentPanel.add(jSeparator1, gridBagConstraints);

		connectButton.setText("Connect");
		connectButton.setActionCommand(GuiCommandConstants.CREATE_CONNECTION_ACT_CMD);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				connectButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		parentPanel.add(cancelButton, gridBagConstraints);

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		parentPanel.add(testConnectionButton, gridBagConstraints);

		testConnectionButton.setText("Test Connection");
		testConnectionButton.setActionCommand(GuiCommandConstants.TEST_CONNECTION_ACT_CMD);
		testConnectionButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						testConnectionButtonActionPerformed(evt);
					}
				});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(connectButton, gridBagConstraints);

		getContentPane().add(parentPanel, BorderLayout.CENTER);

		pack();
	}

	private void savePwdCheckBoxActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void testConnectionButtonActionPerformed(
			ActionEvent evt) {
		GuiEventHandler handler = new GuiEventHandler();
		handler.setParent(getParent());
		ConnectionProperties properties = new ConnectionProperties("TEST");
		properties.setHostName(hostAddrTextField.getText());
		properties.setPortNumber(Integer.parseInt(portTextField.getText()));
		properties.setUserName(userNameTextField.getText());
		properties.setPassword(pwdPasswordField.getText());
		properties.setSid(sidTextField.getText());
		properties.setDatabaseName(schemaNameTextField.getText());
		handler.setData(properties);
		handler.setSourceForm(this);
		handler.actionPerformed(evt);
		
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
		dispose();
	}

	private void connectButtonActionPerformed(ActionEvent evt) {
		GuiEventHandler handler = new GuiEventHandler();
		handler.setParent(getParentFrame());
		handler.setSourceForm(this);
		ConnectionProperties properties = new ConnectionProperties("TEST");
		properties.setHostName(hostAddrTextField.getText());
		properties.setPortNumber(Integer.parseInt(portTextField.getText()));
		properties.setUserName(userNameTextField.getText());
		properties.setPassword(pwdPasswordField.getText());
		properties.setSid(sidTextField.getText());
		properties.setDatabaseName(schemaNameTextField.getText());
		handler.setData(properties);
		handler.setSourceForm(this);
		handler.actionPerformed(evt);
		
	}

	private void serviceNameRadioButtonActionPerformed(
			ActionEvent evt) {
		if(serviceNameRadioButton.isSelected()){
			serviceNameTextField.setEnabled(true);
			sidTextField.setEnabled(false);
		} else if(!serviceNameRadioButton.isSelected()){
			serviceNameTextField.setEnabled(false);
			sidTextField.setEnabled(true);
		}
	}

	private void SIDRadioButtonActionPerformed(ActionEvent evt) {
		if(SIDRadioButton.isSelected()){
			sidTextField.setEnabled(true);
			serviceNameTextField.setEnabled(false);
		} else if(!SIDRadioButton.isSelected()){
			sidTextField.setEnabled(false);
			serviceNameTextField.setEnabled(true);
		}
	}

	private void charSetComboBoxActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void newConnectionButtonActionPerformed(
			ActionEvent evt) {
		String name = DisplayUtils.readString("Enter a new connection name:");
		if(!StringUtil.hasValidContent(name)){
			DisplayUtils.displayMessage(this.getParent(), 
					"Please enter a valid connection name.",DisplayTypeEnum.ERROR);
			return;
		}
		ConnectionProperties p = new ConnectionProperties(name);
		populateConnectionProperties(p);
		catalog.add(p);
		connectionNames.add(name);
		connectionNamesComboBox.setModel(new DefaultComboBoxModel(connectionNames));
		connectionNamesComboBox.setSelectedItem(name);
		if(catalog.isConnPropModified()){
			saveConnectionButton.setEnabled(true);
		}else{
			saveConnectionButton.setEnabled(false);
		}
	}

	private void saveConnectionButtonActionPerformed(
			ActionEvent evt) {
		String name = connectionNamesComboBox.getSelectedItem().toString();
		ConnectionProperties p = catalog.getByName(name);
		populatePropsToConnProps(p);
		ConnectionPropertiesRWUtil.getInstance().saveCatalog(catalog);
//		catalog.setSaved();
//		XmlRWUtils.wr
		saveConnectionButton.setEnabled(false);
	}
	
	private void populatePropsToConnProps(ConnectionProperties properties){
		properties.setHostName(hostAddrTextField.getText());
		try{
			properties.setPortNumber(Integer.parseInt(portTextField.getText()));
		}catch(Exception e){
			properties.setPortNumber(1521);
		}
		properties.setUserName(userNameTextField.getText());
		properties.setPassword(pwdPasswordField.getText());
		properties.setSid(sidTextField.getText());
		properties.setServiceName(serviceNameTextField.getText());
		properties.setDatabaseName(schemaNameTextField.getText());
	}

	private void deleteConnectionButtonActionPerformed(
			ActionEvent evt) {
		String name = connectionNamesComboBox.getSelectedItem().toString();
		ConnectionProperties p = catalog.getByName(name);
		catalog.getConnectionPropertiesList().remove(p);
		connectionNames.remove(name);
		connectionNamesComboBox.setModel(new DefaultComboBoxModel(connectionNames));
		connectionNamesComboBox.setSelectedIndex(0);
		for (ConnectionProperties prop : catalog.getConnectionPropertiesList()) {
			prop.setDisplayOrder(prop.getDisplayOrder() - 1);
		}
		ConnectionPropertiesRWUtil.getInstance().saveCatalog(catalog);
	}

	private void connectionNamesComboBoxActionPerformed(ActionEvent evt) {
		String name = connectionNamesComboBox.getSelectedItem().toString();
		ConnectionProperties p = catalog.getByName(name);
		if(p != null){
			populateConnectionProperties(p);
		}
	}
	
	private void populateConnectionProperties(ConnectionProperties p){
		hostAddrTextField.setText(p.getHostName());
		portTextField.setText(""+p.getPortNumber());
		userNameTextField.setText(p.getUserName());
		pwdPasswordField.setText(p.getPassword());
		if(StringUtil.hasValidContent(p.getSid())){
			sidTextField.setText(p.getSid());
			SIDRadioButton.setSelected(true);
		}else if(StringUtil.hasValidContent(p.getServiceName())){
			serviceNameTextField.setText(p.getServiceName());
			serviceNameRadioButton.setSelected(true);
		}
		schemaNameTextField.setText(p.getDatabaseName());
	}

	private void editConnNameButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}



	// Variables declaration - do not modify
	private JRadioButton SIDRadioButton;
	private ButtonGroup buttonGroup1;
	private JButton cancelButton;
	private JComboBox charSetComboBox;
	private JButton connectButton;
	private JButton deleteConnectionButton;
	private JButton editConnNameButton;
	private JTextField hostAddrTextField;
	private JPanel httpHostPanel;
	private JComboBox connectionNamesComboBox;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JPanel jPanel2;
	private JSeparator jSeparator1;
	private JButton newConnectionButton;
	private JPanel parentPanel;
	private JTextField portTextField;
	private JPasswordField pwdPasswordField;
	private JButton saveConnectionButton;
	private JCheckBox savePwdCheckBox;
	private JRadioButton serviceNameRadioButton;
	private JTextField serviceNameTextField;
	private JTabbedPane settingsTabbedPane;
	private JTextField sidTextField;
	private JButton testConnectionButton;
	private JTextField userNameTextField;
	// End of variables declaration

	public void disableButtons(boolean b){
		connectButton.setEnabled(!b);
		testConnectionButton.setEnabled(!b);
		cancelButton.setEnabled(!b);
	}

	public JFrame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}
}
