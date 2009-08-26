/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.model.Table;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class DatabaseDirectoryPanel extends JPanel implements ActionListener,
		OracleGuiConstants, TreeSelectionListener, MouseListener{

	private DatabaseDirectoryTree databaseDirectoryTree;
	protected JPopupMenu m_popup;
	protected Action m_action;
	protected TreePath m_clickedPath;
	private JPopupMenu dbDirectoryTreePopup;
	
	public DatabaseDirectoryPanel(DatabaseDirectoryTree tree) {
		if(tree == null)
			throw new IllegalArgumentException("DatabaseDirectoryTree cannot be NULL");
		this.databaseDirectoryTree = tree;
		this.databaseDirectoryTree.addTreeSelectionListener(this);
		initComponents();
	}
	
	private void initComponents(){
		refreshTreeButton = new JButton();
		databaseDirectoryToolBar = new JToolBar();
		dbDirectoryTreePopup = new JPopupMenu();
		
		m_popup = new JPopupMenu();
        m_action = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (m_clickedPath == null) {
                    return;
                }
                if (getDatabaseDirectoryTree().isExpanded(m_clickedPath)) {
                	getDatabaseDirectoryTree().collapsePath(m_clickedPath);
                } else {
                	getDatabaseDirectoryTree().expandPath(m_clickedPath);
                }
            }
        };
        
        m_popup.add(m_action);
        
        getDatabaseDirectoryTree().add(dbDirectoryTreePopup);
		
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

	@Override
	public void valueChanged(TreeSelectionEvent event) {
		DefaultMutableTreeNode node = getDatabaseDirectoryTree().getTreeNode(event.getPath());
		DatabaseNode<Table> tableNode =  getDatabaseDirectoryTree().getDatabaseNode(node);
		if(tableNode == null)
			return;
		if(tableNode instanceof TableNode){
			Table t = ((TableNode)tableNode).getTable();
			if(t != null){
				String tableName = t.getModelName();
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
