/**
 * 
 */
package com.gs.dbex.historyMgr.data;

import java.io.File;
import java.util.List;

import com.gs.dbex.common.DbexCommonContext;
import com.gs.dbex.historyMgr.ApplicationDataHistoryMgr;
import com.gs.dbex.integration.xmlbeans.ConnectionPropertiesBodGenerator;
import com.gs.dbex.integration.xmlbeans.ConnectionPropertiesXmlTransformer;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.utils.io.FileRWUtil;
import com.gs.utils.text.StringUtil;

/**
 * @author sabuj.das
 *
 */
public class ApplicationDataHistoryMgrImpl implements ApplicationDataHistoryMgr {

	private static DbexCommonContext dbexCommonContext = DbexCommonContext.getInstance();
	
	private ConnectionPropertiesBodGenerator connectionPropertiesBodGenerator;
	private ConnectionPropertiesXmlTransformer connectionPropertiesXmlTransformer;
	
	public ApplicationDataHistoryMgrImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ConnectionPropertiesBodGenerator getConnectionPropertiesBodGenerator() {
		return connectionPropertiesBodGenerator;
	}

	public void setConnectionPropertiesBodGenerator(
			ConnectionPropertiesBodGenerator connectionPropertiesBodGenerator) {
		this.connectionPropertiesBodGenerator = connectionPropertiesBodGenerator;
	}

	public ConnectionPropertiesXmlTransformer getConnectionPropertiesXmlTransformer() {
		return connectionPropertiesXmlTransformer;
	}

	public void setConnectionPropertiesXmlTransformer(
			ConnectionPropertiesXmlTransformer connectionPropertiesXmlTransformer) {
		this.connectionPropertiesXmlTransformer = connectionPropertiesXmlTransformer;
	}



	/**
	 * This API Loads all the ConnectionProperties into a List from predefined file.
	 */
	public List<ConnectionProperties> loadAllConnectionProperties() {
		return loadAllConnectionProperties(dbexCommonContext.getConnectionConfigFileName());
	}
	
	/**
	 * This API Loads all the ConnectionProperties into a List from a given file.
	 */
	public List<ConnectionProperties> loadAllConnectionProperties(String fileName) {
		if(!StringUtil.hasValidContent(fileName))
			return null;
		String xmlText = FileRWUtil.getContents(new File(fileName));
		return getConnectionPropertiesXmlTransformer().readAllConnectionProperties(xmlText);
	}

	/**
	 * Reads a ConnectionProperties by connection name.
	 */
	public ConnectionProperties getConnectionProperties(String connectionName) {
		return null;
	}

}
