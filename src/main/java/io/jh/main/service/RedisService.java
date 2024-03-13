package io.jh.main.service;

import io.jh.main.domain.BasicDataViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public String getRedisTemplate(String key) {
        ValueOperations<String, Object> template = redisTemplate.opsForValue();
        return (String)template.get(key);
    }

    public void addRedisData(BasicDataViewVO vo) {
        redisTemplate.opsForValue().set(vo.getKey(), vo.getData());
    }
}
