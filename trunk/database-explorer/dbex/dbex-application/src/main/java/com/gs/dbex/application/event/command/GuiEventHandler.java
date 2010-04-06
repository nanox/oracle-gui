package com.gs.dbex.application.event.command;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.omg.CORBA.portable.ApplicationException;

import com.gs.dbex.application.accesscontrol.AuthorizationController;
import com.gs.dbex.application.connection.ConnectionDialog;
import com.gs.dbex.application.dlg.OpenResourceDialog;
import com.gs.dbex.application.dlg.SearchObjectDialog;
import com.gs.dbex.application.dlg.StyleConfigurationDialog;
import com.gs.dbex.application.iframe.DatabaseViewerInternalFrame;
import com.gs.dbex.application.menu.MenuBarItems;
import com.gs.dbex.application.panel.SqlQueryPanel;
import com.gs.dbex.application.panel.TableContentPanel;
import com.gs.dbex.application.panel.TableDetailsPanel;
import com.gs.dbex.application.tab.ButtonTabComponent;
import com.gs.dbex.application.util.DisplayTypeEnum;
import com.gs.dbex.application.util.DisplayUtils;
import com.gs.dbex.application.util.WindowUtil;
import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.core.oracle.OracleDbGrabber;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Table;
import com.gs.dbex.service.DatabaseConnectionService;
import com.gs.dbex.service.impl.DatabaseConnectionServiceImpl;
import com.gs.utils.text.StringUtil;

/**
 * @author Sabuj Das
 *
 *	This is an EventHandler for handling common events.
 *
 */
public class GuiEventHandler implements ActionListener, GuiCommandConstants {

	private Component parent, sourceForm;
	private Object data;
	private DatabaseConnectionService connectionService;
	
	public GuiEventHandler() {
		connectionService = new DatabaseConnectionServiceImpl();
	}
	
	public void actionPerformed(ActionEvent e) {
		
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
