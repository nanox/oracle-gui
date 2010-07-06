/**
 * 
 */
package com.gs.dbex.launcher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	DatabaseExplorerFrame explorerFrame = new DatabaseExplorerFrame();
        		if(explorerFrame != null){
        			explorerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        			explorerFrame.setVisible(true);
        		}
            }
        });
		
	}

}
