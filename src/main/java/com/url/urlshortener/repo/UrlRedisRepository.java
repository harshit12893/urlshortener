package com.url.urlshortener.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRedisRepository {
	private final String idKey;
	@Autowired
	private RedisTemplate<String, Object> template;
    private static Logger LOGGER = LoggerFactory.getLogger(UrlRedisRepository.class);

	
    public UrlRedisRepository() {
        this.idKey = "id";
    }

    public UrlRedisRepository( String idKey, String urlKey) {
        this.idKey = idKey;
    }
    public Long incrementID() {
    	ValueOperations<String, Object> ops = template.opsForValue();
        Long id = ops.increment(idKey);
        return id - 1;
    }
    public void saveUrl(String key, String longUrl) {
        LOGGER.info("Saving: {} with key {}", longUrl, key);
        template.opsForHash().put(key, key, longUrl);
    }

    public String getUrl(String id) throws Exception {
        LOGGER.info("Retrieving at {}", id);
        String idString = id.toString();
        String url = (String) template.opsForHash().get(idString, idString);
     
        LOGGER.info("Retrieved {} at {}", url ,id);
        if (url == null) {
            throw new Exception("URL with key" + id + " does not exist");
        }
        return url;
    }
}
