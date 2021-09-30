package com.gmail.robertosrjr.redis;

import com.gmail.robertosrjr.redis.domain.Book;
import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Value("${spring.redis.host}")
	private String hostname;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.database}")
	private int database;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
				.readFrom(ReadFrom.REPLICA_PREFERRED)
				.build();
		RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(hostname, port);
		serverConfig.setPassword(password);
		serverConfig.setDatabase(database);

		return new LettuceConnectionFactory(serverConfig, clientConfig);
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(this.redisConnectionFactory());
		return template;
	}
}
