/**
 * 
 */
package com.gs.dbex.launcher;

import javax.swing.JFrame;

import com.gs.dbex.application.frame.DatabaseExplorerFrame;

/**
 * @author sabuj.das
 *
 */
public class DatabaseExplorerLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DatabaseExplorerFrame explorerFrame = new DatabaseExplorerFrame();
		if(explorerFrame != null){
			//explorerFrame.setSize(200,200);
			explorerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			explorerFrame.setVisible(true);
		}
	}

}
