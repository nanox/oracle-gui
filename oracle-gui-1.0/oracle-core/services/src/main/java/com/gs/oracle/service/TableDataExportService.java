/**
 * 
 */
package com.gs.oracle.service;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.enums.TableDataExportTypeEnum;

/**
 * @author sabuj.das
 *
 */
public interface TableDataExportService {

	public boolean exportData(String schemaName, String tableName, TableDataExportTypeEnum exportTypeEnum,
			String outputFileName, String exportQuery) throws ApplicationException;
}
