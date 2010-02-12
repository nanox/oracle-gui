/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: OracleGuiContext.java
 *	Type	: com.gs.oracle.OracleGuiContext.java
 *	Date	: Jul 29, 2009	8:36:34 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.context;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

/**
 * @author Green Moon
 *
 */
public class OracleGuiContext implements Serializable {

	public static final String CONTEXT_FILE_NAME = "OracleGuiContext.ctx";

	protected static OracleGuiContext instance;
	private OracleGuiContext() {
		
	}
	public static OracleGuiContext getInstance(){
		if(instance == null)
			instance = new OracleGuiContext();
		return instance;
	}
	
	// data for main window
	public Point mainWindowLocation;
	public Dimension mainWindowSize;
	
}
