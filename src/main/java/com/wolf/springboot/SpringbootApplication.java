package com.wolf.springboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.wolf.springboot.domain.BaseRepositoryImpl;

/**
 * 
 * <p>
 * Title: SpringbootApplication
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月9日
 */
@EnableCaching
@EnableAsync
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class SpringbootApplication {

	static Logger log = LogManager.getLogger(SpringbootApplication.class.getName());

	public static void main(String[] args) {
		log.info("log4j-start");
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
