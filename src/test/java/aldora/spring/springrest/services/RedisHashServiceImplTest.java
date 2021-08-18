package aldora.spring.springrest.services;

import aldora.spring.springrest.SpringRestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringRestApplication.class})
class RedisHashServiceImplTest {
    @Autowired
    @Qualifier("hashOperations")
    RedisService redisService;

    private final String redisKey = "redistest";

    @Test
    void put() {
        redisService.put("redistest", "WAWA", "KAKA");
        assertEquals("KAKA", redisService.get(redisKey, "WAWA"));
    }
}