package aldora.spring.springrest.controllers.v1;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lombok/logging")
@Slf4j
// Comment any other Lombok logging annotation and uncomment this to work with Apache Commons Logging
//@CommonsLog

// Comment any other Lombok logging annotation and uncomment this to work directly with Log4j2
//@Log4j2
public class LombokLoggingController {
    @GetMapping
    public String logging() {
        log.trace("trace log");
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");

        return "slf4j log completed";
    }
}
