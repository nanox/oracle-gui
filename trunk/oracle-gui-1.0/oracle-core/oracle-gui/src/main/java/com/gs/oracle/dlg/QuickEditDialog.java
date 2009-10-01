/**
 * 
 */
package com.gs.oracle.dlg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.util.WindowUtil;
import com.gs.oracle.vo.QuickEditVO;

/**
 * @author Green Moon
 *
 */
public class QuickEditDialog extends JDialog {

	private JFrame parentFrame;
	private QuickEditVO quickEditVO;
	
	
    public QuickEditDialog(JFrame parent, QuickEditVO quickEditVO) {
        super(parent, true);
        this.parentFrame = parent;
        this.quickEditVO = quickEditVO;
        initComponents();
        setMinimumSize(new Dimension(300, 400));
        setPreferredSize(getMinimumSize());
        WindowUtil.bringCenterTo(this, parentFrame);
        getRootPane().setDefaultButton(updateButton);
        valueTextArea.requestFocus();
    }

    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        mainPanel = new JPanel();
        jLabel1 = new JLabel();
        schemaNameTextField = new JTextField();
        jLabel2 = new JLabel();
        tableNameTextField = new JTextField();
        jLabel3 = new JLabel();
        jScrollPane1 = new JScrollPane();
        valueTextArea = new JTextArea();
        cancelButton = new JButton();
        updateButton = new JButton();
        columnNameLabel = new JLabel();
        jSeparator1 = new JSeparator();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        setTitle("Quick Edit"); 
        setName("Form"); 

        mainPanel.setName("mainPanel");
        mainPanel.setLayout(new GridBagLayout());

        jLabel1.setText("SCHEMA : "); 
        jLabel1.setName("jLabel1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jLabel1, gridBagConstraints);

        schemaNameTextField.setBackground(new Color(255, 255, 255));
        schemaNameTextField.setEditable(false);
        schemaNameTextField.setForeground(new Color(0, 0, 204));
        schemaNameTextField.setText(getQuickEditVO().getSchemaName()); 
        schemaNameTextField.setName("schemaNameTextField"); 
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(schemaNameTextField, gridBagConstraints);

        jLabel2.setText("TABLE : ");
        jLabel2.setName("jLabel2"); 
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jLabel2, gridBagConstraints);

        tableNameTextField.setBackground(new Color(255, 255, 255));
        tableNameTextField.setEditable(false);
        tableNameTextField.setForeground(new Color(0, 0, 204));
        tableNameTextField.setText(getQuickEditVO().getTableName()); 
        tableNameTextField.setName("tableNameTextField");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(tableNameTextField, gridBagConstraints);

        jLabel3.setText("Value : ");
        jLabel3.setName("jLabel3");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jLabel3, gridBagConstraints);

        jScrollPane1.setName("jScrollPane1"); 

        valueTextArea.setText(getQuickEditVO().getCurrentColumnValue());
        valueTextArea.setColumns(20);
        valueTextArea.setRows(5);
        valueTextArea.setName("valueTextArea"); 
        jScrollPane1.setViewportView(valueTextArea);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        mainPanel.add(jScrollPane1, gridBagConstraints);

        cancelButton.setText("Cancel"); 
        cancelButton.setName("cancelButton");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new Insets(4, 2, 2, 2);
        mainPanel.add(cancelButton, gridBagConstraints);

        updateButton.setText("Update"); 
        updateButton.setName("updateButton"); 
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new Insets(4, 2, 2, 2);
        mainPanel.add(updateButton, gridBagConstraints);

        columnNameLabel.setText(getQuickEditVO().getCurrentColumnName() + " = ?");
        columnNameLabel.setName("columnNameLabel"); 
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(4, 2, 4, 2);
        mainPanel.add(columnNameLabel, gridBagConstraints);

        jSeparator1.setName("jSeparator1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 0, 2, 0);
        mainPanel.add(jSeparator1, gridBagConstraints);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	QuickEditVO v = new QuickEditVO();
            	v.setSchemaName("Schema x");
            	v.setTableName("Table x");
            	v.setCurrentColumnName("currentColumnName");
            	v.setCurrentColumnValue("currentColumnValue");
                QuickEditDialog dialog = new QuickEditDialog(new JFrame(), v);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration 
    private JButton cancelButton;
    private JLabel columnNameLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JPanel mainPanel;
    private JTextField schemaNameTextField;
    private JTextField tableNameTextField;
    private JButton updateButton;
    private JTextArea valueTextArea;
    // End of variables declaration



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


	/**
	 * @return the quickEditVO
	 */
	public QuickEditVO getQuickEditVO() {
		return quickEditVO;
	}


	/**
	 * @param quickEditVO the quickEditVO to set
	 */
	public void setQuickEditVO(QuickEditVO quickEditVO) {
		this.quickEditVO = quickEditVO;
	}


    
}
