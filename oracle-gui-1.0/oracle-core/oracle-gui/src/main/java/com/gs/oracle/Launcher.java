/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: Launcher.java
 *	Type	: com.gs.oracle.Launcher.java
 *	Date	: Aug 3, 2009	11:04:09 AM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle;

import javax.swing.UIManager;

import com.gs.oracle.frame.OracleGuiMainFrame;

/**
 * @author sabuj.das
 *
 */
public class Launcher {

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            
        }
		OracleGuiMainFrame frame = new OracleGuiMainFrame();
		frame.setVisible(true);
	}
}
