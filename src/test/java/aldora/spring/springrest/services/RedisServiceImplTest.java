package aldora.spring.springrest.services;

import aldora.spring.springrest.SpringRestApplication;
import aldora.spring.springrest.api.v1.model.CategoryDTO;
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

    private final String redisKey = "redistest";

    @Test
    void boundHashOpsPut() {
        redisService.put("redistest", "WAWA", "KAKA");
        assertEquals("KAKA", redisService.get(redisKey, "WAWA"));
    }

    @Test
    void boundHashOpsPutAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("santa", "claus");
        map.put("kk", "aldora");
        map.put("lemon", "tree");
        redisService.putAll("redistest", map);
        assertEquals("aldora", redisService.get(redisKey, "kk"));

        List<Object> multiGet = redisService.multiGet( redisKey, Lists.newArrayList("santa", "kk", "lemon"));
        assertEquals(3, multiGet.size());

        List<Object> results = redisService.values( redisKey);

        Map<Object, Object> entries = redisService.entries(redisKey);
    }

    @Test
    void putObject() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("category name");

        redisService.put(redisKey, "category", categoryDTO);
    }
}