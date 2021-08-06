package aldora.spring.springrest.controllers.v1;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logging")
public class LoggingController {
    private final static Logger logger = LoggerFactory.getLogger(LoggingController.class);

    private final static org.apache.logging.log4j.Logger nativeLogger = LogManager.getLogger(LoggingController.class);

    @GetMapping
    public String slf4jLogging() {
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

        return "slf4j log completed";
    }

    @GetMapping("/nativelog")
    public String nativeLogging() {
        nativeLogger.trace("trace log, printed by Log4j2 without passing through SLF4J");
        nativeLogger.debug("debug log, printed by Log4j2 without passing through SLF4J");
        nativeLogger.info("info log, printed by Log4j2 without passing through SLF4J");
        nativeLogger.warn("warn log, printed by Log4j2 without passing through SLF4J");
        nativeLogger.error("error log, printed by Log4j2 without passing through SLF4J");

        return "slf4j log completed";
    }
}
