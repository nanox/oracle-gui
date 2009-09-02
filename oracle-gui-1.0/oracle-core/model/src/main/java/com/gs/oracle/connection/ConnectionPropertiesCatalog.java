/**
 * 
 */
package com.gs.oracle.connection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author sabuj.das
 *
 */
public class ConnectionPropertiesCatalog implements Serializable{

	private SortedSet<ConnectionProperties> connectionPropertiesList;
	
	public ConnectionPropertiesCatalog() {
		connectionPropertiesList = new TreeSet<ConnectionProperties>();
	}

	public SortedSet<ConnectionProperties> getConnectionPropertiesList() {
		return connectionPropertiesList;
	}

	public void setConnectionPropertiesList(
			SortedSet<ConnectionProperties> connectionPropertiesList) {
		this.connectionPropertiesList = connectionPropertiesList;
	}
	
	public void add(ConnectionProperties p){
		if(p == null)
			return;
		for (ConnectionProperties prop : connectionPropertiesList) {
			prop.setDisplayOrder(prop.getDisplayOrder() + 1);
		}
		p.setDisplayOrder(1);
		connectionPropertiesList.add(p);
	}
	
	public ConnectionProperties getByName(String name){
		ConnectionProperties connectionProperties = null;
		
		for (ConnectionProperties p : connectionPropertiesList) {
			if(name.equalsIgnoreCase(p.getConnectionName())){
				connectionProperties = p;
				break;
			}
		}
		
		return connectionProperties;
	}
	
	public boolean isConnPropModified(){
		for (ConnectionProperties p : connectionPropertiesList) {
			if(p.getIsModified()){
				return true;
			}
		}
		return false;
	}
	
	public void setSaved(){
		for (ConnectionProperties p : connectionPropertiesList) {
			if(p.getIsModified()){
				p.setIsModified(false);
			}
		}
	}
}
