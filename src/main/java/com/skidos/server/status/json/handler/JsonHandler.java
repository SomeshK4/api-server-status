package com.skidos.server.status.json.handler;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skidos.server.status.model.ServerStatus;

public class JsonHandler {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
	public static String toJSON(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
	
	public static ServerStatus fromJSON(String json) throws JsonParseException, JsonMappingException, IOException{
		ServerStatus serverStatus = objectMapper.readValue(json, ServerStatus.class);
		return serverStatus;
	}
}
