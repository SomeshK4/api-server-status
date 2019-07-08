package com.skidos.server.status.lambda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.skidos.server.status.lambda.db.configuration.DatabaseConfiguration;
import com.skidos.server.status.lambda.model.ServerStatusRequest;
import com.skidos.server.status.lambda.model.ServerStatusResponse;
import com.skidos.server.status.lambda.validator.ServerStatusRequestValidator;

/**
 * Handles the request and return the server status as online/offline
 *
 */
public class ServerStatusHandler implements RequestHandler<ServerStatusRequest, ServerStatusResponse> {
	
	private static final String DEFAULT_LANGUAGE = "en";

	public ServerStatusResponse handleRequest(ServerStatusRequest statusRequest, Context context) {
		LambdaLogger logger = context.getLogger();
		String language = DEFAULT_LANGUAGE;
		if(ServerStatusRequestValidator.validateRequest(statusRequest.getLanguage())) {
			language = statusRequest.getLanguage();
		}
        logger.log("Request received to get the server status corresponding to language  " + language);
        ServerStatusResponse status = new ServerStatusResponse();
        status.setStatus("online");
        try {
			Connection connection = DatabaseConfiguration.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select email from user limit 1");
			while(rs.next()) {
				status.setMessage(rs.getString("email"));
			}
		} catch (SQLException ex) {
			logger.log("Error occured while establishing the connection with database "+ex);
		}
        return status;
	}
}
