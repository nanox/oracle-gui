package com.gs.dbex.service.impl;

import java.util.Set;

import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Schema;
import com.gs.dbex.service.DatabaseMetadataService;

public class DatabaseMetadataServiceImpl implements DatabaseMetadataService {

	public Set<Schema> getSchemaDetails(
			ConnectionProperties connectionProperties) {
		System.out.println("DatabaseMetadataServiceImpl");
		return null;
	}

}
