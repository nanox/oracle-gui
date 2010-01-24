/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author sabuj.das
 *
 */
public class TableRenamePanel extends JPanel {

    public TableRenamePanel() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel1 = new JLabel();
        newTableNameTextField = new JTextField();
        jPanel1 = new JPanel();

        setLayout(new GridBagLayout());

        jLabel1.setText("New Table Name: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(jLabel1, gridBagConstraints);

        newTableNameTextField.setText("NewTableName");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(newTableNameTextField, gridBagConstraints);

        jPanel1.setLayout(new BorderLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }



    private JLabel jLabel1;
    private JPanel jPanel1;
    private JTextField newTableNameTextField;
    
	public JTextField getNewTableNameTextField() {
		return newTableNameTextField;
	}

    

}
