package dev.mizule.ares.app;

import dev.mizule.ares.app.util.AppClassPathAppender;
import dev.mizule.ares.app.util.AppDependencies;

import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

public class Launcher {

    private static final CountDownLatch loadLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            downloadDependencies();
        } finally {
            loadLatch.countDown();
        }

        new AfterLauncher();
    }

    private static void downloadDependencies() {
        new AppClassPathAppender().append(AppDependencies.resolve(Path.of("libraries")));
    }
}
