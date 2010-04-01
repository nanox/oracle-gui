/**
 * 
 */
package com.gs.dbex.application.util;

import java.io.File;
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

import sun.misc.IOUtils;

import com.gs.dbex.model.cfg.ConnectionProperties;

/**
 * @author sabuj.das
 *
 */
public class ConnectionPropertiesRWUtil {
	
	private static ConnectionPropertiesRWUtil instance;
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private ConnectionPropertiesCatalog catalog; 
	private Reader reader;
	private Mapping mapping;
	
	private ConnectionPropertiesRWUtil() {
		catalog = new ConnectionPropertiesCatalog();
		try{
			InputStream is = IOUtils.getResourceAsStream(OracleGuiConstants.CONN_PROPERTIES_MAPPING_FILE);
			mapping = new Mapping();
			InputSource iso = new InputSource();
			iso.setByteStream(is);
			mapping.loadMapping(iso);
			
			File file = IOUtils.mkfile(OracleGuiConstants.CONNECTION_DATA_FILE);
			reader = new FileReader(file);
			unmarshaller = new Unmarshaller();
			unmarshaller.setMapping(mapping);
			
		} catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	public static ConnectionPropertiesRWUtil getInstance(){
		if(instance == null){
			instance = new ConnectionPropertiesRWUtil();
		}
		return instance;
	}
	
	public void saveConnectionProperties(ConnectionProperties connectionProperties){
		if(connectionProperties == null){
			return;
		}
		catalog.add(connectionProperties);
		
		try {
			File file = IOUtils.mkfile(OracleGuiConstants.CONNECTION_DATA_FILE);
			OutputStream os = new FileOutputStream(file);
			marshaller = new Marshaller(new OutputStreamWriter(os));
			marshaller.setMapping(mapping);
			
			marshaller.marshal(catalog);
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ConnectionPropertiesCatalog getCatalog(){
		try {
			catalog = (ConnectionPropertiesCatalog)
				unmarshaller.unmarshal(reader);
			if(catalog == null){
				catalog = new ConnectionPropertiesCatalog();
			}
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalog;
	}
	
	public void saveCatalog(ConnectionPropertiesCatalog catalog){
		if(catalog == null){
			return;
		}
		catalog.setSaved();
		try {
			File file = IOUtils.mkfile(OracleGuiConstants.CONNECTION_DATA_FILE);
			OutputStream os = new FileOutputStream(file);
			marshaller = new Marshaller(new OutputStreamWriter(os));
			marshaller.setMapping(mapping);
			
			marshaller.marshal(catalog);
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConnectionProperties getConnectionProperties(String connName){
		return getCatalog().getByName(connName);
	}
	
	public void updateConnectionProperties(ConnectionProperties connectionProperties){
		
	}
}
