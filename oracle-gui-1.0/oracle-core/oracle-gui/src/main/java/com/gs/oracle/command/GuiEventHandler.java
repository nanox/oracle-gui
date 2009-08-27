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

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.dlg.ConnectionDialog;
import com.gs.oracle.frame.OracleGuiMainFrame;
import com.gs.oracle.iframe.DatabaseViewerInternalFrame;
import com.gs.oracle.service.DatabaseConnectionService;
import com.gs.oracle.service.impl.DatabaseConnectionServiceImpl;
import com.gs.oracle.util.DisplayTypeEnum;
import com.gs.oracle.util.DisplayUtils;
import com.gs.oracle.util.MenuBarUtil;
import com.gs.oracle.util.WindowUtil;

/**
 * @author Green Moon
 *
 */
public class GuiEventHandler implements ActionListener, GuiCommandConstants {

	private Component parent, sourceForm;
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
				Runnable connRun = new Runnable(){
					@Override
					public void run() {
						ConnectionDialog dlg = (ConnectionDialog) sourceForm;
						dlg.disableButtons(true);
						OracleGuiMainFrame frame = (OracleGuiMainFrame) parent;
						frame.getStatusBar().getCurrentStatusLabel().setIcon(new ImageIcon(this.getClass()
								.getResource(OracleGuiConstants.IMAGE_PATH + "loading.gif")));
						frame.getStatusBar().getCurrentStatusLabel().setText("Connecting to Database. Please wait...");
						Connection conn = null;
						try {
							conn = connectionService.createConnection((ConnectionProperties) getData());
						} catch (ApplicationException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						//TODO: Need to change the condition
						if(conn != null){
							ConnectionProperties p = (ConnectionProperties) data;
							p.setConnection(conn);
							DatabaseViewerInternalFrame iFrame = new DatabaseViewerInternalFrame(p);
							iFrame.setVisible(true);
							((OracleGuiMainFrame)parent).getMainDesktopPane().add(iFrame);
							((ConnectionDialog)getSourceForm()).dispose();
						}else{
							dlg.disableButtons(false);
							DisplayUtils.displayMessage(parent, "ERROR", DisplayTypeEnum.INFO);
						}
						frame.getStatusBar().getCurrentStatusLabel().setIcon(null);
						frame.getStatusBar().getCurrentStatusLabel().setText("");
					}
				};
				new Thread(connRun).start();
			}else if(TEST_CONNECTION_ACT_CMD.equals(cmd)){
				Runnable testConnRun = new Runnable(){
					@Override
					public void run() {
						ConnectionDialog dlg = (ConnectionDialog) sourceForm;
						dlg.disableButtons(true);
						OracleGuiMainFrame frame = (OracleGuiMainFrame) parent;
						frame.getStatusBar().getCurrentStatusLabel().setIcon(new ImageIcon(this.getClass()
								.getResource(OracleGuiConstants.IMAGE_PATH + "loading.gif")));
						frame.getStatusBar().getCurrentStatusLabel().setText("Connecting to Database. Please wait...");
						
						if(data != null){
							if(data instanceof ConnectionProperties){
								try{
									boolean b = connectionService.testConnection((ConnectionProperties) data);
									String success = "SUCCESSFUL";
									if(!b){
										success = "FAILED";
									}
									DisplayUtils.displayMessage(parent, success, DisplayTypeEnum.INFO);
								}catch(ApplicationException ex){
									
								}
							}
						}
						dlg.disableButtons(false);
						frame.getStatusBar().getCurrentStatusLabel().setIcon(null);
						frame.getStatusBar().getCurrentStatusLabel().setText("");
					}
				};
				new Thread(testConnRun).start();
			} else if(VIEW_TABLE_DETAILS_ACT_CMD.equals(cmd)){
				
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

	public Component getSourceForm() {
		return sourceForm;
	}

	public void setSourceForm(Component sourceForm) {
		this.sourceForm = sourceForm;
	}

	public DatabaseConnectionService getConnectionService() {
		return connectionService;
	}

	public void setConnectionService(DatabaseConnectionService connectionService) {
		this.connectionService = connectionService;
	}
	
	
}
