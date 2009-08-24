/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.gs.oracle.OracleGuiConstants;

/**
 * @author sabuj.das
 *
 */
public class TableDetailsPanel extends JPanel implements ActionListener,
		OracleGuiConstants {

	private JTabbedPane tableDetailsTabbedPane;
	
	
	public TableDetailsPanel() {
		initComponents();
	}
	
	private void initComponents(){
		tableDetailsTabbedPane = new JTabbedPane();
		tableDetailsTabbedPane.setFocusable(false);
		tableDetailsTabbedPane.addTab("Data", new JPanel());
		tableDetailsTabbedPane.addTab("Columns", new JPanel());
		tableDetailsTabbedPane.addTab("Constraints", new JPanel());
		tableDetailsTabbedPane.addTab("Dependencies", new JPanel());
		tableDetailsTabbedPane.addTab("Indexes", new JPanel());
		tableDetailsTabbedPane.addTab("SQL", new JPanel());
		
		setLayout(new BorderLayout());
		
		add(tableDetailsTabbedPane, BorderLayout.CENTER);
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public JTabbedPane getTableDetailsTabbedPane() {
		return tableDetailsTabbedPane;
	}

}
