/**
 * 
 */
package com.gs.oracle.dlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.gs.oracle.comps.ColumnNameListModel;
import com.gs.oracle.comps.ExtensionFileFilter;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.model.Column;
import com.gs.oracle.model.Table;
import com.gs.oracle.util.WindowUtil;
import com.gs.oracle.vo.TableDataExportTypeEnum;

/**
 * @author sabuj.das
 *
 */
public class TableDataExportDialog  extends JDialog {
	
	private Table table;
	private TableDataExportTypeEnum exportTypeEnum;
	private JFrame parentFrame;
	private ConnectionProperties connectionProperties;
	private ColumnNameListModel allColumnNameListModel;
	private ColumnNameListModel selectedColumnNameListModel;
    
    public TableDataExportDialog(JFrame parentFrame, Table table,
			TableDataExportTypeEnum exportTypeEnum,
			ConnectionProperties connectionProperties) {
    	super(parentFrame, true);
		this.parentFrame = parentFrame;
		this.table = table;
		this.exportTypeEnum = exportTypeEnum;
		this.connectionProperties = connectionProperties;
		initComponents();
		allColumnNameListModel = new ColumnNameListModel(new ArrayList<Column>());
		if(this.table.getColumnlist() != null){
			selectedColumnNameListModel = new ColumnNameListModel(this.table.getColumnlist());
		}
		allColumnList.setModel(allColumnNameListModel);
		selectedColumnList.setModel(selectedColumnNameListModel);
		setMinimumSize(new Dimension(450, 350));
        setPreferredSize(getMinimumSize());
        setSize(getPreferredSize());
        getRootPane().setDefaultButton(exportButton);
		WindowUtil.bringCenterTo(this, parentFrame);
	}
    
    
	private void initComponents() {
        GridBagConstraints gridBagConstraints;

        mainPanel = new JPanel();
        schemaNameLabel = new JLabel();
        schemaNameTextField = new JTextField();
        tableNameLabel = new JLabel();
        tableNameTextField = new JTextField();
        exportTabbedPane = new JTabbedPane();
        exportFileFormatPanel = new JPanel();
        formatLabel = new JLabel();
        formatNameLabel = new JLabel();
        outputLabel = new JLabel();
        outputFileTextField = new JTextField();
        browseButton = new JButton();
        columnSelectionPanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTree1 = new JTree();
        dataFilterPanel = new JPanel();
        jLabel1 = new JLabel();
        whereClauseTextField = new JTextField();
        filterButton = new JButton();
        jLabel2 = new JLabel();
        jScrollPane2 = new JScrollPane();
        sampleDataTable = new JTable();
        cancelButton = new JButton();
        exportButton = new JButton();
        
        allColumnList = new JList();
        selectedColumnList = new JList();
        removeAllButton = new JButton("<<");
        removeSelectedButton = new JButton("<");
        addSelectedButton = new JButton(">");
        addAllButton = new JButton(">>");
        moveUpButton = new JButton("Up");
        moveDownButton = new JButton("Down");

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Export Data - " + getTable().getModelName());
        getContentPane().setLayout(new GridBagLayout());

        cancelButton.addActionListener(formListener);
        exportButton.addActionListener(formListener);
        browseButton.addActionListener(formListener);
        filterButton.addActionListener(formListener);
        whereClauseTextField.addKeyListener(formListener);
        allColumnList.addMouseListener(formListener);
        allColumnList.addListSelectionListener(formListener);
        selectedColumnList.addMouseListener(formListener);
        selectedColumnList.addListSelectionListener(formListener);
        removeAllButton.addActionListener(formListener);
        removeSelectedButton.addActionListener(formListener);
        addSelectedButton.addActionListener(formListener);
        addAllButton.addActionListener(formListener);
        moveUpButton.addActionListener(formListener);
        moveDownButton.addActionListener(formListener);
        
        
        mainPanel.setLayout(new GridBagLayout());

        schemaNameLabel.setText("Schema : ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(4, 2, 2, 2);
        mainPanel.add(schemaNameLabel, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(4, 2, 2, 2);
        schemaNameTextField.setBackground(new Color(255, 255, 255));
        schemaNameTextField.setEditable(false);
        schemaNameTextField.setForeground(new Color(0, 0, 204));
        schemaNameTextField.setText(getTable().getSchemaName()); 
        mainPanel.add(schemaNameTextField, gridBagConstraints);

        tableNameLabel.setText("Table : ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(tableNameLabel, gridBagConstraints);

        tableNameTextField.setBackground(new Color(255, 255, 255));
        tableNameTextField.setEditable(false);
        tableNameTextField.setForeground(new Color(0, 0, 204));
        tableNameTextField.setText(getTable().getModelName()); 
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(tableNameTextField, gridBagConstraints);

        exportFileFormatPanel.setLayout(new GridBagLayout());

        formatLabel.setText("Export Format ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        exportFileFormatPanel.add(formatLabel, gridBagConstraints);

        formatNameLabel.setText("" + getExportTypeEnum().getDescription());
        formatNameLabel.setForeground(new Color(0, 0, 204));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        exportFileFormatPanel.add(formatNameLabel, gridBagConstraints);

        outputLabel.setText("Output File ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        exportFileFormatPanel.add(outputLabel, gridBagConstraints);

        outputFileTextField.setText("");
        outputFileTextField.setForeground(new Color(0, 0, 204));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        exportFileFormatPanel.add(outputFileTextField, gridBagConstraints);

        browseButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(5, 0, 5, 2);
        exportFileFormatPanel.add(browseButton, gridBagConstraints);

        exportTabbedPane.addTab("Format", exportFileFormatPanel);

        columnSelectionPanel.setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(new JLabel("All Columns"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(new JLabel("Selected Columns"), gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        allColumnListScrollPane.setViewportView(allColumnList);
        columnSelectionPanel.add(allColumnListScrollPane, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(addAllButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(addSelectedButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(removeSelectedButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(removeAllButton, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        selectedColumnListScrollPane.setViewportView(selectedColumnList);
        columnSelectionPanel.add(selectedColumnListScrollPane, gridBagConstraints);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(moveUpButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        columnSelectionPanel.add(moveDownButton, gridBagConstraints);


        exportTabbedPane.addTab("Columns", columnSelectionPanel);

        dataFilterPanel.setLayout(new GridBagLayout());

        jLabel1.setText("WHERE ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        dataFilterPanel.add(jLabel1, gridBagConstraints);

        whereClauseTextField.setText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        dataFilterPanel.add(whereClauseTextField, gridBagConstraints);

        filterButton.setText("Filter");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        dataFilterPanel.add(filterButton, gridBagConstraints);

        jLabel2.setText("Sample Data ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        dataFilterPanel.add(jLabel2, gridBagConstraints);

        sampleDataTable.setModel(new DefaultTableModel());
        jScrollPane2.setViewportView(sampleDataTable);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        dataFilterPanel.add(jScrollPane2, gridBagConstraints);

        exportTabbedPane.addTab("Where", dataFilterPanel);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(4, 2, 2, 2);
        mainPanel.add(exportTabbedPane, gridBagConstraints);

        cancelButton.setText("Cancel");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        mainPanel.add(cancelButton, gridBagConstraints);

        exportButton.setText("Export");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(5, 2, 5, 2);
        mainPanel.add(exportButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);

        pack();
    }


    /**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}


	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}


	private class FormListener 
		implements 
			ActionListener, FocusListener, KeyListener, ListSelectionListener,
			MouseListener
	{
        FormListener() {
        	
        }
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == cancelButton) {
                dispose();
            } else if (evt.getSource() == exportButton) {
                export();
            } else if (evt.getSource() == browseButton) {
                browse();
            } else if (evt.getSource() == filterButton) {
                filter();
            }
            else if (evt.getSource() == addAllButton) {
            	moveSelectedItems(allColumnList, selectedColumnList, true);
            } else if (evt.getSource() == addSelectedButton) {
            	moveSelectedItems(allColumnList, selectedColumnList, false);
            } else if (evt.getSource() == removeSelectedButton) {
                moveSelectedItems(selectedColumnList, allColumnList, false);
            } else if (evt.getSource() == removeAllButton) {
            	moveSelectedItems(selectedColumnList, allColumnList, true);
            } else if (evt.getSource() == moveUpButton) {
                
            } else if (evt.getSource() == moveDownButton) {
                
            }
        }
		
		public void focusGained(FocusEvent evt) {
			
		}
		
		public void focusLost(FocusEvent evt) {
			if (evt.getSource() == whereClauseTextField) {
                
            }
		}
		
		public void keyPressed(KeyEvent evt) {
			
		}
		
		public void keyReleased(KeyEvent evt) {
			
		}
		
		public void keyTyped(KeyEvent evt) {
			
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
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

	
	private void moveSelectedItems(JList from, JList to, boolean all){
		ColumnNameListModel fromModel = (ColumnNameListModel) from.getModel();
		ColumnNameListModel toModel = (ColumnNameListModel) to.getModel();
		if(! all){
			int[] fromIndeces = from.getSelectedIndices();
			if(fromIndeces == null || fromIndeces.length > 0){
				
			}
		} else {
			List<Column> fromList = fromModel.getColumnList();
			for (Column column : fromList) {
				toModel.addColumn(column);
			}
			fromModel.clear();
		}
		from.updateUI();
		to.updateUI();
	}
	
	private void export() {
		
	}

	private void browse() {
		ExtensionFileFilter fileFilter = new ExtensionFileFilter(
				new String[]{getExportTypeEnum().getExtension()}, 
				getExportTypeEnum().getDescription()
			);
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(fileFilter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		int opt = chooser.showOpenDialog(getParentFrame());
		if(JFileChooser.APPROVE_OPTION == opt){
			File file = chooser.getSelectedFile();
			if(file != null){
				String fileName = file.getAbsolutePath();
				if(!fileName.endsWith(getExportTypeEnum().getExtension())){
					fileName += getExportTypeEnum().getExtension();
				}
				outputFileTextField.setText(fileName);
			}
		}
	}

	private void filter() {

	}
	
	
	
	
	
	
    /**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}


	/**
	 * @return the exportTypeEnum
	 */
	public TableDataExportTypeEnum getExportTypeEnum() {
		return exportTypeEnum;
	}


	/**
	 * @return the connectionProperties
	 */
	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}


	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}


	/**
	 * @param exportTypeEnum the exportTypeEnum to set
	 */
	public void setExportTypeEnum(TableDataExportTypeEnum exportTypeEnum) {
		this.exportTypeEnum = exportTypeEnum;
	}


	/**
	 * @param connectionProperties the connectionProperties to set
	 */
	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}


    
    // Variables declaration 
    private JButton browseButton;
    private JButton cancelButton;
    private JPanel columnSelectionPanel;
    private JPanel dataFilterPanel;
    private JButton exportButton;
    private JPanel exportFileFormatPanel;
    private JTabbedPane exportTabbedPane;
    private JButton filterButton;
    private JLabel formatLabel;
    private JLabel formatNameLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTree jTree1;
    private JPanel mainPanel;
    private JTextField outputFileTextField;
    private JLabel outputLabel;
    private JTable sampleDataTable;
    private JLabel schemaNameLabel;
    private JTextField schemaNameTextField;
	private JLabel tableNameLabel;
    private JTextField tableNameTextField;
    private JTextField whereClauseTextField;
    
    private JList allColumnList, selectedColumnList;
    private JButton removeAllButton, removeSelectedButton,
    		addAllButton, addSelectedButton, 
    		moveUpButton, moveDownButton;
    private JScrollPane allColumnListScrollPane = new JScrollPane(),
    		selectedColumnListScrollPane = new JScrollPane();
    // End of variables declaration

}
