/**
 * 
 */
package com.gs.oracle.accesscontrol;

import java.util.HashMap;
import java.util.Map;

import com.gs.oracle.model.Column;
import com.gs.oracle.model.Table;

/**
 * @author Sabuj Das
 *
 */
public class Authorization {

	private Map<Table, TablePrivilege> tablePrivilegeMap; 
	private Map<Column, ColumnPrivilege> columnPrivilegeMap;
	
	public Authorization() {
		tablePrivilegeMap = new HashMap<Table, TablePrivilege>();
		columnPrivilegeMap = new HashMap<Column, ColumnPrivilege>();
	}
	
	public <T> boolean hasAccess(T t, String permissionName){
		if(t instanceof Table){
			return hasAccessForTable((Table) t, permissionName);
		}else if(t instanceof Column){
			return hasAccessForColumn((Column) t, permissionName);
		}
		return true;
	}
	
	public boolean hasAccessForTable(Table t, String permissionName){
		return true;
	}
	
	public boolean hasAccessForColumn(Column c, String permissionName){
		return true;
	}
}
