/**
 * 
 */
package com.gs.oracle.comps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Database;
import com.gs.oracle.model.OracleDataType;
import com.gs.oracle.model.Schema;
import com.gs.oracle.model.Table;

/**
 * @author Green Moon
 * 
 */
public class DatabaseDirectoryTree extends JTree implements OracleGuiConstants{
	public static final ImageIcon ICON_ROOT_DATABASES = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH
					+ "DB_dev_perspective.gif"));
	public static final ImageIcon ICON_ROOT_DATABASE = new ImageIcon(
			DatabaseDirectoryTree.class
					.getResource(IMAGE_PATH + "Database.png"));
	public static final ImageIcon ICON_SCHEMA = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "schema.gif"));
	public static final ImageIcon ICON_TABLE = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "table.gif"));
	public static final ImageIcon ICON_COLUMN = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "columns.gif"));

	public static final ImageIcon ICON_PK_COLUMN = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH
					+ "PrimaryKeyColumn.gif"));
	public static final ImageIcon ICON_FK_COLUMN = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "ForeignKeyColumn.gif"));
	public static final ImageIcon ICON_FOLDER_TABLE = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "Folder_table.png"));
	public static final ImageIcon ICON_TABLE_DELETED = new ImageIcon(
			DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "deleted_table.gif"));

	private String rootNodeName;
	private Database database;

	public DatabaseDirectoryTree() {
	}

	public DatabaseDirectoryTree(Database db) {
		this.database = db;
		initComponents();
	}

	public DefaultTreeModel getTreeModel() {
		return defaultTreeModel;
	}

	private void initComponents() {
		topNode = populateDatabaseTree(database);
		defaultTreeModel = new DefaultTreeModel(topNode);
		setModel(defaultTreeModel);
		ToolTipManager.sharedInstance().registerComponent(this);
		putClientProperty("JTree.lineStyle", "Angled");
		TreeCellRenderer renderer = new IconCellRenderer();
		setCellRenderer(renderer);
		// addTreeExpansionListener(new DirExpansionListener());
		// addTreeSelectionListener(new DirSelectionListener());

		getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		setShowsRootHandles(true);
		setEditable(false);

	}
	
	public void reload(Database db){
		this.database = db;
		removeAll();
		initComponents();
	}

	public DefaultMutableTreeNode populateDatabaseTree(Database database) {
		DefaultMutableTreeNode dbNode = new DefaultMutableTreeNode(
				new IconData(ICON_ROOT_DATABASE, null, new DatabaseTreeNode(
						database)));
		for (Schema schema : database.getSchemaList()) {
			DefaultMutableTreeNode sNode = new DefaultMutableTreeNode(
					new IconData(ICON_SCHEMA, null, new SchemaNode(schema)));
			// add all the tables in the table folder.
			DefaultMutableTreeNode tableFolderNode = new DefaultMutableTreeNode(
					new IconData(ICON_FOLDER_TABLE, null, new FolderNode<Table>("Tables", schema.getTableList())));
			if(null != schema.getTableList() && schema.getTableList().size() > 0){
				for (Table t : schema.getTableList()) {
					DefaultMutableTreeNode tNode = null;
					if(t.isDeleted()){
						tNode = new DefaultMutableTreeNode(
								new IconData(ICON_TABLE_DELETED, null, new TableNode(t)));
					}else{
						tNode = new DefaultMutableTreeNode(
								new IconData(ICON_TABLE, null, new TableNode(t)));
					}
					for (Column c : t.getColumnlist()) {
						DefaultMutableTreeNode cNode = new DefaultMutableTreeNode(
								new IconData(ICON_COLUMN, null, new ColumnNode(c)));
						if (null != c.getPrimaryKey() && Boolean.TRUE.equals(c.getPrimaryKey())) {
							cNode = new DefaultMutableTreeNode(new IconData(
									ICON_PK_COLUMN, null, new ColumnNode(c)));
						}else if (null != c.getForeignKey() && Boolean.TRUE.equals(c.getForeignKey())) {
							cNode = new DefaultMutableTreeNode(new IconData(
									ICON_FK_COLUMN, null, new ColumnNode(c)));
						} 
						tNode.add(cNode);
					}
					tableFolderNode.add(tNode);
				}
			}
			sNode.add(tableFolderNode);
			dbNode.add(sNode);
		}
		return dbNode;
	}

	public String getToolTipText(MouseEvent ev) {
		if (ev == null)
			return null;
		TreePath path = getPathForLocation(ev.getX(), ev.getY());
		if (path != null) {
			DatabaseNode<?> dbNode = getDatabaseNode(getTreeNode(path));
			if (dbNode == null)
				return null;
			return dbNode.toString();
		}
		return null;
	}

	public DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	public <T> DatabaseNode<T> getDatabaseNode(DefaultMutableTreeNode node) {
		if (node == null)
			return null;
		Object obj = node.getUserObject();
		if (obj instanceof IconData)
			obj = ((IconData) obj).getObject();
		if (obj instanceof DatabaseNode){
			return (DatabaseNode<T>) obj;
		}
		else
			return null;
	}

	public JTextField getDisplayTextField() {
		return displayTextField;
	}

	public void setDisplayTextField(JTextField displayTextField) {
		this.displayTextField = displayTextField;
	}

	private DefaultMutableTreeNode topNode;
	private JTextField displayTextField;
	protected DefaultTreeModel defaultTreeModel;

	protected JPopupMenu m_popup;
	protected Action treeAction;
	protected TreePath clickedPath;


	private static String selectedPath = "";

}

