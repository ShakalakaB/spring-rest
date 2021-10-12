package aldora.spring.springrest.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityEventListener {
   @EventListener
    public void handleEntityEvent(EntityEvent event) {
        System.out.println("Got event: " + event.getEventName());
    }
}
