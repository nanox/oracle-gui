/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: ConnectionDialog.java
 *	Type	: com.gs.oracle.dlg.ConnectionDialog.java
 *	Date	: Jul 30, 2009	9:48:50 AM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.dlg;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

/**
 * @author Green Moon
 * 
 */
public class ConnectionDialog extends JDialog {

	public ConnectionDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
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
		jComboBox1 = new JComboBox();
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
		parentPanel.add(jLabel1, gridBagConstraints);

		newConnectionButton.setText("New ...");
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

		jComboBox1.setModel(new DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		jComboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		parentPanel.add(jComboBox1, gridBagConstraints);

		editConnNameButton.setText("...");
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
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(jLabel3, gridBagConstraints);

		hostAddrTextField.setText("jTextField1");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(hostAddrTextField, gridBagConstraints);

		jLabel4.setText("User Name");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(jLabel4, gridBagConstraints);

		userNameTextField.setText("jTextField2");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(userNameTextField, gridBagConstraints);

		jLabel5.setText("Password");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(jLabel5, gridBagConstraints);

		pwdPasswordField.setText("jPasswordField1");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
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
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(savePwdCheckBox, gridBagConstraints);

		jLabel7.setText("Port");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(jLabel7, gridBagConstraints);

		portTextField.setText("jTextField3");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(portTextField, gridBagConstraints);

		jLabel8.setText("Default Character Set");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(jLabel8, gridBagConstraints);

		charSetComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		charSetComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				charSetComboBoxActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(charSetComboBox, gridBagConstraints);

		sidTextField.setText("jTextField4");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
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
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
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
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(serviceNameRadioButton, gridBagConstraints);

		serviceNameTextField.setText("jTextField1");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		httpHostPanel.add(serviceNameTextField, gridBagConstraints);

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

		connectButton.setText("jButton5");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				connectButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		parentPanel.add(connectButton, gridBagConstraints);

		cancelButton.setText("jButton6");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		parentPanel.add(cancelButton, gridBagConstraints);

		testConnectionButton.setText("jButton7");
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
		parentPanel.add(testConnectionButton, gridBagConstraints);

		getContentPane().add(parentPanel, BorderLayout.CENTER);

		pack();
	}

	private void savePwdCheckBoxActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void testConnectionButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void connectButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void serviceNameRadioButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void SIDRadioButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void charSetComboBoxActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void newConnectionButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void saveConnectionButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void deleteConnectionButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox1ActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void editConnNameButtonActionPerformed(
			ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ConnectionDialog dialog = new ConnectionDialog(
						new JFrame(), true);
				dialog.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
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
	private JComboBox jComboBox1;
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

}
