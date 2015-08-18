package com.github.cleverage.elasticsearch;

import com.github.cleverage.elasticsearch.plugin.IndexPlugin;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.guice.GuiceableModule;
import scala.collection.Seq;

public class ElasticsearchModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IndexPlugin.class)
            .asEagerSingleton();
    }
}
