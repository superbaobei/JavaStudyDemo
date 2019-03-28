package com.sxy.www;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class MydemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(MydemoApplication.class);

	public static void main(String[] args) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
		logger.info("service start.................");
		SpringApplication.run(MydemoApplication.class, args);

	}

	@Bean
	@Qualifier("concurrentMapCacheManager")//Create a new ConcurrentMapCacheManager with the specified name.
	@Primary//when multily bean exists,this bean is take precedence
	ConcurrentMapCacheManager concurrentMapCacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
