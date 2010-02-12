/**
 * 
 */
package com.gs.oracle.context;

import java.util.HashMap;
import java.util.Map;

import com.gs.oracle.model.Database;

/**
 * @author sabuj.das
 *
 */
public class OracleGuiResourceContext {

	private static OracleGuiResourceContext instance = null;
	
	private OracleGuiResourceContext() {
		
	}

	public static OracleGuiResourceContext getInstance() {
		if(instance == null)
			instance = new OracleGuiResourceContext();
		return instance;
	}
	
	/* ------   Static resources, to be available for full application   ---- */
	public Map<String, Database> connectedDatabaseMap = new HashMap<String, Database>();
	
	public int internalFrameCount = 0;
	
}
