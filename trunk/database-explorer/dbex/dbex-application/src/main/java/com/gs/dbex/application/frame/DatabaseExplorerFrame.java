/**
 * 
 */
package com.gs.dbex.application.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.gs.dbex.service.DatabaseConnectionService;
import com.gs.utils.swing.window.WindowUtil;

/**
 * @author sabuj.das
 *
 */
public class DatabaseExplorerFrame extends JFrame implements ActionListener {

	private DatabaseConnectionService databaseConnectionService;
	
	
	public DatabaseExplorerFrame() {
		setInitialProperties();
	}
	
	private void setInitialProperties(){
		setSize(880, 600);
		setTitle("Oracle GUI");
		/*setIconImage((new ImageIcon(DatabaseExplorerFrame.class
				.getResource(OracleGuiConstants.IMAGE_PATH + "Oracle-Gui.gif")))
				.getImage());*/
		WindowUtil.bringToCenter(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the databaseConnectionService
	 */
	public DatabaseConnectionService getDatabaseConnectionService() {
		return databaseConnectionService;
	}

	/**
	 * @param databaseConnectionService the databaseConnectionService to set
	 */
	public void setDatabaseConnectionService(
			DatabaseConnectionService databaseConnectionService) {
		this.databaseConnectionService = databaseConnectionService;
	}

	
	
}
