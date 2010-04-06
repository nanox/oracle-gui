/**
 * 
 */
package com.gs.dbex.application.connection;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.gs.dbex.application.constants.ApplicationConstants;
import com.gs.dbex.application.constants.GuiCommandConstants;
import com.gs.dbex.application.event.ApplicationEventHandler;
import com.gs.dbex.common.enums.DatabaseTypeEnum;
import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public class ConnectionDialog extends JDialog implements ActionListener{


    public ConnectionDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        storageTypeButtonGroup = new ButtonGroup();
        jLabel1 = new JLabel();
        driverNameTextField = new JTextField();
        jLabel2 = new JLabel();
        urlTextField = new JTextField();
        jLabel3 = new JLabel();
        uidTextField = new JTextField();
        connectButton = new JButton();
        jLabel4 = new JLabel();
        pwdPasswordField1 = new JPasswordField();
        driverManagerButton = new JButton();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        databaseTypeComboBox = new JComboBox();
        cancelButton = new JButton();
        testButton = new JButton();
        jLabel10 = new JLabel();
        statusLabel = new JLabel();
        jLabel9 = new JLabel();
        jLabel12 = new JLabel();
        catalogRadioButton = new JRadioButton();
        schemaRadioButton = new JRadioButton();
        jLabel13 = new JLabel();
        hostNameTextField = new JTextField();
        portNumberFormattedTextField = new JFormattedTextField();
        connectionNameComboBox = new JComboBox();
        jToolBar1 = new JToolBar();
        newConnectionPropButton = new JButton();
        openConnectionPropButton = new JButton();
        jSeparator3 = new JToolBar.Separator();
        loadConnectionPropButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        saveConnectionPropButton = new JButton();
        saveAsConnectionPropButton = new JButton();
        saveAllConnectionPropButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        clearConnectionPropButton = new JButton();
        deleteConnectionPropButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Connect");
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());

        jLabel1.setText("Driver Class Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(driverNameTextField, gridBagConstraints);

        jLabel2.setText("URL");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(urlTextField, gridBagConstraints);

        jLabel3.setText("User ID");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel3, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(uidTextField, gridBagConstraints);

        connectButton.setText("Connect");
        connectButton.addActionListener(this);
        connectButton.setActionCommand(GuiCommandConstants.CREATE_CONNECTION_ACT_CMD);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new Insets(2, 2, 4, 2);
        getContentPane().add(connectButton, gridBagConstraints);

        jLabel4.setText("Password");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(pwdPasswordField1, gridBagConstraints);

        driverManagerButton.setText("Driver Manager");
        driverManagerButton.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(driverManagerButton, gridBagConstraints);

        jLabel5.setText("Connection Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel5, gridBagConstraints);

        jLabel6.setText("Database Type");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(6, 2, 2, 2);
        getContentPane().add(jLabel6, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(6, 2, 2, 2);
        databaseTypeComboBox.addActionListener(this);
        getContentPane().add(databaseTypeComboBox, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new Insets(2, 2, 4, 2);
        getContentPane().add(cancelButton, gridBagConstraints);

        testButton.setText("Test");
        testButton.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new Insets(2, 2, 4, 2);
        getContentPane().add(testButton, gridBagConstraints);

        jLabel10.setText("Status");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel10, gridBagConstraints);

        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(statusLabel, gridBagConstraints);

        jLabel9.setText("Port");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel9, gridBagConstraints);

        jLabel12.setText("Storage Type");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel12, gridBagConstraints);

        storageTypeButtonGroup.add(catalogRadioButton);
        catalogRadioButton.setText("Catalog");
        catalogRadioButton.addActionListener(this);
        catalogRadioButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(catalogRadioButton, gridBagConstraints);

        storageTypeButtonGroup.add(schemaRadioButton);
        schemaRadioButton.setSelected(true);
        schemaRadioButton.addActionListener(this);
        schemaRadioButton.setText("Schema");
        schemaRadioButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(schemaRadioButton, gridBagConstraints);

        jLabel13.setText("Host Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jLabel13, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(hostNameTextField, gridBagConstraints);

        portNumberFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new java.text.DecimalFormat("#0"))));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(portNumberFormattedTextField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        connectionNameComboBox.addActionListener(this);
        getContentPane().add(connectionNameComboBox, gridBagConstraints);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMinimumSize(new Dimension(100, 25));

        newConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "new_untitled_text_file.gif"))); 
        newConnectionPropButton.setText("New");
        newConnectionPropButton.addActionListener(this);
        newConnectionPropButton.setFocusable(false);
        newConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(newConnectionPropButton);

        openConnectionPropButton.addActionListener(this);
        openConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "open.gif"))); 
        openConnectionPropButton.setText("Open");
        openConnectionPropButton.setFocusable(false);
        openConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        openConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(openConnectionPropButton);
        jToolBar1.add(jSeparator3);

        loadConnectionPropButton.addActionListener(this);
        loadConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "go-down.png"))); 
        loadConnectionPropButton.setText("Load");
        loadConnectionPropButton.setFocusable(false);
        loadConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        loadConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(loadConnectionPropButton);
        jToolBar1.add(jSeparator1);

        saveConnectionPropButton.addActionListener(this);
        saveConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "save_edit.gif"))); 
        saveConnectionPropButton.setText("Save");
        saveConnectionPropButton.setFocusable(false);
        saveConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(saveConnectionPropButton);

        saveAsConnectionPropButton.addActionListener(this);
        saveAsConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "saveas_edit.gif"))); 
        saveAsConnectionPropButton.setText("Save As");
        saveAsConnectionPropButton.setFocusable(false);
        saveAsConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveAsConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(saveAsConnectionPropButton);

        saveAllConnectionPropButton.addActionListener(this);
        saveAllConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "saveall_edit.gif"))); 
        saveAllConnectionPropButton.setText("Save All");
        saveAllConnectionPropButton.setFocusable(false);
        saveAllConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveAllConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(saveAllConnectionPropButton);
        jToolBar1.add(jSeparator2);

        clearConnectionPropButton.addActionListener(this);
        clearConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "clear_co.gif"))); 
        clearConnectionPropButton.setText("Clear");
        clearConnectionPropButton.setFocusable(false);
        clearConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clearConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(clearConnectionPropButton);

        deleteConnectionPropButton.addActionListener(this);
        deleteConnectionPropButton.setIcon(new ImageIcon(getClass().getResource(ApplicationConstants.IMAGE_PATH + "delete_edit.gif"))); 
        deleteConnectionPropButton.setText("Delete");
        deleteConnectionPropButton.setFocusable(false);
        deleteConnectionPropButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteConnectionPropButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(deleteConnectionPropButton);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        getContentPane().add(jToolBar1, gridBagConstraints);

        pack();
    }

    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConnectionDialog dialog = new ConnectionDialog(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private JButton cancelButton;
    private JRadioButton catalogRadioButton;
    private JButton clearConnectionPropButton;
    private JComboBox databaseTypeComboBox;
    private JButton connectButton;
    private JButton deleteConnectionPropButton;
    private JButton driverManagerButton;
    private JTextField driverNameTextField;
    private JTextField hostNameTextField;
    private JComboBox connectionNameComboBox;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel9;
    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JToolBar.Separator jSeparator3;
    private JToolBar jToolBar1;
    private JButton loadConnectionPropButton;
    private JButton newConnectionPropButton;
    private JButton openConnectionPropButton;
    private JFormattedTextField portNumberFormattedTextField;
    private JPasswordField pwdPasswordField1;
    private JButton saveAllConnectionPropButton;
    private JButton saveAsConnectionPropButton;
    private JButton saveConnectionPropButton;
    private JRadioButton schemaRadioButton;
    private JLabel statusLabel;
    private ButtonGroup storageTypeButtonGroup;
    private JButton testButton;
    private JTextField uidTextField;
    private JTextField urlTextField;

    public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(connectButton)){
			connect(e);
		}
	}
    
    private void connect(ActionEvent evt){
    	ApplicationEventHandler handler = new ApplicationEventHandler();
		handler.setParent(getParent());
		handler.setSourceForm(this);

		ConnectionProperties connectionProperties = new ConnectionProperties();
		connectionProperties.setConnectionName("TestOracle");
		connectionProperties.setConnectionUrl("jdbc:oracle:thin:hr/hr@localhost:1521/XE");
		connectionProperties.setDatabaseType(DatabaseTypeEnum.ORACLE.getCode());
		connectionProperties.setUserName("hr");
		connectionProperties.setPassword("hr");
		connectionProperties.setHostName("localhost");
		connectionProperties.setPortNumber(1521);
		connectionProperties.setSidServiceName("XE");
		
		handler.setData(connectionProperties);
		handler.setSourceForm(this);
		handler.actionPerformed(evt);
    }

    private void populateOracleValues(){
    	
    }
}
