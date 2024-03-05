package io.jh.main.controller;

import io.jh.main.model.BasicDataViewVO;
import io.jh.main.service.RedisService;
import io.jh.main.utility.ResponseData;
import io.jh.main.utility.ResponseUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/main/v1/redis")
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/get/{key}")
    public ResponseEntity<ResponseData<String>> all(@PathVariable String key) {
        return ResponseUtility.createGetSuccessResponse(redisService.getRedisTemplate(key));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<Void>> addRedis(@RequestBody BasicDataViewVO vo) {
        redisService.addRedisData(vo);
        return ResponseUtility.createPostSyncSuccessResponse();
    }
}