class IconCellRenderer extends JLabel implements TreeCellRenderer {
	protected Color m_textSelectionColor;
	protected Color m_textNonSelectionColor;
	protected Color m_bkSelectionColor;
	protected Color m_bkNonSelectionColor;
	protected Color m_borderSelectionColor;

	protected boolean m_selected;

	public IconCellRenderer() {
		super();
		m_textSelectionColor = UIManager.getColor("Tree.selectionForeground");
		m_textNonSelectionColor = UIManager.getColor("Tree.textForeground");
		m_bkSelectionColor = UIManager.getColor("Tree.selectionBackground");
		m_bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
		m_borderSelectionColor = UIManager
				.getColor("Tree.selectionBorderColor");
		setOpaque(false);
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus)

	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Object obj = node.getUserObject();
		setText(obj.toString());

		if (obj instanceof Boolean)
			setText("Retrieving data...");

		if (obj instanceof IconData) {
			IconData idata = (IconData) obj;
			if (expanded)
				setIcon(idata.getExpandedIcon());
			else
				setIcon(idata.getIcon());
		} else
			setIcon(null);

		setFont(tree.getFont());
		setForeground(sel ? m_textSelectionColor : m_textNonSelectionColor);
		setBackground(sel ? m_bkSelectionColor : m_bkNonSelectionColor);
		m_selected = sel;
		return this;
	}

	public void paintComponent(Graphics g) {
		Color bColor = getBackground();
		Icon icon = getIcon();

		g.setColor(bColor);
		int offset = 0;
		if (icon != null && getText() != null)
			offset = (icon.getIconWidth() + getIconTextGap());
		g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

		if (m_selected) {
			g.setColor(m_borderSelectionColor);
			g.drawRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);
		}

		super.paintComponent(g);
	}
}

class IconData {
	protected Icon nodeIcon;
	protected Icon expandedIcon;
	protected Object nodeData;

	public IconData(Icon icon, Object data) {
		nodeIcon = icon;
		expandedIcon = null;
		nodeData = data;
	}

	public IconData(Icon icon, Icon expandedIcon, Object data) {
		nodeIcon = icon;
		this.expandedIcon = expandedIcon;
		nodeData = data;
	}

	public Icon getIcon() {
		return nodeIcon;
	}

	public Icon getExpandedIcon() {
		return expandedIcon != null ? expandedIcon : nodeIcon;
	}

	public Object getObject() {
		return nodeData;
	}

