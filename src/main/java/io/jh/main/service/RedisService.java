package io.jh.main.service;

import io.jh.main.domain.BasicDataViewVO;
import io.jh.main.domain.board.BoardCategoryVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("redisService")
@AllArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final RedisTemplate<String, List<?>> redisListTemplate;

    public String getRedisTemplate(String key) {
        ValueOperations<String, Object> template = redisTemplate.opsForValue();
        return (String)template.get(key);
    }

    public void addRedisData(BasicDataViewVO vo) {
        redisTemplate.opsForValue().set(vo.getKey(), vo.getData());
    }

    public void addRedisListData(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }

    public List<?> asdf(String key) {
        return redisListTemplate.opsForList().index(key, 0);
    }

    public void addRedisListData(String key, List<?> list) {
        redisListTemplate.opsForList().leftPush(key, list);
    }
//
//    public List<?> getRedisListData(String key) {
//        return redisListTemplate.opsForList().index(key, 0);
//    }
}
