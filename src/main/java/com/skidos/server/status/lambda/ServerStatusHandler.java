package com.skidos.server.status.lambda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try (Connection connection = DatabaseConfiguration.getConnection()){
			PreparedStatement pstmt = connection.prepareStatement("Select Message,Status from ServerStatus where language = ?");
			pstmt.setString(1, language);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				status.setStatus(rs.getString("Status"));
				status.setMessage(rs.getString("Message"));
			}
		} catch (SQLException ex) {
			logger.log("Error occured while establishing the connection with database " + ex);
			status.setMessage("Error while getting the status");
		}
        return status;
	}
}
