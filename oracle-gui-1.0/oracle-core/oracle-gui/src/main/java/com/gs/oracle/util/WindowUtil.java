/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: WindowUtil.java
 *	Type	: com.gs.oracle.util.WindowUtil.java
 *	Date	: Jul 29, 2009	8:15:51 PM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @author Green Moon
 *
 */
public class WindowUtil {

	/**
	 * This method changes the location of the frame to move it to
	 * the center of the screen.
	 * @param frame
	 */
	public static void bringToCenter(final JFrame frame){
		Dimension originalSize = frame.getSize();
		Dimension scrResolution = Toolkit.getDefaultToolkit().getScreenSize();
		Point location = new Point(
				(int)((scrResolution.getWidth() / 2) - (originalSize.getWidth() / 2)),
				(int)((scrResolution.getHeight() / 2) - (originalSize.getHeight() / 2))
			);
		frame.setLocation(location);
	}
	
}
