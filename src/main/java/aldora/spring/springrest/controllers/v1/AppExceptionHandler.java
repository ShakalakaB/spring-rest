package aldora.spring.springrest.controllers.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("error: ", e);
        return "Got you!";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404Exception(Exception e) {
        log.error("error: ", e);
        return "404!";
    }
}
