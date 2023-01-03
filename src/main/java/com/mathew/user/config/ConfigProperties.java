package com.mathew.user.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="baseurls")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigProperties{
  private String ratingservice;
  private String hotelservice;
}