package com.skidos.server.status.lambda.validator;

/**
 * 
 * @author someshKumar
 *
 */
public final class ServerStatusRequestValidator {
	
	
	public static boolean validateRequest(String language) {
		if(null != language && !language.isEmpty()) {
			return true;
		}
		return false;
	}

}
