/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.gs.oracle.OracleGuiConstants;

/**
 * @author sabuj.das
 *
 */
public class ColumnDetailsPanel extends JPanel implements ActionListener,
		OracleGuiConstants {

	private JButton refreshButton, addColumnButton, editColumnButton, dropColumnButton;
	private JTable columDetailsTable;
	
	public ColumnDetailsPanel() {
		initComponents();
	}
	
	private void initComponents() {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
