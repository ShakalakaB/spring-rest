package aldora.spring.springrest.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Getter
public class EntityEvent extends ApplicationEvent {
    private final String eventName;

    public EntityEvent(Object source, String eventName) {
        super(source);
        this.eventName = eventName;
    }
}
