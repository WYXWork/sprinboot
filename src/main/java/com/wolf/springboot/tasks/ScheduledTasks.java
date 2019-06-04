package com.wolf.springboot.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	private Logger logger=LogManager.getLogger(getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async//异步执行
    @Scheduled(fixedRate = 60000)
    public Future<String> reportCurrentTime() {
    	logger.error("A每分钟执行一次，现在时间：" + dateFormat.format(new Date()));
        return new AsyncResult<>("任务A完成");
    }
    
    @Async
    @Scheduled(fixedRate = 60000)
    public Future<String> reportCurrentTime2() {
    	logger.error("B每分钟执行一次，现在时间：" + dateFormat.format(new Date()));
        return new AsyncResult<>("任务B完成");
    }
    
    @Async
    @Scheduled(fixedRate = 60000)
    public Future<String> reportCurrentTime3() {
    	logger.error("C每分钟执行一次，现在时间：" + dateFormat.format(new Date()));
        return new AsyncResult<>("任务C完成");
    }

}
