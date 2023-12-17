package dev.mizule.ares.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.jpenilla.gremlin.runtime.DependencyCache;
import xyz.jpenilla.gremlin.runtime.DependencyResolver;
import xyz.jpenilla.gremlin.runtime.DependencySet;

import java.nio.file.Path;
import java.util.Set;

public class AppDependencies {

    private static final Logger logger = LoggerFactory.getLogger(AppDependencies.class.getSimpleName());

    public static Set<Path> resolve(Path cacheDir) {
        DependencySet deps = DependencySet.readFromClasspathResource(
                AppDependencies.class.getClassLoader(),
                "dependencies.txt"
        );
        DependencyCache cache = new DependencyCache(cacheDir);
        Set<Path> files;
        try (DependencyResolver downloader = new DependencyResolver(logger)) {
            files = downloader.resolve(deps, cache).jarFiles();
        }
        cache.cleanup();
        return files;
    }
}