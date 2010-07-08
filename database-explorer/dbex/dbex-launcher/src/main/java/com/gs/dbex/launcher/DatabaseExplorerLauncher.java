/**
 * 
 */
package com.gs.dbex.launcher;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.gs.dbex.application.frame.DatabaseExplorerFrame;
import com.gs.dbex.common.DbexCommonConstants;
import com.gs.dbex.common.DbexCommonContext;

/**
 * @author sabuj.das
 *
 */
public class DatabaseExplorerLauncher {

	private static final Logger logger = Logger.getLogger(DatabaseExplorerLauncher.class);
	private static DbexCommonContext dbexCommonContext = DbexCommonContext.getInstance();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Launching Database Explorer.");
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
        try{
        	createFiles();
        }catch (Exception ex){
        	ex.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	DatabaseExplorerFrame explorerFrame = new DatabaseExplorerFrame();
        		if(explorerFrame != null){
        			explorerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        			explorerFrame.setVisible(true);
        		}
            }
        });
        if(logger.isDebugEnabled()){
			logger.debug("");
		}
		
	}
	
	private static void createFiles() throws Exception{
		File dataDir = new File(dbexCommonContext.getDataDirName());
		if(!dataDir.exists()){
			dataDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + dataDir.getCanonicalPath() + " ] created.");
			}
		} 
		File appDataDir = new File(dbexCommonContext.getApplicationDataDir());
		if(!appDataDir.exists()){
			appDataDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + appDataDir.getCanonicalPath() + " ] created.");
			}
		} 
		File historyDataDir = new File(DbexCommonConstants.LOCAL_HISTORY_PATH);
		if(!historyDataDir.exists()){
			historyDataDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + historyDataDir.getCanonicalPath() + " ] created.");
			}
		} 
		File userDir = new File(DbexCommonConstants.USER_DATA_PATH);
		if(!userDir.exists()){
			userDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + userDir.getCanonicalPath() + " ] created.");
			}
		} 
		
		File connPropsFile = new File(dbexCommonContext.getConnectionConfigFileName());
		if(!connPropsFile.exists()){
			connPropsFile.createNewFile();
			if(logger.isDebugEnabled()){
				logger.debug("File [ " + connPropsFile.getCanonicalPath() + " ] created.");
			}
		}
	}

}
