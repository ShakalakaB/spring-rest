package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.event.EntityEvent;
import aldora.spring.springrest.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {
    private final EventPublisher eventPublisher;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        eventPublisher.publishEvent("hello");
        return "ok";
    }
}
