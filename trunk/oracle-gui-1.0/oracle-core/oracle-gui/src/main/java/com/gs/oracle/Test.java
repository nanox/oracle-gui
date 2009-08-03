package com.gs.oracle;

import javax.swing.UIManager;

import com.gs.oracle.frame.OracleGuiMainFrame;

public class Test {

	/**
	 * TODO: Delete this class at end
	 * @param args
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            
        }
		OracleGuiMainFrame frame = new OracleGuiMainFrame();
		frame.setVisible(true);
	}

}
