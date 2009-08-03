/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: OracleGuiMainFrame.java
 *	Type	: com.gs.oracle.frame.OracleGuiMainFrame.java
 *	Date	: Jul 29, 2009	8:14:51 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.frame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import com.gs.oracle.comps.ToolbarButtons;
import com.gs.oracle.dlg.StatusBar;
import com.gs.oracle.util.MenuBarUtil;
import com.gs.oracle.util.WindowUtil;

/**
 * @author Green Moon
 *
 */
public class OracleGuiMainFrame extends JFrame {

	/**
	 *  Generated serialVersionUID
	 */
	private static final long serialVersionUID = -2179986000548903441L;
	
	
	private JDesktopPane mainDesktopPane;
    private JMenuBar mainMenuBar;
    private JPanel mainPanel;
    private JToolBar mainToolBar;
    
    private StatusBar statusBar;

	public OracleGuiMainFrame() {
		initComponents();
		setInitialProperties();
	}
	
	private void setInitialProperties(){
		setSize(800, 600);
		setTitle("Oracle GUI");
		WindowUtil.bringToCenter(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new JPanel();
        mainToolBar = new JToolBar();
        mainDesktopPane = new JDesktopPane();
        mainMenuBar = new JMenuBar();
        
        statusBar = new StatusBar();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); 

        mainPanel.setName("jPanel1"); 
        mainPanel.setLayout(new GridBagLayout());

        mainToolBar.setRollover(true);
        mainToolBar.setName("jToolBar1"); 
        mainToolBar.setFloatable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        mainToolBar.add(ToolbarButtons.createToolbarButton("", "", "Database.png"));
        mainPanel.add(mainToolBar, gridBagConstraints);

        mainDesktopPane.setName("jDesktopPane1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(mainDesktopPane, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        mainPanel.add(statusBar, gridBagConstraints);
        
        
        
        
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        mainMenuBar.setName("jMenuBar1"); 
        mainMenuBar.add(new JMenu("File"));
        setJMenuBar(mainMenuBar);

        pack();
    }
	
}
