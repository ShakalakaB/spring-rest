package aldora.spring.springrest.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher publisher;

    public void publishEvent(final String name) {
        publisher.publishEvent(new EntityEvent(this, name));
    }
}
