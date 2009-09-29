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
import javax.swing.UnsupportedLookAndFeelException;

import org.fife.plaf.Office2003.Office2003LookAndFeel;

import com.gs.oracle.frame.OracleGuiMainFrame;

import de.muntjak.tinylookandfeel.TinyLookAndFeel;

/**
 * @author sabuj.das
 *
 */
public class Launcher {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	try {
				UIManager.setLookAndFeel(TinyLookAndFeel.class.getCanonicalName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
        }
		OracleGuiMainFrame frame = new OracleGuiMainFrame();
		frame.setVisible(true);
	}
}
