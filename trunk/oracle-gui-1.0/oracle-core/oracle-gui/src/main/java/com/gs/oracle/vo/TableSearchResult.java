/**
 * 
 */
package com.gs.oracle.vo;

import java.sql.Timestamp;

import com.gs.oracle.model.ColumnHeader;

/**
 * @author sabuj.das
 *
 */
public class TableSearchResult extends SearchResult {

	private String tableSpaceName;
	private String clusterName;
	private Long numberOfRows;
	private Character cacheIndicator;
	private String tableLock;
	private Character nested;
	
	
	@Override
	@ColumnHeader(title="Table Name", index=0)
	public String getObjectName() {
		return super.getObjectName();
	}
	
	@Override
	@ColumnHeader(title="Owner Name", index=1)
	public String getOwnerName() {
		return super.getOwnerName();
	}
	
	@Override
	@ColumnHeader(title="Status", index=2)
	public String getStatus() {
		return super.getStatus();
	}
	@Override
	@ColumnHeader(title="Date Created", index=3)
	public Timestamp getCreatedDate() {
		return super.getCreatedDate();
	}
	
	@Override
	@ColumnHeader(title="Is Temporary", index=4)
	public Character getTemporary() {
		return super.getTemporary();
	}
	
	@Override
	public Character getGranted() {
		return super.getGranted();
	}
	@Override
	public Timestamp getLastDDLDate() {
		return super.getLastDDLDate();
	}
	
	
	@Override
	public Character getSecondary() {
		return super.getSecondary();
	}
	
	
	
	public String getTableSpaceName() {
		return tableSpaceName;
	}
	public String getClusterName() {
		return clusterName;
	}
	public Long getNumberOfRows() {
		return numberOfRows;
	}
	public Character getCacheIndicator() {
		return cacheIndicator;
	}
	public String getTableLock() {
		return tableLock;
	}
	public Character getNested() {
		return nested;
	}
	public void setTableSpaceName(String tableSpaceName) {
		this.tableSpaceName = tableSpaceName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public void setNumberOfRows(Long numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	public void setCacheIndicator(Character cacheIndicator) {
		this.cacheIndicator = cacheIndicator;
	}
	public void setTableLock(String tableLock) {
		this.tableLock = tableLock;
	}
	public void setNested(Character nested) {
		this.nested = nested;
	}
	
	
	
}
