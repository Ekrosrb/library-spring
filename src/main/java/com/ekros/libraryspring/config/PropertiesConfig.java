package com.ekros.libraryspring.config;

import com.ekros.libraryspring.model.event.properties.EventProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

  @Bean
  @ConfigurationProperties(prefix = "event.service")
  public EventProperties eventProperties(){
    return new EventProperties();
  }
}
