package com.github.cleverage.elasticsearch;

import com.github.cleverage.elasticsearch.plugin.IndexPlugin;
import com.google.inject.AbstractModule;
import org.elasticsearch.discovery.Discovery;

public class ElasticsearchModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IndexPlugin.class)
            .asEagerSingleton();
    }
}
