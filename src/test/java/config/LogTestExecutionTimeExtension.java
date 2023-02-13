package config;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTestExecutionTimeExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
	
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
		//LOG.info("Starting test: " + context.getDisplayName());
		context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod())).put("startTime", Instant.now());
	}

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
    	Instant startTime = (Instant) context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod())).remove("startTime");
	  	Duration duration = Duration.between(startTime, Instant.now());
	  	LOG.info("Finished test: " + context.getDisplayName() + " in " + duration.toMillis() + "ms");
    }
}
