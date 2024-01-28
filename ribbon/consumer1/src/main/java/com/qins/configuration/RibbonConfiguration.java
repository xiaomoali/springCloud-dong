package com.qins.configuration;


import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

//    负载均衡策略
//    BestAvailableRule
//    ZoneAvoidanceRule
//    AvailabilityFilteringRule
//    ResponseTimeWeightedRule
//    WeightedResponseTimeRule
//    RoundRobinRule
//    RandomRule
//    RetryRule

    @Bean
    public IRule defaultLBStrategy(){
        return new RandomRule();

    }

}
