/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: Launcher.java
 *	Type	: com.gs.oracle.Launcher.java
 *	Date	: Aug 3, 2009	11:04:09 AM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.oracle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.gs.oracle.frame.OracleGuiMainFrame;

/**
 * @author sabuj.das
 *
 */
public class Launcher {
	private static Logger logger = Logger.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		logger.info("Launching Application : Oracle GUI. -- ");
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	
        }
		
        OracleGuiMainFrame frame = new OracleGuiMainFrame();
        frame.setVisible(true);
		
	}
}
