/**
 * 
 */
package com.gs.oracle.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gs.oracle.ApplicationException;
import com.gs.oracle.enums.ReadDepthEnum;
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.model.ExportedTableRelation;
import com.gs.oracle.model.ForeignKey;
import com.gs.oracle.model.ImportedTableRelation;
import com.gs.oracle.model.Table;
import com.gs.oracle.model.TableDependency;
import com.gs.oracle.model.TableRelation;
import com.gs.oracle.service.DependencyService;

/**
 * @author Green Moon
 *
 */
public class DependencyServiceImpl implements DependencyService {

	
	public DependencyServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public TableDependency generateTableDependency(Connection connection,
			String schemaName, String tableName) throws ApplicationException {
		OracleDbGrabber dbGrabber = new OracleDbGrabber();
		TableDependency dependency = new TableDependency();
		
		Table currentTable = dbGrabber.grabTable(connection, schemaName, tableName, ReadDepthEnum.DEEP);
		if(currentTable == null){
			return null;
		}
		dependency.setCurrentTable(currentTable);
		
		List<ForeignKey> importedKeys = currentTable.getImportedKeys();
		List<ForeignKey> exportedKeys = currentTable.getExportedKeys();
		List<ImportedTableRelation> importedRelations = new ArrayList<ImportedTableRelation>();
		List<ExportedTableRelation> exportedRelations = new ArrayList<ExportedTableRelation>();
		
		if(importedKeys != null){
			for (ForeignKey impKey : importedKeys) {
				ImportedTableRelation r = new ImportedTableRelation();
				r.setRelationTitle("Imported Relation");
				r.setRelationType("IMPORTED");
				r.setImportedKey(impKey);
				Table importedTable = dbGrabber.grabTable(connection, 
						impKey.getPkTableSchem(), 
						impKey.getPkTableName(), ReadDepthEnum.DEEP);
				r.setImportedTable(importedTable);
				r.setForeignColumnName(impKey.getPkColumnName());
				importedRelations.add(r);
			}
		}
		
		if(exportedKeys != null){
			for (ForeignKey expKey : exportedKeys) {
				ExportedTableRelation r = new ExportedTableRelation();
				r.setRelationTitle("Exported Relation");
				r.setRelationType("EXPORTED");
				r.setExportedKey(expKey);
				Table importedTable = dbGrabber.grabTable(connection, 
						expKey.getFkTableSchem(), 
						expKey.getFkTableName(), ReadDepthEnum.DEEP);
				r.setExportedTable(importedTable);
				r.setForeignColumnName(expKey.getFkColumnName());
				exportedRelations.add(r);
			}
		}
		
		dependency.setImportedRelations(importedRelations);
		dependency.setExportedRelations(exportedRelations);
		
		return dependency;
	}

}
