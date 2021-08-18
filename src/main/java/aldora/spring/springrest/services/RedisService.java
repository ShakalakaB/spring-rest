package aldora.spring.springrest.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RedisService {
    void put(String key, String hashKey, Object hashValue);

    void putAll(String key, Map<String, Object> value);

    Object get(String key, String hashKey);

    List<Object> multiGet(String key, Collection<Object> hashKeys);

    List<Object> values(String key);

    Map<Object, Object> entries(String key);

}
