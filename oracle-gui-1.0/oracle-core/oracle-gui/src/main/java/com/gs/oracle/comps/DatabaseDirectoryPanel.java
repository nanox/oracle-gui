/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class DatabaseDirectoryPanel extends JPanel implements ActionListener,
		OracleGuiConstants {

	private DatabaseDirectoryTree databaseDirectoryTree;
	
	public DatabaseDirectoryPanel(DatabaseDirectoryTree tree) {
		if(tree == null)
			throw new IllegalArgumentException("DatabaseDirectoryTree cannot be NULL");
		this.databaseDirectoryTree = tree;
		initComponents();
	}
	
	private void initComponents(){
		refreshTreeButton = new JButton();
		databaseDirectoryToolBar = new JToolBar();
		databaseDirectoryToolBar.setFloatable(false);
		setLayout(new GridBagLayout());
		Icon image = null;
		GridBagConstraints gridBagConstraints = null;
		
		refreshTreeButton.setFocusable(false);
		image = new ImageIcon(MenuBarUtil.class
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "reload_green.png"));
		refreshTreeButton.setIcon(image);
		refreshTreeButton.addActionListener(this);
		databaseDirectoryToolBar.add(refreshTreeButton);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(databaseDirectoryToolBar, gridBagConstraints);
		
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(getDatabaseDirectoryTree());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(jScrollPane1, gridBagConstraints);
	}
	
	private JButton refreshTreeButton;
	private JToolBar databaseDirectoryToolBar;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}



	public DatabaseDirectoryTree getDatabaseDirectoryTree() {
		return databaseDirectoryTree;
	}

}
