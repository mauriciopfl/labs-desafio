package com.challenge.parsers.Infraestructure.Services;

import com.challenge.parsers.config.SchedulerCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Conditional(SchedulerCondition.class)
@Component
public class FileProcessingScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingService.class);


    @Autowired
    private ProcessingService processingService;


    @Value("${interval.to.execute}")
    private long fixedRate;


    @Scheduled(fixedRateString = "${interval.to.execute}", initialDelay = 0)
    public void processFiles() {
        if(fixedRate>0) {
            logger.info("started execution scheduler with an interval of {} milliseconds", fixedRate);
            processingService.processFilesFromPath();
        }else{
            logger.info("Interval to execute is 0, scheduler will not be executed");
        }
    }
}
