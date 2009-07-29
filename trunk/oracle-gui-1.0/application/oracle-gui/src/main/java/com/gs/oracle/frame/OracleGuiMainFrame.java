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

import javax.swing.JFrame;

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

	public OracleGuiMainFrame() {
		setInitialProperties();
	}
	
	private void setInitialProperties(){
		setSize(800, 600);
		setTitle("Oracle GUI");
		WindowUtil.bringToCenter(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
