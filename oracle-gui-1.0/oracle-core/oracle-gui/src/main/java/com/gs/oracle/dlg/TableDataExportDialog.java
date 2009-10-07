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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.gs.oracle.connection.ConnectionProperties;
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
    
    public TableDataExportDialog(JFrame parentFrame, Table table,
			TableDataExportTypeEnum exportTypeEnum,
			ConnectionProperties connectionProperties) {
    	super(parentFrame, true);
		this.parentFrame = parentFrame;
		this.table = table;
		this.exportTypeEnum = exportTypeEnum;
		this.connectionProperties = connectionProperties;
		initComponents();
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

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Export Data - " + getTable().getModelName());
        getContentPane().setLayout(new GridBagLayout());

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

        tableNameTextField.addActionListener(formListener);
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

        columnSelectionPanel.setLayout(new BorderLayout());

        jScrollPane1.setViewportView(jTree1);

        columnSelectionPanel.add(jScrollPane1, BorderLayout.CENTER);

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


	private class FormListener implements ActionListener {
        FormListener() {
        	
        }
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == tableNameTextField) {
                
            }
        }
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
    // End of variables declaration

}
