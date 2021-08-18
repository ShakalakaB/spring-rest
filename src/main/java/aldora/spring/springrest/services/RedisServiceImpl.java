package aldora.spring.springrest.services;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void boundHashOpsPut(String key, String hashKey, Object hashValue) {
        BoundHashOperations<String, Object, Object> hashOps = getHashOperations(key);
        hashOps.put(hashKey, hashValue);
    }

    @Override
    public void boundHashOpsPutAll(String key, Map<String, Object> value) {
        BoundHashOperations<String, Object, Object> hashOps = getHashOperations(key);
        hashOps.putAll(value);
    }

    @Override
    public Object boundHashOpsGet(String key, String hashKey) {
        BoundHashOperations<String, Object, Object> hashOps = getHashOperations(key);
        return hashOps.get(hashKey);
    }

    @Override
    public List<Object> boundHashOpsMultiGet(String key, Collection<Object> hashKeys) {
        BoundHashOperations<String, Object, Object> hashOps = getHashOperations(key);
        return hashOps.multiGet(hashKeys);
    }

    @Override
    public List<Object> boundHashOpsValues(String key) {
        BoundHashOperations<String, Object, Object> hashOps = getHashOperations(key);
        return hashOps.values();
    }

    @Override
    public Map<Object, Object> boundHashOpsEntries(String key) {
        BoundHashOperations<String, Object, Object> hashOps = getHashOperations(key);
        return hashOps.entries();
    }

    private BoundHashOperations<String, Object, Object> getHashOperations(String key) {
        return redisTemplate.boundHashOps(key);
    }
}
