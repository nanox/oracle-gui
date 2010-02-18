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

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.fife.plaf.Office2003.Office2003LookAndFeel;

import com.gs.oracle.frame.OracleGuiMainFrame;

/**
 * This is the entry point of the application.
 * 
 * @author sabuj.das
 *
 */
public class Launcher {
	private static Logger logger = Logger.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		logger.info("Launching Application : Oracle GUI. -- ");
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    String osName = System.getProperty("os.name");
	    logger.info("Operating System : " + osName);
	    //String lnfClass = UIManager.getCrossPlatformLookAndFeelClassName();
	    String lnfClass = UIManager.getSystemLookAndFeelClassName();
	    if(osName.toLowerCase().startsWith("win")){
	    	lnfClass = Office2003LookAndFeel.class.getCanonicalName();
	    }
		try {
			if(logger.isDebugEnabled())
				logger.debug("Applying default look and feel : " + lnfClass);
			UIManager.setLookAndFeel(lnfClass);
        } catch (Exception ex) {
        	logger.error("Cannot load the look and feel" , ex);
        }
		
        OracleGuiMainFrame frame = new OracleGuiMainFrame();
        frame.setVisible(true);
		
	}
}
