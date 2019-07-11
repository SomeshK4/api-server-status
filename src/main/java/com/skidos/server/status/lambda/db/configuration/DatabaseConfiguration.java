package com.skidos.server.status.lambda.db.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author someshkumar
 *
 */
public class DatabaseConfiguration {
	
	private static String configFile = "db.properties";
	private static HikariConfig config;
    private static HikariDataSource ds;
    
    private DatabaseConfiguration() {
    	
    }
    
    static {
    	System.out.println("Started creating database configuration ::: ");
    	config =  new HikariConfig(configFile);
        ds = new HikariDataSource(config);
        System.out.println("Completed setup of database configuration ::: ");
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
     
    

}
