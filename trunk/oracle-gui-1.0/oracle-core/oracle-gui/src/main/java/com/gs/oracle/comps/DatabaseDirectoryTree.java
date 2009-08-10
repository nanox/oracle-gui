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

/**
 * @author Green Moon
 *
 */
public class DatabaseDirectoryTree extends JTree implements OracleGuiConstants{

	
	private final ImageIcon ICON_COMPUTER = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));
	private final ImageIcon ICON_DISK = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));
	private final ImageIcon ICON_FOLDER_CLOSED = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));
	private final ImageIcon ICON_FOLDER_OPENED = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));
	private final ImageIcon ICON_EXPAND = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));
	private final ImageIcon ICON_COLLASPE = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));
    private final ImageIcon NOTEPAD_ICON = new ImageIcon(
            DatabaseDirectoryTree.class.getResource(IMAGE_PATH + "DB_dev_perspective.gif"));

    
    private String rootNodeName;
    
    public DatabaseDirectoryTree() {
        this("DB");
	}
    
	public DatabaseDirectoryTree(String rootNodeName) {
        this.rootNodeName = rootNodeName;
		initComponents();
	}


    public DefaultTreeModel getTreeModel() {
        return defaultTreeModel;
    }


	private void initComponents() {

		topNode = new DefaultMutableTreeNode(new IconData(ICON_COMPUTER, null,
				rootNodeName));
		DefaultMutableTreeNode node;
		File[] roots = File.listRoots();
		for (int k = 0; k < roots.length; k++) {
			node = new DefaultMutableTreeNode(new IconData(ICON_DISK, null,
					new FileNode(roots[k])));
			topNode.add(node);
			node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}

		defaultTreeModel = new DefaultTreeModel(topNode);
		setModel(defaultTreeModel);
		ToolTipManager.sharedInstance().registerComponent(this);
		putClientProperty("JTree.lineStyle", "Angled");
		TreeCellRenderer renderer = new IconCellRenderer();
		setCellRenderer(renderer);
		//addTreeExpansionListener(new DirExpansionListener());
		//addTreeSelectionListener(new DirSelectionListener());
        
		getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		setShowsRootHandles(true);
		setEditable(false);

	}

	public String getToolTipText(MouseEvent ev) {
		if (ev == null)
			return null;
		TreePath path = getPathForLocation(ev.getX(), ev.getY());
		if (path != null) {
			FileNode fnode = getFileNode(getTreeNode(path));
			if (fnode == null)
				return null;
			File f = fnode.getFile();
			return (f == null ? null : f.getPath());
		}
		return null;
	}

	public DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	public FileNode getFileNode(DefaultMutableTreeNode node) {
		if (node == null)
			return null;
		Object obj = node.getUserObject();
		if (obj instanceof IconData)
			obj = ((IconData) obj).getObject();
		if (obj instanceof FileNode)
			return (FileNode) obj;
		else
			return null;
	}

	public ImageIcon getICON_COMPUTER() {
		return ICON_COMPUTER;
	}

	public ImageIcon getICON_DISK() {
		return ICON_DISK;
	}

	public ImageIcon getICON_FOLDER_CLOSED() {
		return ICON_FOLDER_CLOSED;
	}

	public ImageIcon getICON_FOLDER_OPENED() {
		return ICON_FOLDER_OPENED;
	}

	public ImageIcon getICON_EXPAND() {
		return ICON_EXPAND;
	}

	public ImageIcon getICON_COLLASPE() {
		return ICON_COLLASPE;
	}

    public ImageIcon getNOTEPAD_ICON() {
        return NOTEPAD_ICON;
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

    
/*
    
	class DirExpansionListener implements TreeExpansionListener {
		public void treeExpanded(TreeExpansionEvent event) {
			final DefaultMutableTreeNode node = getTreeNode(event.getPath());
			final FileNode fnode = getFileNode(node);
			if (fnode != null && fnode.expand(node)) {
				defaultTreeModel.reload(node);
			}
		}

		public void treeCollapsed(TreeExpansionEvent event) {
		}
	}

	class DirSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent event) {
			DefaultMutableTreeNode node = getTreeNode(event.getPath());
			FileNode fnode = getFileNode(node);
			if (fnode != null) {
				//displayTextField.setText(fnode.getFile().getAbsolutePath());
				
			} else {
				//displayTextField.setText("");
				
			}
		}
	}
*/
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
	protected Icon m_icon;
	protected Icon m_expandedIcon;
	protected Object m_data;
    protected boolean isDirectory = false;
    protected boolean isFile = false;

	public IconData(Icon icon, Object data) {
		m_icon = icon;
		m_expandedIcon = null;
		m_data = data;
	}

	public IconData(Icon icon, Icon expandedIcon, Object data) {
		m_icon = icon;
		m_expandedIcon = expandedIcon;
		m_data = data;
	}

	public Icon getIcon() {
		return m_icon;
	}

	public Icon getExpandedIcon() {
		return m_expandedIcon != null ? m_expandedIcon : m_icon;
	}

	public Object getObject() {
		return m_data;
	}

	public String toString() {
		return m_data.toString();
	}

    public boolean isIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public boolean isIsFile() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }
    
}
class FileNode {
	protected File m_file;

	public FileNode(File file) {
		m_file = file;
	}

	public File getFile() {
		return m_file;
	}

	public String toString() {
		return m_file.getName().length() > 0 ? m_file.getName() : m_file
				.getPath();
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

		File[] files = listFiles();
		if (files == null)
			return true;

		Vector v = new Vector();

		for (int k = 0; k < files.length; k++) {
			File f = files[k];
			if (!(f.isDirectory())){
				
            }
			FileNode newNode = new FileNode(f);

			boolean isAdded = false;
			for (int i = 0; i < v.size(); i++) {
				FileNode nd = (FileNode) v.elementAt(i);
				if (newNode.compareTo(nd) < 0) {
					v.insertElementAt(newNode, i);
					isAdded = true;
					break;
				}
			}
			if (!isAdded)
				v.addElement(newNode);
		}

		for (int i = 0; i < v.size(); i++) {
			FileNode nd = (FileNode) v.elementAt(i);
            IconData idata = null;
            if(nd.m_file.isDirectory()){
                idata = new IconData(new DatabaseDirectoryTree()
					.getICON_FOLDER_CLOSED(), new DatabaseDirectoryTree()
					.getICON_FOLDER_OPENED(), nd);
            }else if(nd.m_file.isFile()){
                idata = new IconData(new DatabaseDirectoryTree()
					.getNOTEPAD_ICON(), new DatabaseDirectoryTree()
					.getNOTEPAD_ICON(), nd);
            }
            if(idata == null){
                continue;
            }
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
			parent.add(node);

			if (nd.hasSubDirs())
				node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}

		return true;
	}

	public boolean hasSubDirs() {
		File[] files = listFiles();
		if (files == null)
			return false;
		for (int k = 0; k < files.length; k++) {
			if (files[k].isDirectory())
				return true;
		}
		return false;
	}

	public int compareTo(FileNode toCompare) {
		return m_file.getName().compareToIgnoreCase(toCompare.m_file.getName());
	}

	protected File[] listFiles() {
		if (!m_file.isDirectory())
			return null;
		try {
			return m_file.listFiles();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error reading directory "
					+ m_file.getAbsolutePath(), "Warning",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
}