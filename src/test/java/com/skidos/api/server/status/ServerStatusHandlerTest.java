package com.skidos.api.server.status;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.skidos.server.status.json.handler.JsonHandler;
import com.skidos.server.status.lambda.ServerStatusHandler;
import com.skidos.server.status.model.ServerStatus;

/**
 * Unit test.
 */
public class ServerStatusHandlerTest {
	private Context context;

	@Before
	public void setUp() throws Exception {
		LambdaLogger lambdaLogger = mock(LambdaLogger.class);
		context = mock(Context.class);
		when(context.getLogger()).thenReturn(lambdaLogger);
	}

	@Test
	public void testHandleRequest() throws Exception {
		ServerStatusHandler helloWorldHandlerHandler = new ServerStatusHandler();
		ServerStatus serverStatus = JsonHandler.fromJSON(helloWorldHandlerHandler.handleRequest(-1, context));
		Assert.assertEquals("offline", serverStatus.getStatus());
	}
}
