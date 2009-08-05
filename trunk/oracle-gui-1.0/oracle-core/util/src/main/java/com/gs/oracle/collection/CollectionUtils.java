/**
 * 
 */
package com.gs.oracle.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for manipulation of collections
 * 
 * 
 * @author sabuj.das
 * 
 */
public class CollectionUtils {

	/**
	 * This API creates a list of list from a given list. This uses the
	 * maxSplitSize to make sublist from the given list and add the list to the
	 * output list.
	 * 
	 * @param <T>
	 *            The type of the Object the list can contain
	 * @param fromList
	 *            The input list which needs to split and create new List of
	 *            list
	 * @param maxSplitSize
	 *            maximum size of each split
	 * @return List of List<code>&lt;T&gt;</code>
	 * @author sabuj.das
	 */
	public static <T> List<List<T>> split(List<T> fromList, int maxSplitSize) {
		int n = fromList.size(); // total size of the list
		List<List<T>> toList = new ArrayList<List<T>>();
		// split the list by the maxSplitSize
		for (int i = 0; i < n; i += maxSplitSize) {
			if ((i + maxSplitSize) > n) {
				toList.add(fromList.subList(i, n));
			} else {
				toList.add(fromList.subList(i, (i + maxSplitSize)));
			}
		}
		return toList;
	}

	public static void main(String[] args) {
		List<Integer> iList = new ArrayList<Integer>();
		for (int i = 0; i < 115; i++) {
			iList.add(i);
		}
		List<List<Integer>> lll = split(iList, 13);
		for (List<Integer> list : lll) {
			System.out.println(list.size() + "\t ::> " + list);
		}

	}
}
