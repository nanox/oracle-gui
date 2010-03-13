/**
 * 
 */
package com.gs.dbex.launcher;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[]{
						"/context/dbex-launcher-context.xml"
				}
			);
		DatabaseExplorerFrame explorerFrame = (DatabaseExplorerFrame) applicationContext.getBean("DatabaseExplorerFrame");
		if(explorerFrame != null){
			//explorerFrame.setSize(200,200);
			explorerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			explorerFrame.setVisible(true);
		}
	}

}
