/**
 * 
 */
package com.gs.oracle.connection;

import java.util.Comparator;

/**
 * @author sabuj.das
 *
 */
public class ConnectionPropComparator implements
		Comparator<ConnectionProperties> {

	public int compare(ConnectionProperties o1, ConnectionProperties o2) {
		if(o1.getDisplayOrder() < o2.getDisplayOrder()){
			return -1;
		}else if(o1.getDisplayOrder() > o2.getDisplayOrder()){
			return 1;
		} 
		return 0;
	}

}
