/**
 * 
 */
package com.gs.dbex.launcher;

import java.awt.EventQueue;
import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.dbex.application.context.ApplicationCommonContext;
import com.gs.dbex.application.frame.DatabaseExplorerFrame;
import com.gs.dbex.common.DbexCommonContext;
import com.gs.dbex.historyMgr.ApplicationDataHistoryMgr;
import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public class DatabaseExplorerLauncher {

	private static final Logger logger = Logger.getLogger(DatabaseExplorerLauncher.class);
	private static DbexCommonContext dbexCommonContext = DbexCommonContext.getInstance();
	private static ApplicationCommonContext applicationCommonContext = ApplicationCommonContext.getInstance();
	
	private ApplicationDataLoader applicationDataLoader;
	
	
	public DatabaseExplorerLauncher() {
		getApplicationDataLoader();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Launching Database Explorer.");
		
		logger.info("Creating Spring Beans");
		dbexCommonContext.applicationSpringContext = new ClassPathXmlApplicationContext(
				new String[] { "/context/dbex-launcher-context.xml"});
		
		DatabaseExplorerLauncher launcher = (DatabaseExplorerLauncher) dbexCommonContext.applicationSpringContext.getBean("databaseExplorerLauncher");
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
        	logger.info("Create files/folders.");
        	createFiles();
        }catch (Exception ex){
        	ex.printStackTrace();
        }
        logger.info("Initialize Context.");
        launcher.getApplicationDataLoader().populateInitialContext();
        DatabaseExplorerFrame explorerFrame = new DatabaseExplorerFrame();
		if(explorerFrame != null){
			explorerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			explorerFrame.setVisible(true);
		}
        
	}
	
	
	
	private static void createFiles() throws Exception{
		File profilesDir = new File(dbexCommonContext.getProfilesDirName());
		if(!profilesDir.exists()){
			profilesDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + profilesDir.getCanonicalPath() + " ] created.");
			}
		} 
		File userDir = new File(dbexCommonContext.getUserDataPath());
		if(!userDir.exists()){
			userDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + userDir.getCanonicalPath() + " ] created.");
			}
		} 
		
		
		File appDataDir = new File(dbexCommonContext.getApplicationDataDir());
		if(!appDataDir.exists()){
			appDataDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + appDataDir.getCanonicalPath() + " ] created.");
			}
		} 
		File historyDataDir = new File(dbexCommonContext.getLocalHistoryPath());
		if(!historyDataDir.exists()){
			historyDataDir.mkdir();
			if(logger.isDebugEnabled()){
				logger.debug("Directory [ " + historyDataDir.getCanonicalPath() + " ] created.");
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
	
	
	public ApplicationDataLoader getApplicationDataLoader() {
		return applicationDataLoader;
	}

	public void setApplicationDataLoader(ApplicationDataLoader applicationDataLoader) {
		this.applicationDataLoader = applicationDataLoader;
	}

	
}
