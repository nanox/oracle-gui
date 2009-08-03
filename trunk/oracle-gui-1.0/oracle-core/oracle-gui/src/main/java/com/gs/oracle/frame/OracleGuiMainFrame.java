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

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import com.gs.oracle.dlg.StatusBar;
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
	
	
	private JDesktopPane jDesktopPane1;
    private JMenuBar jMenuBar1;
    private JPanel jPanel1;
    private JToolBar jToolBar1;
    
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

        jPanel1 = new JPanel();
        jToolBar1 = new JToolBar();
        jDesktopPane1 = new JDesktopPane();
        jMenuBar1 = new JMenuBar();
        
        statusBar = new StatusBar();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jToolBar1.add(new JButton("sda"));
        jPanel1.add(jToolBar1, gridBagConstraints);

        jDesktopPane1.setName("jDesktopPane1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jDesktopPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        jPanel1.add(statusBar, gridBagConstraints);
        
        
        
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setName("jMenuBar1"); // NOI18N
        jMenuBar1.add(new JMenu("File"));
        setJMenuBar(jMenuBar1);

        pack();
    }
	
}
