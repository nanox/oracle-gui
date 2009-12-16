/** ***************************************************************************
 *		Oracle GUI	
 *	
 *	File	: XmlRWUtils.java
 *	Type	: com.gs.oracle.io.XmlRWUtils.java
 *	Date	: Dec 9, 2009	1:30:30 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.oracle.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

/**
 * @author Sabuj Das
 *
 */
public class XmlRWUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T readUsingCastor(File inputXml, InputStream mappingStream){
		T data = null;
		Mapping mapping = new Mapping();
		try {
			InputSource iso = new InputSource();
			iso.setByteStream(mappingStream);
			mapping.loadMapping(iso);
			Reader reader = new FileReader(inputXml);
			Unmarshaller unmarshaller = new Unmarshaller();
			unmarshaller.setMapping(mapping);
			
			data = (T) unmarshaller.unmarshal(reader);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@SuppressWarnings("unchecked")
	public static <T> T readUsingCastor(File inputXml, File mappingFile){
		try {
			return (T)readUsingCastor(inputXml, new FileInputStream(mappingFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> void writeUsingCastor(File outputXml, InputStream mappingFileInputStream, T data){
		Mapping mapping = new Mapping();
		try {
			InputSource iso = new InputSource();
			iso.setByteStream(mappingFileInputStream);
			mapping.loadMapping(iso);
			
			OutputStream os = new FileOutputStream(outputXml);
			
			Marshaller marshaller = new Marshaller(new OutputStreamWriter(os));
			marshaller.setMapping(mapping);
			marshaller.marshal(data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> void writeUsingCastor(File outputXml, File mappingFile, T data){
		try {
			writeUsingCastor(outputXml, new FileInputStream(mappingFile), data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
