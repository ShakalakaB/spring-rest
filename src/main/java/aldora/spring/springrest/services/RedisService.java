package aldora.spring.springrest.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RedisService {
    void boundHashOpsPut(String key, String hashKey, Object hashValue);

    void boundHashOpsPutAll(String key, Map<String, Object> value);

    Object boundHashOpsGet(String key, String hashKey);

    List<Object> boundHashOpsMultiGet(String key, Collection<Object> hashKeys);

    List<Object> boundHashOpsValues(String key);

    Map<Object, Object> boundHashOpsEntries(String key);

}
