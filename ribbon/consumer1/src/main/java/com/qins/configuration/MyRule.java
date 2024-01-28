package com.qins.configuration;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

@Component
public class MyRule implements IRule {
    @Override
    public Server choose(Object o) {
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {

    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return null;
    }
}
