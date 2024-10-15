package com.challenge.parsers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SchedulerCondition implements Condition {

    @Value("${interval.to.execute}")
    private long fixedRate;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String interval = context.getEnvironment().getProperty("interval.to.execute");
        return interval != null && Long.parseLong(interval) > 0;
    }
}