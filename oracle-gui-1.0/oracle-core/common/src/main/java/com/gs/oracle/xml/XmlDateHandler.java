/**
 * 
 */
package com.gs.oracle.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.exolab.castor.mapping.FieldHandler;
import org.exolab.castor.mapping.ValidityException;

/**
 * @author Sabuj Das
 *
 */
public class XmlDateHandler implements FieldHandler{

	private static final String FORMAT = "yyyy-MM-dd";

    /**
     * Creates a new MyDateHandler instance
     */
    public XmlDateHandler() {
        
    }

    /**
     * Returns the value of the field from the object.
     *
     * @param object The object
     * @return The value of the field
     * @throws IllegalStateException The Java object has changed and
     *  is no longer supported by this handler, or the handler is not
     *  compatible with the Java object
     */
    public Object getValue(final Object object) throws IllegalStateException {
        Root root = (Root)object;
        Date value = root.getDate();
        if (value == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
        Date date = (Date)value;
        return formatter.format(date);
    }


    /**
     * Sets the value of the field on the object.
     *
     * @param object The object
     * @param value The new value
     * @throws IllegalStateException The Java object has changed and
     *  is no longer supported by this handler, or the handler is not
     *  compatible with the Java object
     * @throws IllegalArgumentException The value passed is not of
     *  a supported type
     */
    public void setValue(Object object, Object value) 
       throws IllegalStateException, IllegalArgumentException {
       
        Root root = (Root)object;
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
        Date date = null;
        try {
            date = formatter.parse((String)value);
        }
        catch(ParseException px) {
            throw new IllegalArgumentException(px.getMessage());
        }
        root.setDate(date);
    }


    /**
     * Creates a new instance of the object described by this field.
     *
     * @param parent The object for which the field is created
     * @return A new instance of the field's value
     * @throws IllegalStateException This field is a simple type and
     *  cannot be instantiated
     */
    public Object newInstance(Object parent) throws IllegalStateException {
        //-- Since it's marked as a string...just return null,
        //-- it's not needed.
        return null;
    }


    /**
     * Sets the value of the field to a default value.
     *
     * Reference fields are set to null, primitive fields are set to
     * their default value, collection fields are emptied of all
     * elements.
     *
     * @param object The object
     * @throws IllegalStateException The Java object has changed and
     *  is no longer supported by this handler, or the handler is not
     *  compatible with the Java object
     */
    public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
        ((Root)object).setDate(null);
    }

	@Override
	public void checkValidity(Object arg0) throws ValidityException,
			IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	
}
