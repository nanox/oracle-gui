/**
 * 
 */
package com.gs.oracle.util;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

import com.gs.oracle.common.StringUtil;
import com.gs.oracle.comps.ExtensionFileFilter;

/**
 * @author sabuj.das
 *
 */
public class FileIOUtil {

	
	public static File openSingleFile(Component parent, ExtensionFileFilter fileFilter, Boolean isDir){
		File file = null;
		
		JFileChooser chooser = new JFileChooser(".");
		if(fileFilter != null)
			chooser.setFileFilter(fileFilter);
		chooser.setMultiSelectionEnabled(false);
		if(isDir != null){
			if(Boolean.TRUE == isDir)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}
		
		int option = chooser.showOpenDialog(parent);
		if(JFileChooser.APPROVE_OPTION == option){
			file = chooser.getSelectedFile();
		}
		
		return file;
	}
	
	public static File[] openMultipleFile(Component parent, ExtensionFileFilter fileFilter, Boolean isDir){
		File[] files = null;
		
		JFileChooser chooser = new JFileChooser(".");
		if(fileFilter != null)
			chooser.setFileFilter(fileFilter);
		chooser.setMultiSelectionEnabled(true);
		if(isDir != null){
			if(Boolean.TRUE == isDir)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}
		
		int option = chooser.showOpenDialog(parent);
		if(JFileChooser.APPROVE_OPTION == option){
			files = chooser.getSelectedFiles();
		}
		
		return files;
	}
	
	public static File browseToSaveFile(Component parent, String path, ExtensionFileFilter fileFilter, Boolean isDir){
		File file = null;
		
		JFileChooser chooser = null;
		if(StringUtil.hasValidContent(path)){
			chooser = new JFileChooser(path);
		} else {
			chooser = new JFileChooser(".");
		}
		if(fileFilter != null)
			chooser.setFileFilter(fileFilter);
		chooser.setMultiSelectionEnabled(false);
		if(isDir != null){
			if(Boolean.TRUE == isDir)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}
		
		int option = chooser.showSaveDialog(parent);
		if(JFileChooser.APPROVE_OPTION == option){
			file = chooser.getSelectedFile();
		}
		
		return file;
	}
}
