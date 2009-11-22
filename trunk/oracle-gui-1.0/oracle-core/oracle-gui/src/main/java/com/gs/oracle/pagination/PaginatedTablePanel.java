/**
 * 
 */
package com.gs.oracle.pagination;

import java.awt.Color;
import java.awt.Cursor;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.gs.oracle.comps.ResultSetTableModel;
import com.gs.oracle.comps.ResultSetTableModelFactory;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.util.DisplayTypeEnum;
import com.gs.oracle.util.DisplayUtils;
import com.gs.oracle.vo.PaginationResult;



/**
 * @author sabuj.das
 *
 */
public class PaginatedTablePanel extends JPanel implements Serializable,
		ActionListener, MouseListener, KeyListener {

	private static final Logger logger = Logger.getLogger(PaginatedTablePanel.class);
	
	public static final int MIN_RECORDS_PER_PAGE = 10;
	public static final int MAX_RECORDS_PER_PAGE = 99999;
	
	private JFrame parentFrame;
	private ConnectionProperties connectionProperties;
	private ResultSetTableModelFactory resultSetTableModelFactory;
	//private ResultSetTableModel resultSetTableModel;
	
	private String queryString;
	private String countQuery;
	private PaginationResult paginationResult;

    public PaginatedTablePanel(JFrame parentFrame, 
    		ConnectionProperties connectionProperties, String query, String countQuery) {
    	this.parentFrame = parentFrame;
        this.connectionProperties = connectionProperties;
        this.queryString = query;
        this.countQuery = countQuery;
        try{
        	resultSetTableModelFactory = new ResultSetTableModelFactory(
        			connectionProperties.getDataSource().getConnection());
        	
        } catch(SQLException sqx){
        	DisplayUtils.displayMessage(parentFrame, "Cannot create connection to database.", DisplayTypeEnum.ERROR);
        }
        
        paginationResult = new PaginationResult();
        paginationResult.setStartRow(0);
        initComponents();
        
        populatePaginatedResult(1);
        
    }
    
    public void populatePaginatedResult(int pageNumber){
    	
    	if(resultSetTableModelFactory != null){
        	
        	paginationResult.setCurrentPage(pageNumber);
        	paginationResult.setRowsPerPage(MIN_RECORDS_PER_PAGE);
        	
        	showTableData();
        	
        }
    }
    
    private int getTotalRecords(){
    	int totalRows = 0;
		Connection connection = null;
    	try {
    		logger.info("Populating Data in table for Page number : "+ paginationResult.getCurrentPage());
			connection = getConnectionProperties().getDataSource().getConnection();
			
			Statement statement = connection.prepareStatement(countQuery);
			ResultSet rs = statement.executeQuery(countQuery);
			if(rs != null){
				while(rs.next()){
					totalRows = rs.getInt(1);
				}
				rs.close();
			}
			logger.info("Total " + totalRows + " found by the query : " + countQuery);
			
		} catch (SQLException e) {
			DisplayUtils.displayMessage(getParentFrame(), e.getMessage(), DisplayTypeEnum.ERROR);
		} catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return totalRows;
    }
    
    private void showTableData() {
		paginationResult.setRowAttributes(getTotalRecords());
		paginationResult.setEndRow(paginationResult.getStartRow() + paginationResult.getRowsPerPage());
		int rowNumFrom = paginationResult.getStartRow();
    	int rowNumTo = paginationResult.getEndRow();
    	logger.info("Executing query : " + getQueryString() + " With LIMIT > " + rowNumFrom + " and < " + rowNumTo);
    	try {
			targetTable.setModel(resultSetTableModelFactory.getResultSetTableModel(getQueryString(), rowNumFrom, rowNumTo));
		} catch (SQLException e) {
			DisplayUtils.displayMessage(getParentFrame(), e.getMessage(), DisplayTypeEnum.ERROR);
		} catch(Exception e){
			e.printStackTrace();
		}
		populatePageLinks();
		
	}

    
    private void populatePageLinks() {
    	if(paginationResult.isPreviousPage()){
			previousPageLabel.setVisible(true);
		}else{
			previousPageLabel.setVisible(false);
		}
		if(paginationResult.isNextPage()){
			nextPageLabel.setVisible(true);
		}else{
			nextPageLabel.setVisible(false);
		}
		alterPageNumbers();
	}
    
    public void alterPageNumbers(){
		resetPageNumbers();
		int first = getFirstPage();
		if(first>0){
			firstPageLinkLabel.setText(""+first);
			if(first == paginationResult.getCurrentPage()){
				firstPageLinkLabel.setForeground(Color.BLUE);
			}else{
				firstPageLinkLabel.setForeground(Color.BLACK);
			}
			if(first+1 <= paginationResult.getTotalPages()){
				secondPageLinkLabel.setText(""+(first+1));
				if(first+1 == paginationResult.getCurrentPage()){
					secondPageLinkLabel.setForeground(Color.BLUE);
				}else{
					secondPageLinkLabel.setForeground(Color.BLACK);
				}
				if(first+2 <= paginationResult.getTotalPages()){
					thirdPageLinkLabel.setText(""+(first+2));
					if(first+2 == paginationResult.getCurrentPage()){
						thirdPageLinkLabel.setForeground(Color.BLUE);
					}else{
						thirdPageLinkLabel.setForeground(Color.BLACK);
					}
					if(first+3 <= paginationResult.getTotalPages()){
						fourthPageLinkLabel.setText(""+(first+3));
						if(first+3 == paginationResult.getCurrentPage()){
							fourthPageLinkLabel.setForeground(Color.BLUE);
						}else{
							fourthPageLinkLabel.setForeground(Color.BLACK);
						}
						if(first+4 <= paginationResult.getTotalPages()){
							fifthPageLinkLabel.setText(""+(first+4));
							if(first+4 == paginationResult.getCurrentPage()){
								fifthPageLinkLabel.setForeground(Color.BLUE);
							}else{
								fifthPageLinkLabel.setForeground(Color.BLACK);
							}
						}
					}
				}
			}
		}
		
	}
    
    private void resetPageNumbers(){
		firstPageLinkLabel.setText("");
		secondPageLinkLabel.setText("");
		thirdPageLinkLabel.setText("");
		fourthPageLinkLabel.setText("");
		fifthPageLinkLabel.setText("");
	}
    
    private int getFirstPage(){
		int page = 0;
		if(paginationResult.getTotalPages() <=5 || paginationResult.getCurrentPage() <=3){
			page = 1;
		}else if(paginationResult.getCurrentPage() + 1 > paginationResult.getTotalPages()){
			page = paginationResult.getCurrentPage() - 4;
		}else if(paginationResult.getCurrentPage()+2 > paginationResult.getTotalPages()){
			page = paginationResult.getCurrentPage()-3;
		}else{
			page = paginationResult.getCurrentPage()-2;
		}
		return page;
	}

	public void gotoNextPage(){
    	if(paginationResult.getCurrentPage() >= paginationResult.getTotalPages()){
    		return ;
    	}
    	paginationResult.setCurrentPage(
    		paginationResult.getCurrentPage() + 1);
    	gotoPage(paginationResult.getCurrentPage());
    }
    
    public void gotoPreviousPage(){
    	if(paginationResult.getCurrentPage() <= 0){
    		return ;
    	}
    	paginationResult.setCurrentPage(
    		paginationResult.getCurrentPage() - 1);
    	gotoPage(paginationResult.getCurrentPage());
    }
    
    public void gotoPage(int pageNumber){
    	
    	populatePaginatedResult(pageNumber);
    }
    
   
    /**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @return the connectionProperties
	 */
	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	/**
	 * @param connectionProperties the connectionProperties to set
	 */
	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
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
        previousPageLabel.addMouseListener(this);
        
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
        rowsPerPageTextField.setText("" + MIN_RECORDS_PER_PAGE);
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
        nextPageLabel.addMouseListener(this);
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

        targetTable.setAutoCreateRowSorter(true);
        targetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        targetTable.setAutoscrolls(true);
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

        totaPageslLabel.setText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        bottomNavigationPanel.add(totaPageslLabel, gridBagConstraints);

        pagerPanel.setLayout(new GridBagLayout());

        firstPageLinkLabel.setText("");
        firstPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(firstPageLinkLabel, gridBagConstraints);

        secondPageLinkLabel.setText("");
        secondPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(secondPageLinkLabel, gridBagConstraints);

        thirdPageLinkLabel.setText("");
        thirdPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(thirdPageLinkLabel, gridBagConstraints);

        fourthPageLinkLabel.setText("");
        fourthPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(fourthPageLinkLabel, gridBagConstraints);

        fifthPageLinkLabel.setText("");
        fifthPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 4, 0, 4);
        pagerPanel.add(fifthPageLinkLabel, gridBagConstraints);

        goToFirstPageLinkLabel.setText("<<");
        goToFirstPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 6);
        pagerPanel.add(goToFirstPageLinkLabel, gridBagConstraints);

        goToPreviousPageLabel.setText("<");
        goToPreviousPageLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 6);
        pagerPanel.add(goToPreviousPageLabel, gridBagConstraints);

        goToNextPageLinkLabel.setText(">");
        goToNextPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 6, 0, 2);
        pagerPanel.add(goToNextPageLinkLabel, gridBagConstraints);

        goToLastPageLinkLabel.setText(">>");
        goToLastPageLinkLabel.setFont(new Font("Tahoma", 1, 11));
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
		if(MouseEvent.BUTTON1 == e.getButton()
				&& e.getClickCount() == 1){
			if(e.getSource().equals(previousPageLabel)){
				gotoPreviousPage();
			}
			else if(e.getSource().equals(nextPageLabel)){
				gotoNextPage();
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mouseEntered(event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(previousPageLabel)){
			previousPageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(e.getSource().equals(nextPageLabel)){
			nextPageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	/* (non-Javadoc)
	 * @see event.MouseListener#mouseExited(event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(previousPageLabel)){
			previousPageLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		else if(e.getSource().equals(nextPageLabel)){
			nextPageLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
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
