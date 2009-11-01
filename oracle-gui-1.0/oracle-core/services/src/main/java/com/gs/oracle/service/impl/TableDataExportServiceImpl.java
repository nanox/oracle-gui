/**
 * 
 */
package com.gs.oracle.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gs.oracle.common.StringUtil;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.enums.TableDataExportTypeEnum;
import com.gs.oracle.io.IOUtils;
import com.gs.oracle.service.TableDataExportService;

/**
 * @author sabuj.das
 *
 */
public class TableDataExportServiceImpl implements TableDataExportService{

	private ConnectionProperties connectionProperties;
	private TableDataExportHandler exportHandler;
	
	
	public TableDataExportServiceImpl(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
		exportHandler = new TableDataExportHandler(connectionProperties);
	}



	/**
	 * @return the connectionProperties
	 */
	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}



	/**
	 * @param connectionProperties the connectionProperties to set
	 */
	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}



	@Override
	public boolean exportData(String schemaName, String tableName, TableDataExportTypeEnum exportTypeEnum,
			String outputFileName, String exportQuery) {
		if(!StringUtil.hasValidContent(exportQuery) 
				|| !StringUtil.hasValidContent(outputFileName)
				|| exportTypeEnum == null)
			return false;
		File outputFile = null;
		exportHandler.setSchemaName(schemaName);
		exportHandler.setTableName(tableName);
		if(TableDataExportTypeEnum.CSV.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.CSV.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.CSV.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
			exportHandler.exportToTEXT(exportQuery, outputFile, true, ',');
		} else if(TableDataExportTypeEnum.EXCEL.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.EXCEL.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.EXCEL.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
		} else if(TableDataExportTypeEnum.HTML.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.HTML.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.HTML.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
			exportHandler.exportToHTML(exportQuery, outputFile);
		} else if(TableDataExportTypeEnum.INSERT_STATEMENT.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.INSERT_STATEMENT.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.INSERT_STATEMENT.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
			exportHandler.exportToInsertStatement(exportQuery, outputFile);
		} else if(TableDataExportTypeEnum.SQL_LOADER.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.SQL_LOADER.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.SQL_LOADER.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
			
		} else if(TableDataExportTypeEnum.TEXT.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.TEXT.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.TEXT.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
			exportHandler.exportToTEXT(exportQuery, outputFile, false, '\t');
		} else if(TableDataExportTypeEnum.XML.getCode() == exportTypeEnum.getCode()){
			if(!outputFileName.toLowerCase().endsWith(TableDataExportTypeEnum.XML.getExtension())){
				outputFileName += outputFileName + TableDataExportTypeEnum.XML.getExtension();
			}
			outputFile = IOUtils.mkfile(outputFileName);
			exportHandler.exportToXML(exportQuery, outputFile);
		} 
		
		return false;
	}
	


}