	public String toString() {
		return nodeData.toString();
	}

}

interface DatabaseNode<T> {
	public boolean expand(DefaultMutableTreeNode parent);
}

class DatabaseTreeNode implements DatabaseNode<Database> {

	protected Database database;

	public DatabaseTreeNode(Database database) {
		this.database = database;
	}

	@Override
	public boolean expand(DefaultMutableTreeNode parent) {
		DefaultMutableTreeNode flag = (DefaultMutableTreeNode) parent
				.getFirstChild();
		if (flag == null) // No flag
			return false;
		Object obj = flag.getUserObject();
		if (!(obj instanceof Boolean))
			return false; // Already expanded

		parent.removeAllChildren(); // Remove Flag

		List<Schema> schemas = database.getSchemaList();
		if (schemas == null) {
			return true;
		}

		Vector<SchemaNode> schemaNodeVector = new Vector<SchemaNode>();

		for (int k = 0; k < schemas.size(); k++) {
			Schema s = schemas.get(k);
			SchemaNode schemaNode = new SchemaNode(s);
			boolean isAdded = false;
			for (int i = 0; i < schemaNodeVector.size(); i++) {
				SchemaNode nd = schemaNodeVector.elementAt(i);
				if (schemaNode.compareTo(nd) < 0) {
					schemaNodeVector.insertElementAt(schemaNode, i);
					isAdded = true;
					break;
				}
			}
			if (!isAdded)
				schemaNodeVector.addElement(schemaNode);
		}

		for (int i = 0; i < schemaNodeVector.size(); i++) {
			SchemaNode nd = schemaNodeVector.elementAt(i);
			IconData idata = new IconData(DatabaseDirectoryTree.ICON_SCHEMA,
					DatabaseDirectoryTree.ICON_SCHEMA, nd);

			if (idata == null) {
				continue;
			}
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
			parent.add(node);

			if (nd.hasTables())
				node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}

		return true;
	}

	/**
	 * @return the database
	 */
	public Database getDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return database.toString();
	}

}

class SchemaNode implements DatabaseNode<Schema>, Comparable<SchemaNode> {
	protected Schema schema;

	public SchemaNode(Schema s) {
		this.schema = s;
	}

	public boolean hasTables() {
		if (null != getSchema().getTableList()
				&& getSchema().getTableList().size() > 0)
			return true;
		return false;
	}

	/**
	 * @return the schema
	 */
	public Schema getSchema() {
		return schema;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return schema.toString();
	}

	public boolean expand(DefaultMutableTreeNode parent) {
		DefaultMutableTreeNode flag = (DefaultMutableTreeNode) parent
				.getFirstChild();
		if (flag == null) // No flag
			return false;
		Object obj = flag.getUserObject();
		if (!(obj instanceof Boolean))
			return false; // Already expanded

		parent.removeAllChildren(); // Remove Flag

		List<Table> tables = schema.getTableList();
		if (tables == null) {
			return true;
		}

		Vector<TableNode> tableNodeVector = new Vector<TableNode>();

		for (int k = 0; k < tables.size(); k++) {
			Table t = tables.get(k);
			TableNode tableNode = new TableNode(t);
			boolean isAdded = false;
			for (int i = 0; i < tableNodeVector.size(); i++) {
				TableNode nd = tableNodeVector.elementAt(i);
				if (tableNode.compareTo(nd) < 0) {
					tableNodeVector.insertElementAt(tableNode, i);
					isAdded = true;
					break;
				}
			}
			if (!isAdded)
				tableNodeVector.addElement(tableNode);
		}

		for (int i = 0; i < tableNodeVector.size(); i++) {
			TableNode nd = tableNodeVector.elementAt(i);
			IconData idata = null;
			if(nd.table.isDeleted()){
				idata = new IconData(DatabaseDirectoryTree.ICON_TABLE_DELETED,
						DatabaseDirectoryTree.ICON_TABLE_DELETED, nd);
			}else{
				idata = new IconData(DatabaseDirectoryTree.ICON_TABLE,
						DatabaseDirectoryTree.ICON_TABLE, nd);
			}
			if (idata == null) {
				continue;
			}
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
			parent.add(node);

			if (nd.hasColumns())
				node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}

		return true;
	}

