/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: GuiEventHandler.java
 *	Type	: com.gs.oracle.command.GuiEventHandler.java
 *	Date	: Aug 4, 2009	10:24:13 AM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.command;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.gs.oracle.dlg.ConnectionDialog;
import com.gs.oracle.frame.OracleGuiMainFrame;
import com.gs.oracle.iframe.DatabaseViewerInternalFrame;
import com.gs.oracle.service.DatabaseConnectionService;
import com.gs.oracle.service.impl.DatabaseConnectionServiceImpl;
import com.gs.oracle.util.WindowUtil;

/**
 * @author Green Moon
 *
 */
public class GuiEventHandler implements ActionListener, GuiCommandConstants {

	private Component parent;
	private Object data;
	private DatabaseConnectionService connectionService;
	
	
	public GuiEventHandler() {
		connectionService = new DatabaseConnectionServiceImpl();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e != null){
			String cmd = e.getActionCommand();
			if(NEW_CONNECTION_ACT_CMD.equals(cmd)){
				ConnectionDialog dialog = new ConnectionDialog((JFrame)parent, true);
				WindowUtil.bringCenterTo(dialog, parent);
				dialog.setVisible(true);
			}else if(CREATE_CONNECTION_ACT_CMD.equals(cmd)){
				Connection conn = connectionService.createConnection(null);
				//TODO: Need to change the condition
				if(conn == null){
					DatabaseViewerInternalFrame frame = new DatabaseViewerInternalFrame();
					((OracleGuiMainFrame)parent).getMainDesktopPane().add(frame);
					frame.setVisible(true);
				}else{
					
				}
			}
		}
	}
	/**
	 * @return the parent
	 */
	public Component getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Component parent) {
		this.parent = parent;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
