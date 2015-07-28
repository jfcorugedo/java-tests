package es.juan.test.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.junit.Test;

public class Log4j2Test {

    private static final Logger logger = LogManager.getLogger("HelloWorld");
    // private static final Logger logger = LogManager.getLogger

    @Test
    public void testLog4j2(){

        System.out.println("...Before");
        System.out.println();

        logger.info("Hello, this is an INFO message"); 
        logger.warn("Hello, this is an WARN  message");
        logger.fatal("Hello, this is a FATAL  message");
        logger.fatal("Hello, this is a FATAL  message ALSO, #2");
        logger.fatal("Hello, this is a FATAL  message ALSO, #3");
        logger.fatal("Hello, this is a FATAL  message ALSO, #4");
        logger.debug("Hello, this is a FATAL  message");
        logger.info("Hello, this is an INFO message");
        logger.info("Hello, this is an INFO message");
        System.out.println();
        System.out.println("...After");

    }

}