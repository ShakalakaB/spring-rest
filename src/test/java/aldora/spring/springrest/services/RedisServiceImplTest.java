package aldora.spring.springrest.services;

import aldora.spring.springrest.SpringRestApplication;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringRestApplication.class})
class RedisServiceImplTest {
    @Autowired
    RedisService redisService;

    @Test
    void boundHashOpsPut() {
        redisService.boundHashOpsPut("redistest", "WAWA", "KAKA");
        assertEquals("KAKA", redisService.boundHashOpsGet("redistest", "WAWA"));
    }

    @Test
    void boundHashOpsPutAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("santa", "claus");
        map.put("kk", "aldora");
        map.put("lemon", "tree");
        redisService.boundHashOpsPutAll("redistest", map);
        assertEquals("aldora", redisService.boundHashOpsGet("redistest", "kk"));

        List<Object> multiGet = redisService.boundHashOpsMultiGet( "redistest", Lists.newArrayList("santa", "kk", "lemon"));
        assertEquals(3, multiGet.size());

        List<Object> results = redisService.boundHashOpsValues( "redistest");

        Map<Object, Object> entries = redisService.boundHashOpsEntries("redistest");
    }
}