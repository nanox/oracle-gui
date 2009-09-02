/**
 * 
 */
package com.gs.oracle.castor;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import com.gs.oracle.OracleGuiConstants;
import com.gs.oracle.connection.ConnectionProperties;
import com.gs.oracle.connection.ConnectionPropertiesCatalog;
import com.gs.oracle.connection.DatabaseTypeEnum;
import com.gs.oracle.io.IOUtils;

/**
 * @author sabuj.das
 *
 */
public class ConnectionDataUtil {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionProperties p1  = new ConnectionProperties("POS_LOCAL_DEV");
		p1.setHostName("db001");
		p1.setPortNumber(1521);
		p1.setUserName("pos_user");
		p1.setPassword("pos_user");
		p1.setSavePassword(true);
		p1.setSid("BK53DV01");
		p1.setServiceName("");
		p1.setDatabaseName("POS_OWNER");
		p1.setDisplayOrder(0);
		
		ConnectionProperties p2  = new ConnectionProperties("POS_LOCAL_TEST");
		p2.setHostName("db001");
		p2.setPortNumber(1521);
		p2.setUserName("pos_user");
		p2.setPassword("pos_user");
		p2.setSavePassword(true);
		p2.setSid("BK53DV01");
		p2.setServiceName("");
		p2.setDatabaseName("POS_OWNER");
		p2.setDisplayOrder(1);
		ConnectionPropertiesCatalog cat = new ConnectionPropertiesCatalog();
		cat.getConnectionPropertiesList().add(p1);
		cat.getConnectionPropertiesList().add(p2);
		
		InputStream is = IOUtils.getResourceAsStream(OracleGuiConstants.CONN_PROPERTIES_MAPPING_FILE);
		Mapping mapping = new Mapping();
		try {
			InputSource iso = new InputSource();
			iso.setByteStream(is);
			mapping.loadMapping(iso);
			IOUtils.mkfile(OracleGuiConstants.CONNECTION_DATA_FILE);
			OutputStream os = new FileOutputStream(OracleGuiConstants.CONNECTION_DATA_FILE);
			Marshaller marshaller = new Marshaller(new OutputStreamWriter(os));
			marshaller.setMapping(mapping);
			marshaller.marshal(cat);
			Unmarshaller um = new Unmarshaller();
			um.setMapping(mapping);
			Reader reader = new FileReader(OracleGuiConstants.CONNECTION_DATA_FILE);
			ConnectionPropertiesCatalog c1 = (ConnectionPropertiesCatalog) um.unmarshal(reader);
			System.out.println(c1.getConnectionPropertiesList().size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
