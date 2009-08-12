/**
 * 
 */
package com.gs.oracle.iframe;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.gs.oracle.comps.DatabaseDirectoryTree;
import com.gs.oracle.comps.SqlQueryPanel;

/**
 * @author sabuj.das
 *
 */
public class DatabaseViewerInternalFrame extends JInternalFrame implements WindowListener{

	private JSplitPane outterSplitPane, innerSplitPane;
	private JPanel mainPanel;
	
	public DatabaseViewerInternalFrame() {
		initComponents();
	}
	
	private void initComponents(){
		setTitle("Change it...");
		setLocation(0, 0);
		setSize(600, 450);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		outterSplitPane = new JSplitPane();
		outterSplitPane.setDividerLocation(150);
		outterSplitPane.setContinuousLayout(true);
		outterSplitPane.setOneTouchExpandable(true);
		outterSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		outterSplitPane.setLeftComponent(
				new JScrollPane(new DatabaseDirectoryTree("Change It...")));
		mainPanel.add(outterSplitPane, BorderLayout.CENTER);
		
		innerSplitPane = new JSplitPane();
		innerSplitPane.setDividerLocation(250);
		innerSplitPane.setContinuousLayout(true);
		innerSplitPane.setOneTouchExpandable(true);
		innerSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		innerSplitPane.setTopComponent(new SqlQueryPanel());
		outterSplitPane.setRightComponent(innerSplitPane);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		//pack();
	}
	
	


	public JSplitPane getOutterSplitPane() {
		return outterSplitPane;
	}

	public void setOutterSplitPane(JSplitPane outterSplitPane) {
		this.outterSplitPane = outterSplitPane;
	}

	public JSplitPane getInnerSplitPane() {
		return innerSplitPane;
	}

	public void setInnerSplitPane(JSplitPane innerSplitPane) {
		this.innerSplitPane = innerSplitPane;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
