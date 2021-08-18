package aldora.spring.springrest.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
@Service
@Qualifier("hashOperations")
//@ConditionalOnClass({RedisTemplate.class})
public class RedisHashServiceImpl implements RedisService {
    private final HashOperations<String, Object, Object> hashOperations;

    public RedisHashServiceImpl(HashOperations<String, Object, Object> hashOperations) {
        this.hashOperations = hashOperations;
    }

    @Override
    public void put(String key, String hashKey, Object hashValue) {
        hashOperations.put(key, hashKey, hashValue);
    }

    @Override
    public void putAll(String key, Map<String, Object> value) {
        hashOperations.putAll(key, value);
    }

    @Override
    public Object get(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

    @Override
    public List<Object> multiGet(String key, Collection<Object> hashKeys) {
        return hashOperations.multiGet(key, hashKeys);
    }

    @Override
    public List<Object> values(String key) {
        return hashOperations.values(key);
    }

    @Override
    public Map<Object, Object> entries(String key) {
        return hashOperations.entries(key);
    }
}
