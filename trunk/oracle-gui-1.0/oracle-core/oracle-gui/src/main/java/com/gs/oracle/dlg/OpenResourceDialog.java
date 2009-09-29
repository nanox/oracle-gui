/*
 * OpenResourceDialog.java
 *
 * Created on Sep 29, 2009, 1:50:54 PM
 */
package com.gs.oracle.dlg;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.gs.oracle.iframe.DatabaseViewerInternalFrame;

/**
 *
 * @author sabuj.das
 */
public class OpenResourceDialog extends JDialog {

	private List<String> schemaNameList;
	private JFrame parentFrame;
	private DatabaseViewerInternalFrame targetDbViewerIFrame;
    
    public OpenResourceDialog(JFrame parent, List<String> schemaNameList,
    		DatabaseViewerInternalFrame targetDbViewerIFrame) {
        super(parent, true);
        this.schemaNameList = schemaNameList;
        this.parentFrame = parent;
        this.targetDbViewerIFrame = targetDbViewerIFrame;
        initComponents();
    }

    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        mainPanel = new JPanel();
        jLabel1 = new JLabel();
        schemaNameComboBox = new JComboBox();
        jLabel2 = new JLabel();
        selectItemTextField = new JTextField();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        matchingItemsList = new JList();
        resourcePathTextField = new JTextField();
        cancelButton = new JButton();
        openButton = new JButton();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Resource");

        mainPanel.setLayout(new GridBagLayout());

        jLabel1.setText("SCHEMA : ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jLabel1, gridBagConstraints);

        schemaNameComboBox.setModel(new DefaultComboBoxModel(getSchemaNameList().toArray()));
        schemaNameComboBox.addActionListener(formListener);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(schemaNameComboBox, gridBagConstraints);

        jLabel2.setText("Select an Item : ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jLabel2, gridBagConstraints);

        selectItemTextField.addKeyListener(formListener);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(selectItemTextField, gridBagConstraints);

        jLabel3.setText("Matching Items : ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jLabel3, gridBagConstraints);

        matchingItemsList.setModel(new AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        matchingItemsList.addListSelectionListener(formListener);
        jScrollPane1.setViewportView(matchingItemsList);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jScrollPane1, gridBagConstraints);

        resourcePathTextField.setEditable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(resourcePathTextField, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(formListener);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(cancelButton, gridBagConstraints);

        openButton.setText("Open");
        openButton.addActionListener(formListener);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(openButton, gridBagConstraints);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements ActionListener, KeyListener, ListSelectionListener {
        FormListener() {}
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == schemaNameComboBox) {
                OpenResourceDialog.this.schemaNameComboBoxActionPerformed(evt);
            }
            else if (evt.getSource() == cancelButton) {
                OpenResourceDialog.this.cancelButtonActionPerformed(evt);
            }
            else if (evt.getSource() == openButton) {
                OpenResourceDialog.this.openButtonActionPerformed(evt);
            }
        }

        public void keyPressed(KeyEvent evt) {
        }

        public void keyReleased(KeyEvent evt) {
        }

        public void keyTyped(KeyEvent evt) {
            if (evt.getSource() == selectItemTextField) {
                OpenResourceDialog.this.selectItemTextFieldKeyTyped(evt);
            }
        }

        public void valueChanged(ListSelectionEvent evt) {
            if (evt.getSource() == matchingItemsList) {
                OpenResourceDialog.this.matchingItemsListValueChanged(evt);
            }
        }
    }

    private void schemaNameComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void selectItemTextFieldKeyTyped(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void matchingItemsListValueChanged(ListSelectionEvent evt) {
        // TODO add your handling code here:
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void openButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration
    private JButton cancelButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JPanel mainPanel;
    private JList matchingItemsList;
    private JButton openButton;
    private JTextField resourcePathTextField;
    private JComboBox schemaNameComboBox;
    private JTextField selectItemTextField;
    // End of variables declaration

	/**
	 * @return the schemaNameList
	 */
	public List<String> getSchemaNameList() {
		return schemaNameList;
	}


	/**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}


	/**
	 * @return the targetDbViewerIFrame
	 */
	public DatabaseViewerInternalFrame getTargetDbViewerIFrame() {
		return targetDbViewerIFrame;
	}


	/**
	 * @param schemaNameList the schemaNameList to set
	 */
	public void setSchemaNameList(List<String> schemaNameList) {
		this.schemaNameList = schemaNameList;
	}


	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}


	/**
	 * @param targetDbViewerIFrame the targetDbViewerIFrame to set
	 */
	public void setTargetDbViewerIFrame(
			DatabaseViewerInternalFrame targetDbViewerIFrame) {
		this.targetDbViewerIFrame = targetDbViewerIFrame;
	}

    
}
