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
import com.gs.oracle.grabber.OracleDbGrabber;
import com.gs.oracle.model.ForeignKey;
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
		
		Table currentTable = dbGrabber.grabTable(connection, schemaName, tableName);
		if(currentTable == null){
			return null;
		}
		dependency.setCurrentTable(currentTable);
		Set<TableRelation> relationSet = new HashSet<TableRelation>();
		
		List<ForeignKey> relatedKeys = new ArrayList<ForeignKey>();
		if(currentTable.getImportedKeys() != null){
			relatedKeys.addAll(currentTable.getImportedKeys());
		}
		if(currentTable.getExportedKeys() != null){
			relatedKeys.addAll(currentTable.getExportedKeys());
		}
		
		
		
		dependency.setTableRelationSet(relationSet);
		
		return dependency;
	}

}
