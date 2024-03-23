package com.jpepe.playingtogether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestPlayingTogetherApplication {

  @Bean
  @ServiceConnection
  MariaDBContainer<?> mariaDbContainer() {
    return new MariaDBContainer<>(DockerImageName.parse("mariadb:latest"));
  }

  @Bean
  @ServiceConnection(name = "redis")
  GenericContainer<?> redisContainer() {
    return new GenericContainer<>(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
  }

  public static void main(String[] args) {
    SpringApplication.from(PlayingTogetherApplication::main)
        .with(TestPlayingTogetherApplication.class)
        .run(args);
  }
}
