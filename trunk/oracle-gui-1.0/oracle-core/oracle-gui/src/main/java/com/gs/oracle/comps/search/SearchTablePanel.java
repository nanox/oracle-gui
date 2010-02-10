/**
 * 
 */
package com.gs.oracle.comps.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.enums.ObjectTypeEnum;
import com.gs.oracle.vo.TableSearchCriteria;

/**
 * @author sabuj.das
 *
 */
public class SearchTablePanel extends JPanel implements ActionListener {

	private static final Icon DB_SEARCH_ICON = new ImageIcon(
			SearchTablePanel.class.getResource(OracleGuiConstants.IMAGE_PATH + "database_search.png"));
	private static final Icon LOADING_SEARCH_RESULT_ICON = new ImageIcon(
			SearchTablePanel.class.getResource(OracleGuiConstants.IMAGE_PATH + "loading_001.gif"));
	
    public SearchTablePanel() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        tableNameTextField = new JTextField();
        jLabel3 = new JLabel();
        availableSchemasComboBox = new JComboBox();
        allSchemaCheckBox = new JCheckBox();
        jLabel4 = new JLabel();
        normalCheckBox = new JCheckBox();
        externalCheckBox = new JCheckBox();
        indexedCheckBox = new JCheckBox();
        transactionCheckBox = new JCheckBox();
        sessionCheckBox = new JCheckBox();
        jSeparator1 = new JSeparator();
        jLabel5 = new JLabel();
        jScrollPane1 = new JScrollPane();
        searchResultTable = new JTable();
        clearButton = new JButton();
        searchResultLabel = new JLabel();
        searchButton = new JButton();
        imageLabel = new JLabel();

        setLayout(new GridBagLayout());

        jLabel1.setText("<HTML> <BODY> <B><U>S</U>earch Table</B> ( * = any string, ? = any char) </BODY> </HTML>");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(8, 2, 8, 2);
        add(jLabel1, gridBagConstraints);

        jLabel2.setText("Table Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(tableNameTextField, gridBagConstraints);

        jLabel3.setText("In Schema");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(jLabel3, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(availableSchemasComboBox, gridBagConstraints);

        allSchemaCheckBox.setText("All available Schemas");
        allSchemaCheckBox.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 0, 2, 2);
        add(allSchemaCheckBox, gridBagConstraints);

        jLabel4.setText("Table Type");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(jLabel4, gridBagConstraints);

        normalCheckBox.setText("Normal");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 0, 2, 2);
        add(normalCheckBox, gridBagConstraints);

        externalCheckBox.setText("External");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(externalCheckBox, gridBagConstraints);

        indexedCheckBox.setText("Index Organized");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(indexedCheckBox, gridBagConstraints);

        transactionCheckBox.setText("Temporary (Transaction)");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 0, 2, 2);
        add(transactionCheckBox, gridBagConstraints);

        sessionCheckBox.setText("Temporary (Session)");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(sessionCheckBox, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(6, 2, 6, 2);
        add(jSeparator1, gridBagConstraints);

        jLabel5.setText("Search Result  ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(jLabel5, gridBagConstraints);

        searchResultTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Matching Table", "Owner", "Open Table", "Show Content"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        searchResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        searchResultTable.setColumnSelectionAllowed(true);
        searchResultTable.setGridColor(new Color(153, 204, 255));
        searchResultTable.setPreferredSize(new Dimension(400, 64));
        jScrollPane1.setViewportView(searchResultTable);
        searchResultTable.getColumnModel().getSelectionModel()
        	.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(jScrollPane1, gridBagConstraints);

        clearButton.setText("Clear");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(clearButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(searchResultLabel, gridBagConstraints);

        searchButton.setText("Search");
        searchButton.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(searchButton, gridBagConstraints);

        imageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        imageLabel.setIcon(DB_SEARCH_ICON);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        add(imageLabel, gridBagConstraints);
    }


    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == allSchemaCheckBox) {
            SearchTablePanel.this.allSchemaCheckBoxActionPerformed(evt);
        } else if (evt.getSource() == searchButton) {
            search();
        }
    }

    private void search() {
		String searchString = tableNameTextField.getText();
		//String ownerName = availableSchemasComboBox.getSelectedItem().toString();
		TableSearchCriteria criteria = new TableSearchCriteria("", 
				searchString, ObjectTypeEnum.TABLE.getTypeCode(), true);
		System.out.println(criteria.getSearchQuery());
	}

	private void allSchemaCheckBoxActionPerformed(ActionEvent evt) {
        if(allSchemaCheckBox.isSelected()){
            availableSchemasComboBox.setEnabled(false);
        } else if(!allSchemaCheckBox.isSelected()){
            availableSchemasComboBox.setEnabled(true);
        }
    }


    private JCheckBox allSchemaCheckBox;
    private JComboBox availableSchemasComboBox;
    private JButton clearButton;
    private JCheckBox externalCheckBox;
    private JLabel imageLabel;
    private JCheckBox indexedCheckBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JCheckBox normalCheckBox;
    private JButton searchButton;
    private JLabel searchResultLabel;
    private JTable searchResultTable;
    private JCheckBox sessionCheckBox;
    private JTextField tableNameTextField;
    private JCheckBox transactionCheckBox;

}
