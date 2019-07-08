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
        /*config.setJdbcUrl("jdbc:mysql://mysql.skidos.com:3306/skidos_dev");
        config.setUsername("api");
        config.setPassword("AP!$k1d0s");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("maximumPoolSize", 50);*/
        ds = new HikariDataSource(config);
        System.out.println("Completed setup of database configuration ::: ");
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
     
    

}
