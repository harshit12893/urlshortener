package com.url.urlshortener.repo;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.url.urlshortener.controller.UrlShortenerController;

@Repository
public class UrlRedisRepository {
	private final String idKey;
	private final String urlKey;
    @Autowired
	private RedisTemplate<String, Object> template;
    private static Logger LOGGER = LoggerFactory.getLogger(UrlRedisRepository.class);

	
    public UrlRedisRepository() {
        this.idKey = "id";
        this.urlKey= "url";
    }

    public UrlRedisRepository( String idKey, String urlKey) {
        this.idKey = idKey;
        this.urlKey=urlKey;
    }
    public Long incrementID() {
    	ValueOperations<String, Object> ops = template.opsForValue();
        Long id = ops.increment(idKey);
        return id - 1;
    }
    public void saveUrl(String key,String hashKey, String longUrl) {
        LOGGER.info("Saving: {} at {}", longUrl, key);
        template.opsForHash().put(key, key, longUrl);
      //  template.opsForSet().add(key, longUrl);
        //jedis.hset(urlKey, key, longUrl);
    }

    public String getUrl(String id) throws Exception {
        LOGGER.info("Retrieving at {}", id);
        //String url = jedis.hget(urlKey, "url:"+id);
        //Set<Object> url = template.opsForSet().members(id.toString());
        String idString = id.toString();
        String url = (String) template.opsForHash().get(idString, idString);
     
        LOGGER.info("Retrieved {} at {}", url ,id);
        if (url == null) {
            throw new Exception("URL at key" + id + " does not exist");
        }
        return url;
    }
}
