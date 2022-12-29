package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.event.EventPublisher;
import aldora.spring.springrest.services.MysqlLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class DemoController {
    private final EventPublisher eventPublisher;
    private final MysqlLockService paymentService;

    @GetMapping("/event")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        eventPublisher.publishEvent("hello");
        return "ok";
    }

    @GetMapping("/transaction/{value}")
    @ResponseStatus(HttpStatus.OK)
    public String testTransaction(@PathVariable Integer value) {
        paymentService.execute(value);
        return "ok";
    }

    @GetMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    public String executeTransaction() {
        paymentService.executeTransaction();
        return "ok";
    }
}
