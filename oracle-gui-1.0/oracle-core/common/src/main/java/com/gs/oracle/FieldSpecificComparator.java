/**
 * 
 */
package com.gs.oracle;


import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * @author Sabuj Das
 *
 */
public class FieldSpecificComparator<T, V extends Comparable<V>> implements
		Comparator<T> {

	private String fieldName;
	
	public FieldSpecificComparator(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public int compare(T o1, T o2) {
		if(o1 == null || o2 == null){
			return 0;
		}
		Class<T> clazz = (Class<T>) o1.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		V v1 = null, v2 = null;
		String getterMethodName = "get" + getFieldName();
		for (Method method : methods) {
			if(method.getName().startsWith("get")){
				if(method.getName().equalsIgnoreCase(getterMethodName)){
					try{
						v1 = (V) method.invoke(o1, null);
						v2 = (V) method.invoke(o2, null);
					}catch(Exception e){
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		if(v1 != null && v2 != null){
			return v1.compareTo(v2);
		}
		return 0;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
