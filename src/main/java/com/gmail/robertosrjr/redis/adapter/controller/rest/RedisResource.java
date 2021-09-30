package com.gmail.robertosrjr.redis.adapter.controller.rest;

import com.gmail.robertosrjr.redis.application.RedisService;
import com.gmail.robertosrjr.redis.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisResource {

    Logger logger = LoggerFactory.getLogger(RedisResource.class);

    @Autowired
    private RedisService service;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Book> findById(@PathVariable String id){

        logger.info("RedisResource::findById::id:{}", id);
        Book book = this.service.findById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> save(@RequestBody Book book){

        logger.info("RedisResource::save::{}", book);
        book = this.service.save(book);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }
}
