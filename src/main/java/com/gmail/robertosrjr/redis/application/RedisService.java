package com.gmail.robertosrjr.redis.application;

import com.gmail.robertosrjr.redis.adapter.controller.rest.RedisResource;
import com.gmail.robertosrjr.redis.domain.Book;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Book save(Book book) {

        try {
            logger.info("RedisService::save::id:{}", book.getIsbn());
            redisTemplate.opsForValue().set(book.getIsbn(), book.getIsbn());
            logger.info("Salvo com sucesso.");
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }

        return book;
    }

    public Book findById(String id) {
        try {

            logger.info("RedisService::findById::id:{}", id);
            String isbn = redisTemplate.opsForValue().get(id);
            logger.info("isbn:{}", isbn);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
