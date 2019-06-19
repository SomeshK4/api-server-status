package com.skidos.server.status.lambda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.skidos.server.status.json.handler.JsonHandler;
import com.skidos.server.status.model.ServerStatus;

/**
 * Hello world!
 *
 */
public class ServerStatusHandler implements RequestHandler<Integer, String> {

	public String handleRequest(Integer input, Context context) {
		LambdaLogger logger = context.getLogger();
        logger.log("Received : " + input);
        ServerStatus status = new ServerStatus();
        String json = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");  
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");  
        	Statement stmt=con.createStatement();  
        	ResultSet rs=stmt.executeQuery("select * from server_status");  
        	while(rs.next()) {
        		status.setStatus(rs.getString("status"));
        	}
			json = JsonHandler.toJSON(status);
		} catch (JsonProcessingException e) {
			 logger.log("Error while processing json : " + e);
		} catch (ClassNotFoundException e) {
			 logger.log("Error while db connection : " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return json;
	}
}
