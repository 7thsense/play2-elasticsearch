package com.github.cleverage.elasticsearch;

import com.github.cleverage.elasticsearch.plugin.IndexPlugin;
import com.google.inject.AbstractModule;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.discovery.ec2.Ec2Discovery;

public class ElasticsearchModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IndexPlugin.class)
            .asEagerSingleton();
    }
}
