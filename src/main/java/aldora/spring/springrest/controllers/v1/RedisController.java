package aldora.spring.springrest.controllers.v1;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/redis")
public class RedisController {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/save")
    public String save() {
        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps("rediskey");
        hashOps.put("kk", "yunjie");
//        hashOps.expire(RedisMeta.KeyPrefix.TOKEN.ttl(), TimeUnit.SECONDS);
        return "ok";
    }
}