	/**
	 * compares the schema names
	 */
	@Override
	public int compareTo(SchemaNode o) {
		return schema.getModelName().compareTo(o.getSchema().getModelName());
	}
}

class TableNode implements DatabaseNode<Table>, Comparable<TableNode> {
	protected Table table;

	public TableNode(Table table) {
		this.table = table;
	}

	public boolean hasColumns() {
		if (null != getTable().getColumnlist()
				&& getTable().getColumnlist().size() > 0)
			return true;
		return false;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return table.toString();
	}

	public boolean expand(DefaultMutableTreeNode parent) {

		DefaultMutableTreeNode flag = (DefaultMutableTreeNode) parent
				.getFirstChild();
		if (flag == null) // No flag
			return false;
		Object obj = flag.getUserObject();
		if (!(obj instanceof Boolean))
			return false; // Already expanded

		parent.removeAllChildren(); // Remove Flag

		List<Column> columns = table.getColumnlist();
		if (columns == null) {
			return true;
		}

		Vector<ColumnNode> columnNodeVector = new Vector<ColumnNode>();

		for (int k = 0; k < columns.size(); k++) {
			Column c = columns.get(k);
			ColumnNode columnNode = new ColumnNode(c);
			boolean isAdded = false;
			for (int i = 0; i < columnNodeVector.size(); i++) {
				ColumnNode nd = columnNodeVector.elementAt(i);
				if (columnNode.compareTo(nd) < 0) {
					columnNodeVector.insertElementAt(columnNode, i);
					isAdded = true;
					break;
				}
			}
			if (!isAdded)
				columnNodeVector.addElement(columnNode);
		}

		for (int i = 0; i < columnNodeVector.size(); i++) {
			ColumnNode nd = columnNodeVector.elementAt(i);
			IconData idata = null;
			if (nd.getColumn().getPrimaryKey()) {
				idata = new IconData(DatabaseDirectoryTree.ICON_PK_COLUMN,
						DatabaseDirectoryTree.ICON_PK_COLUMN, nd);
			} else if (nd.getColumn().getForeignKey()) {
				idata = new IconData(DatabaseDirectoryTree.ICON_FK_COLUMN,
						DatabaseDirectoryTree.ICON_FK_COLUMN, nd);
			} else {
				idata = new IconData(DatabaseDirectoryTree.ICON_COLUMN,
						DatabaseDirectoryTree.ICON_COLUMN, nd);
			}
			if (idata == null) {
				continue;
			}
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
			parent.add(node);
		}

		return true;
	}

	@Override
	public int compareTo(TableNode o) {
		return this.getTable().getModelName().compareTo(
				o.getTable().getModelName());
	}
}

class ColumnNode implements DatabaseNode<Column>, Comparable<ColumnNode> {
	protected Column column;

	public ColumnNode(Column column) {
		this.column = column;
	}

	/**
	 * @return the column
	 */
	public Column getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(Column column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return column.toString();
	}

	public boolean expand(DefaultMutableTreeNode parent) {
		return true;
	}

	@Override
	public int compareTo(ColumnNode o) {
		return getColumn().getModelName().compareTo(
				o.getColumn().getModelName());
	}
}

class FolderNode<T> implements DatabaseNode<T>, Comparable<FolderNode<T>>{
	protected String name;
	protected List<T> contentList;

	public FolderNode(String name, List<T> contentList) {
		this.name = name;
		this.contentList = contentList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<T> getContentList() {
		return contentList;
	}

	public void setContentList(List<T> contentList) {
		this.contentList = contentList;
	}

	public int compareTo(FolderNode<T> o) {
		return getName().compareTo(o.getName());
	};
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean expand(DefaultMutableTreeNode parent) {
		// TODO Auto-generated method stub
		return false;
	}
}