/**
 * 
 */
package com.gs.oracle.pagination;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;



/**
 * @author sabuj.das
 *
 */
public class PaginatedTablePanel extends JPanel implements Serializable,
		ActionListener, MouseListener, KeyListener {

	

    /** Creates new form PaginatedTablePanel */
    public PaginatedTablePanel() {
        initComponents();
    }

   
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        topNavigationPanel = new JPanel();
        previousPageLabel = new JLabel();
        rowsPerPageLabel = new JLabel();
        rowsPerPageTextField = new JTextField();
        refreshButton = new JButton();
        hiddenLabel_01 = new JLabel();
        showActionsToolbarCheckBox = new JCheckBox();
        nextPageLabel = new JLabel();
        tablePanel = new JPanel();
        actionsToolBar = new JToolBar();
        addNewRecordButton = new JButton();
        editRecordButton = new JButton();
        deleteRecordButton = new JButton();
        jSeparator1 = new JToolBar.Separator();
        exportAsLabel = new JLabel();
        exportTypeComboBox = new JComboBox();
        exportButton = new JButton();
        jSeparator2 = new JToolBar.Separator();
        jButton1 = new JButton();
        targetTableScrollPane = new JScrollPane();
        targetTable = new JTable();
        bottomNavigationPanel = new JPanel();
        totaPageslLabel = new JLabel();
        pagerPanel = new JPanel();
        firstPageLinkLabel = new JLabel();
        secondPageLinkLabel = new JLabel();
        thirdPageLinkLabel = new JLabel();
        fourthPageLinkLabel = new JLabel();
        fifthPageLinkLabel = new JLabel();
        goToFirstPageLinkLabel = new JLabel();
        goToPreviousPageLabel = new JLabel();
        goToNextPageLinkLabel = new JLabel();
        goToLastPageLinkLabel = new JLabel();
        gotoPageLabel = new JLabel();
        gotoPageTextField = new JTextField();
        goButtonLabel = new JLabel();

        setLayout(new GridBagLayout());

        topNavigationPanel.setMinimumSize(new Dimension(100, 30));
        topNavigationPanel.setPreferredSize(new Dimension(710, 30));
        topNavigationPanel.setLayout(new GridBagLayout());

        previousPageLabel.setFont(new Font("Tahoma", 1, 11)); 
        previousPageLabel.setForeground(new Color(0, 0, 255));
        previousPageLabel.setText("Prev");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 2, 0, 0);
        topNavigationPanel.add(previousPageLabel, gridBagConstraints);

        rowsPerPageLabel.setText("Rows per Page:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 20, 0, 2);
        topNavigationPanel.add(rowsPerPageLabel, gridBagConstraints);

        rowsPerPageTextField.setFont(new Font("Tahoma", 1, 11));
        rowsPerPageTextField.setForeground(new Color(0, 0, 255));
        rowsPerPageTextField.setHorizontalAlignment(JTextField.CENTER);
        rowsPerPageTextField.setText("5");
        rowsPerPageTextField.setMinimumSize(new Dimension(60, 20));
        rowsPerPageTextField.setPreferredSize(new Dimension(60, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        topNavigationPanel.add(rowsPerPageTextField, gridBagConstraints);

        refreshButton.setText("Refresh");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        topNavigationPanel.add(refreshButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        topNavigationPanel.add(hiddenLabel_01, gridBagConstraints);

        showActionsToolbarCheckBox.setText("Show Actions");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 1, 0, 20);
        topNavigationPanel.add(showActionsToolbarCheckBox, gridBagConstraints);

        nextPageLabel.setFont(new Font("Tahoma", 1, 11));
        nextPageLabel.setForeground(new Color(0, 0, 255));
        nextPageLabel.setText("Next");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 2);
        topNavigationPanel.add(nextPageLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        add(topNavigationPanel, gridBagConstraints);

        tablePanel.setLayout(new GridBagLayout());

        actionsToolBar.setFloatable(false);
        actionsToolBar.setRollover(true);

        addNewRecordButton.setText("Add");
        addNewRecordButton.setFocusable(false);
        addNewRecordButton.setHorizontalTextPosition(SwingConstants.CENTER);
        addNewRecordButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        actionsToolBar.add(addNewRecordButton);

        editRecordButton.setText("Edit");
        editRecordButton.setFocusable(false);
        editRecordButton.setHorizontalTextPosition(SwingConstants.CENTER);
        editRecordButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        actionsToolBar.add(editRecordButton);

        deleteRecordButton.setText("Delete");
        deleteRecordButton.setFocusable(false);
        deleteRecordButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteRecordButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        actionsToolBar.add(deleteRecordButton);
        actionsToolBar.add(jSeparator1);

        exportAsLabel.setText("Export As..");
        actionsToolBar.add(exportAsLabel);

        exportTypeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        exportTypeComboBox.setMaximumSize(new Dimension(100, 18));
        exportTypeComboBox.setMinimumSize(new Dimension(50, 18));
        exportTypeComboBox.setPreferredSize(new Dimension(50, 18));
        actionsToolBar.add(exportTypeComboBox);

        exportButton.setText("Export");
        exportButton.setFocusable(false);
        exportButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exportButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        actionsToolBar.add(exportButton);
        actionsToolBar.add(jSeparator2);

        jButton1.setText("Filter");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
        actionsToolBar.add(jButton1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        tablePanel.add(actionsToolBar, gridBagConstraints);

        // TODO targetTable.setModel();
        targetTableScrollPane.setViewportView(targetTable);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tablePanel.add(targetTableScrollPane, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(tablePanel, gridBagConstraints);

        bottomNavigationPanel.setMinimumSize(new Dimension(100, 30));
        bottomNavigationPanel.setPreferredSize(new Dimension(710, 30));
        bottomNavigationPanel.setLayout(new GridBagLayout());

        totaPageslLabel.setText("999 pages found");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        bottomNavigationPanel.add(totaPageslLabel, gridBagConstraints);

        pagerPanel.setLayout(new GridBagLayout());

        firstPageLinkLabel.setText("1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(firstPageLinkLabel, gridBagConstraints);

        secondPageLinkLabel.setText("2");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(secondPageLinkLabel, gridBagConstraints);

        thirdPageLinkLabel.setText("3");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(thirdPageLinkLabel, gridBagConstraints);

        fourthPageLinkLabel.setText("4");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(fourthPageLinkLabel, gridBagConstraints);

        fifthPageLinkLabel.setText("5");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(fifthPageLinkLabel, gridBagConstraints);

        goToFirstPageLinkLabel.setText("<<");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 6);
        pagerPanel.add(goToFirstPageLinkLabel, gridBagConstraints);

        goToPreviousPageLabel.setText("<");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 6);
        pagerPanel.add(goToPreviousPageLabel, gridBagConstraints);

        goToNextPageLinkLabel.setText(">");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 6, 0, 2);
        pagerPanel.add(goToNextPageLinkLabel, gridBagConstraints);

        goToLastPageLinkLabel.setText(">>");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 6, 0, 2);
        pagerPanel.add(goToLastPageLinkLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        bottomNavigationPanel.add(pagerPanel, gridBagConstraints);

        gotoPageLabel.setText("Go To ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        bottomNavigationPanel.add(gotoPageLabel, gridBagConstraints);

        gotoPageTextField.setHorizontalAlignment(JTextField.RIGHT);
        gotoPageTextField.setMinimumSize(new Dimension(60, 20));
        gotoPageTextField.setPreferredSize(new Dimension(60, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        bottomNavigationPanel.add(gotoPageTextField, gridBagConstraints);

        goButtonLabel.setText(">>");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        bottomNavigationPanel.add(goButtonLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        add(bottomNavigationPanel, gridBagConstraints);
    }


    // Variables declaration
    private JToolBar actionsToolBar;
    private JButton addNewRecordButton;
    private JPanel bottomNavigationPanel;
    private JButton deleteRecordButton;
    private JButton editRecordButton;
    private JLabel exportAsLabel;
    private JButton exportButton;
    private JComboBox exportTypeComboBox;
    private JLabel fifthPageLinkLabel;
    private JLabel firstPageLinkLabel;
    private JLabel fourthPageLinkLabel;
    private JLabel goButtonLabel;
    private JLabel goToFirstPageLinkLabel;
    private JLabel goToLastPageLinkLabel;
    private JLabel goToNextPageLinkLabel;
    private JLabel goToPreviousPageLabel;
    private JLabel gotoPageLabel;
    private JTextField gotoPageTextField;
    private JLabel hiddenLabel_01;
    private JButton jButton1;
    private JToolBar.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JLabel nextPageLabel;
    private JPanel pagerPanel;
    private JLabel previousPageLabel;
    private JButton refreshButton;
    private JLabel rowsPerPageLabel;
    private JTextField rowsPerPageTextField;
    private JLabel secondPageLinkLabel;
    private JCheckBox showActionsToolbarCheckBox;
    private JPanel tablePanel;
    private JTable targetTable;
    private JScrollPane targetTableScrollPane;
    private JLabel thirdPageLinkLabel;
    private JPanel topNavigationPanel;
    private JLabel totaPageslLabel;
    // End of variables declaration

	
	/* (non-Javadoc)
	 * @see event.ActionListener#actionPerformed(event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mouseClicked(event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mouseEntered(event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mouseExited(event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mousePressed(event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mouseReleased(event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.KeyListener#keyPressed(event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.KeyListener#keyReleased(event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see event.KeyListener#keyTyped(event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
