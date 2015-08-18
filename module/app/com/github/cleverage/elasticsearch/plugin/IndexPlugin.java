package com.github.cleverage.elasticsearch.plugin;

import org.elasticsearch.client.transport.NoNodeAvailableException;

import play.Application;
import play.Configuration;
import play.Logger;
import play.Plugin;

import com.github.cleverage.elasticsearch.IndexClient;
import com.github.cleverage.elasticsearch.IndexService;
import play.inject.ApplicationLifecycle;
import play.libs.F;

import javax.inject.Inject;

/**
 * ElasticSearch PLugin for Play 2 written in Java.
 * User: nboire
 * Date: 12/05/12
 */

@javax.inject.Singleton
public class IndexPlugin {
    private final Configuration configuration;
    private IndexClient client = null;

    @Inject
    public IndexPlugin(Configuration configuration, IndexClient client, ApplicationLifecycle lifecycle) {
        this.configuration = configuration;
        this.client = client;
        lifecycle.addStopHook(() -> {
            this.onStop();
            return F.Promise.pure(null);
        });
        onStart();
    }

    private boolean isPluginDisabled() {
        String status = configuration.getString("elasticsearch.plugin");
        return status != null && status.equals("disabled");
    }

    public boolean enabled() {
        return !isPluginDisabled();
    }

    public void onStart() {
        // Load indexName, indexType, indexMapping from annotation
//        try {
//            IndexClient.config.loadFromAnnotations();
//        } catch (Exception e) {
//            client = null;
//            Logger.error("ElasticSearch: Error scanning for annotations", e);
//            throw e;
//        }

        try {
            client.start();
        } catch (Exception e) {
            client = null;
            Logger.error("ElasticSearch : Error when starting ElasticSearch Client ", e);
        }

        // We catch these exceptions to allow application to start even if the module start fails
        try {
            // Create Indexs and Mappings if not Exists
            String[] indexNames = IndexClient.config.indexNames;
            for (String indexName : indexNames) {

                if (!IndexService.existsIndex(indexName)) {
                    // Create index
                    IndexService.createIndex(indexName);

                    // Prepare Index ( define mapping if present )
                    IndexService.prepareIndex(indexName);
                }
            }

            Logger.info("ElasticSearch : Plugin has started");

        } catch (NoNodeAvailableException e) {
            Logger.error("ElasticSearch : No ElasticSearch node is available. Please check that your configuration is " +
                "correct, that you ES server is up and reachable from the network. Index has not been created and prepared.", e);
        } catch (Exception e) {
            Logger.error("ElasticSearch : An unexpected exception has occurred during index preparation. Index has not been created and prepared.", e);
        }

    }

    public void onStop() {
        if (client != null) {
            // Deleting index(s) if define in conf
            if (IndexClient.config.dropOnShutdown) {
                String[] indexNames = client.config.indexNames;
                for (String indexName : indexNames) {
                    if (IndexService.existsIndex(indexName)) {
                        IndexService.deleteIndex(indexName);
                    }
                }
            }

            // Stopping the client
            try {
                client.stop();
            } catch (Exception e) {
                Logger.error("ElasticSearch : error when stop plugin ", e);
            }
        }
        Logger.info("ElasticSearch : Plugin has stopped");
    }
}
