package dev.mizule.ares.app;

import dev.mizule.ares.app.config.Config;
import dev.mizule.ares.app.config.ConfigManager;

import java.nio.file.Path;

/**
 * This is, in short fucking horrible, but, it works.
 */
public class AfterLauncher {

    public AfterLauncher() {
        final Path configPath = Path.of("config.conf");
        final Config config = ConfigManager.loadConfig(configPath);

        final App app = new App(config);
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

        app.start();
    }
}
