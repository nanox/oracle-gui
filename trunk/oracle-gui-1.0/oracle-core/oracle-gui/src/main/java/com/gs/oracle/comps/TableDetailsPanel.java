/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.util.MenuBarUtil;

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

	private void initComponents() {
		tableDetailsTabbedPane = new JTabbedPane();
		tableDetailsTabbedPane.setFocusable(false);
		tableDetailsTabbedPane.addTab("Data", new ImageIcon(
				TableDetailsPanel.class
						.getResource(OracleGuiConstants.IMAGE_PATH
								+ "table_data.gif")), new JPanel());
		tableDetailsTabbedPane.addTab("Columns",new ImageIcon(
				TableDetailsPanel.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "columngroup.gif")), new JPanel());
		tableDetailsTabbedPane.addTab("Constraints",new ImageIcon(
				TableDetailsPanel.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "constraint.gif")), new JPanel());
		tableDetailsTabbedPane.addTab("Dependencies",new ImageIcon(
				TableDetailsPanel.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "dependency.gif")), new JPanel());
		tableDetailsTabbedPane.addTab("Indexes",new ImageIcon(
				TableDetailsPanel.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "index.gif")), new JPanel());
		tableDetailsTabbedPane.addTab("DDL", new ImageIcon(
				TableDetailsPanel.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "generate_ddl.gif")),new JPanel());

		setLayout(new BorderLayout());

		add(tableDetailsTabbedPane, BorderLayout.CENTER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public JTabbedPane getTableDetailsTabbedPane() {
		return tableDetailsTabbedPane;
	}

}
