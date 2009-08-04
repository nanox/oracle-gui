/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: OracleGuiDelegate.java
 *	Type	: com.gs.oracle.command.OracleGuiDelegate.java
 *	Date	: Aug 4, 2009	10:54:16 AM
 *
 *	Author	: Green Moon
 *
 *	
 *****************************************************************************/
package com.gs.oracle.command;

import com.gs.oracle.service.OracleDatabaseService;
import com.gs.oracle.service.impl.OracleDatabaseServiceImpl;

/**
 * @author sabuj.das
 *
 */
public class OracleGuiDelegate {

	private OracleDatabaseService databaseService;
	
	public OracleGuiDelegate() {
		databaseService = new OracleDatabaseServiceImpl();
	}
	
	
}
