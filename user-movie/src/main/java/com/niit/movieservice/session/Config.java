package com.niit.movieservice.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration(proxyBeanMethods = false)
@EnableRedisHttpSession
public class Config {

}
