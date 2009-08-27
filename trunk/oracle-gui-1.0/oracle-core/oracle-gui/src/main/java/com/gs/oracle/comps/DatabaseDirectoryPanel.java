/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.command.GuiCommandConstants;
import com.gs.oracle.command.GuiEventHandler;
import com.gs.oracle.model.Table;
import com.gs.oracle.util.MenuBarUtil;

/**
 * @author sabuj.das
 *
 */
public class DatabaseDirectoryPanel extends JPanel implements ActionListener,
		OracleGuiConstants, TreeSelectionListener, MouseListener{

	private DatabaseDirectoryTree databaseDirectoryTree;
	protected TreePath m_clickedPath;
	private JPopupMenu dbDirectoryTreePopup;
	private JMenuItem expandCollaspMenuItem, viewTableDetailsMenuItem;
	
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
		expandCollaspMenuItem = new JMenuItem("Expand");
		viewTableDetailsMenuItem = new JMenuItem("Table Details");
		
		dbDirectoryTreePopup.addPopupMenuListener(new PopupMenuListener(){

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				directoryTreePopupPopupMenuWillBecomeVisible(e);
			}

		});
		dbDirectoryTreePopup.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				directoryTreePopupComponentShown(e);
			}
			
		});
		getDatabaseDirectoryTree().add(dbDirectoryTreePopup);
		
		expandCollaspMenuItem.addActionListener(this);
        getDatabaseDirectoryTree().add(expandCollaspMenuItem);
        getDatabaseDirectoryTree().add(new JSeparator());
        
        viewTableDetailsMenuItem.addActionListener(this);
        viewTableDetailsMenuItem.setIcon(new ImageIcon(getClass()
				.getResource(OracleGuiConstants.IMAGE_PATH
						+ "sampleContents.gif")));
        getDatabaseDirectoryTree().add(viewTableDetailsMenuItem);
		
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
		if(e.getSource().equals(getDatabaseDirectoryTree())){
            TreePath p = getDatabaseDirectoryTree().getSelectionPath();
            if(p==null)
                return;
            DefaultMutableTreeNode node = getDatabaseDirectoryTree().getTreeNode(p);
            if(node == null)
                return;
            DatabaseNode dbNode = getDatabaseDirectoryTree().getDatabaseNode(node);
            if (dbNode == null) {
                return;
            }
            if(MouseEvent.BUTTON1 == e.getButton()){
                if(dbNode instanceof TableNode){
                    if(e.getClickCount() == 2){
                    	Table table = ((TableNode)dbNode).getTable();
            			if(table != null){
            				String tableName = table.getModelName();
            				openTableDetails(tableName);
            			}
                    }
                }
            }
        }
	}

	private void openTableDetails(String tableName) {
		GuiEventHandler handler = new GuiEventHandler();
		handler.setData(tableName);
		
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
	
	private void directoryTreePopupPopupMenuWillBecomeVisible(PopupMenuEvent evt) {                                                              
        if(evt.getSource().equals(getDatabaseDirectoryTree())){
            TreePath p = getDatabaseDirectoryTree().getSelectionPath();
            if(p==null)
                return;
            DefaultMutableTreeNode node = getDatabaseDirectoryTree().getTreeNode(p);
            if(node == null)
                return;
            /*FileNode fnode = directoryTree.getFileNode(node);
            if (fnode == null) {
                return;
            }*/
            
        }
    }                                                             

    private void directoryTreePopupComponentShown(ComponentEvent evt) {                                                  
        if(evt.getSource().equals(getDatabaseDirectoryTree())){
            TreePath p = getDatabaseDirectoryTree().getSelectionPath();
            if(p==null)
                return;
            DefaultMutableTreeNode node = getDatabaseDirectoryTree().getTreeNode(p);
            if(node == null)
                return;
            /*FileNode fnode = directoryTree.getFileNode(node);
            if (fnode == null) {
                return;
            }*/

        }
    }
    
    
    
    
    class PopupTrigger extends MouseAdapter {

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int x = e.getX();
                int y = e.getY();
                TreePath path = getDatabaseDirectoryTree().getPathForLocation(x, y);
                if (path != null) {
                    if (getDatabaseDirectoryTree().isExpanded(path)) {
                        //m_action.putValue(Action.NAME, "Collapse");
                    } else {
                        //m_action.putValue(Action.NAME, "Expand");
                    }
                    //m_popup.show(m_tree, x, y);
                    //m_clickedPath = path;
                }
            }
        }
    }

}