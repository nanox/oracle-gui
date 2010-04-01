/**
 * 
 */
package com.gs.dbex.application.comps;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Table;

/**
 * @author sabuj.das
 * 
 */
public class TableColumnTree extends JTree implements OracleGuiConstants {

	public static final ImageIcon ICON_ALL_COLUMN = new ImageIcon(
			TableColumnTree.class.getResource(IMAGE_PATH + "columngroup.gif"));
	
	public static final ImageIcon ICON_COLUMN = new ImageIcon(
			TableColumnTree.class.getResource(IMAGE_PATH + "columns.gif"));
	public static final String ALL_COLUMN_TEXT = "All Columns";

	private DefaultMutableTreeNode topNode;
	protected DefaultTreeModel defaultTreeModel;
	
	private Table databaseTable;


	public TableColumnTree(Table databaseTable) {
		this.databaseTable = databaseTable;
		init();
		
		
	}
	
	private void init(){
		topNode = populateTableColumnTree(databaseTable);
		defaultTreeModel = new DefaultTreeModel(topNode);
		setModel(defaultTreeModel);
		ToolTipManager.sharedInstance().registerComponent(this);
		putClientProperty("JTree.lineStyle", "Angled");
		TreeCellRenderer renderer = new IconCellRenderer();
		setCellRenderer(renderer);
	}

	private DefaultMutableTreeNode populateTableColumnTree(Table table) {
		DefaultMutableTreeNode allColumnsNode = new DefaultMutableTreeNode(
				new IconData(ICON_ALL_COLUMN, null, ALL_COLUMN_TEXT));
		for (Column c : table.getColumnlist()) {
			DefaultMutableTreeNode cNode = new DefaultMutableTreeNode(
					new IconData(ICON_COLUMN, null, c.getModelName()));
			allColumnsNode.add(cNode);
		}
		
		return allColumnsNode;
	}
}
