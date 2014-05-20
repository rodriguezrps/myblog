package com.myblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableMongoRepositories(basePackages = "com.myblog.repository")
public class MongoConfig extends AbstractMongoConfiguration {

	public final String MONGO_DB_NAME = "mongoDb.name";
	public final String MONGO_DB_HOST = "mongoDb.host";
	
	@Autowired
	private Environment env;
	
	@Override
	protected String getDatabaseName() {
		return env.getProperty(MONGO_DB_NAME);
	}

	@Override
	public Mongo mongo() throws Exception {
		Mongo mongo = new MongoClient(env.getProperty(MONGO_DB_HOST));
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
	}

}
