package eg.com.dataload.service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import eg.com.dataload.base.DataBaseEnvironmentErrorCodes;
import eg.com.dataload.base.DataBaseEnvironmentException;
import eg.com.dataload.model.env.Database;
import eg.com.dataload.model.env.Env;
import eg.com.dataload.repository.env.EnvRepository;
import eg.com.dataload.util.CommonUtil;

@Service
public class DataBaseEnvService {

	@Autowired
	EnvRepository envRepository;

	Map<String, Map<String, DataSource>> cachedDataSource;

	ReentrantLock lock = new ReentrantLock();

	@PostConstruct
	public void onInit() throws Exception {
		List<Env> allConfiguaredEnvironments = getConfiguaredEnvironments();
		if (!CommonUtil.isEmptyCollection(allConfiguaredEnvironments)) {
			envRepository.save(allConfiguaredEnvironments);
		}
	}

	public List<Env> getConfiguaredEnvironments() throws Exception {
		List<Env> envs = null;
		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("env.yml")) {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			Env[] envArr = mapper.readValue(in, Env[].class);
			if (!CommonUtil.isEmptyArray(envArr)) {
				envs = Arrays.asList(envArr);
				for (Env e : envs) {
					for (Database db : e.getDatabase()) {
						db.setEnvironment(e);
						db.setInitialDeff(true);
					}
				}
			}
		}
		return envs;
	}

	public DataSource getDataSource(String envName, String databaseName) throws DataBaseEnvironmentException {

		Database database = getDatabaseDefenition(envName, databaseName);
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setUrl(database.getConnectionString());
		dataSource.setUsername(database.getUsername());
		dataSource.setPassword(database.getPassword());
		dataSource.setMaxActive(database.getMaxConnections());
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		return dataSource;

	}

	public DataSource getDataSource(Database database) throws DataBaseEnvironmentException {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setUrl(database.getConnectionString());
		dataSource.setUsername(database.getUsername());
		dataSource.setPassword(database.getPassword());
		dataSource.setMaxActive(database.getMaxConnections());
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		return dataSource;

	}

	public Database getDatabaseDefenition(String envName, String databaseName) throws DataBaseEnvironmentException {
		Env env = envRepository.findOne(envName);
		if (env == null) {
			throw new DataBaseEnvironmentException(DataBaseEnvironmentErrorCodes.NoEnvExists, "environment name [" + envName + "]", null);
		}

		Database database = null;
		if (!CommonUtil.isEmptyCollection(env.getDatabase())) {
			for (Database db : env.getDatabase()) {
				if (db.getDatabaseName().equalsIgnoreCase(databaseName)) {
					database = db;
					break;
				}
			}
		}

		if (database == null) {
			throw new DataBaseEnvironmentException(DataBaseEnvironmentErrorCodes.NoDataBaseExists, "environment name [" + envName + "], database name ["
					+ databaseName + "]", null);
		}
		return database;
	}

	public Map<String, DataSource> getEnvDataSources(String envName) throws DataBaseEnvironmentException{
		Env env = envRepository.findOne(envName);
		if(env == null){
			throw new DataBaseEnvironmentException(DataBaseEnvironmentErrorCodes.NoEnvExists, "environment name ["+envName+"]", null);
		}
		
		
		if(!CommonUtil.isEmptyCollection(env.getDatabase())){
			Map<String, DataSource> _return = new HashMap<String, DataSource>();
			for(Database db:env.getDatabase()){
				_return.put(db.getDatabaseName(), getDataSource(db));
			}
			return _return;
		}
		
		throw new DataBaseEnvironmentException(DataBaseEnvironmentErrorCodes.EmptyEnv, "environment name [" + envName + "] has no Database defined ", null);
		
	}
}
