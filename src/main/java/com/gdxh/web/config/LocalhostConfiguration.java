package com.gdxh.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("localhost")
@PropertySource("classpath:META-INF/spring/config/localhost.properties")
public class LocalhostConfiguration {

}
