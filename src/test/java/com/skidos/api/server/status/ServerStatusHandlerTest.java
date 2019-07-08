package com.skidos.api.server.status;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.skidos.server.status.lambda.ServerStatusHandler;
import com.skidos.server.status.lambda.model.ServerStatusRequest;
import com.skidos.server.status.lambda.model.ServerStatusResponse;

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

	/**
	 * Test Handle Request
	 * @throws Exception
	 */
	@Test
	public void testHandleRequest() throws Exception {
		ServerStatusHandler helloWorldHandlerHandler = new ServerStatusHandler();
		ServerStatusRequest request = new ServerStatusRequest();
		request.setLanguage("en");
		ServerStatusResponse serverStatus = helloWorldHandlerHandler.handleRequest(request, context);
		Assert.assertEquals("online", serverStatus.getStatus());
	}
}
