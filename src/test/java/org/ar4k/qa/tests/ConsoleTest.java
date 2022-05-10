package org.ar4k.qa.tests;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class ConsoleTest {

	private static final Logger logger = Logger.getLogger(ConsoleTest.class.getName());

	@Test
	@Order(1)
	public void checkConfigUpdate() {
		// TODO : completare con test sulla gestione dei file e variabili d'ambiente
		logger.info("ok");
	}

	@AfterEach
	public void cleanAgentInstance() throws Exception {
		logger.info("test completed");
	}

}
