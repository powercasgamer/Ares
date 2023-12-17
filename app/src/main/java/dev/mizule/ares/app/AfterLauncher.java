package dev.mizule.ares.app;

import dev.mizule.ares.app.config.Config;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.nio.file.Path;

/**
 * This is, in short fucking horrible, but, it works.
 */
public class AfterLauncher {

    public AfterLauncher() {
        Path configPath = Path.of("config.conf");
        ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder()
                .path(configPath)
                .defaultOptions(options -> options.shouldCopyDefaults(true)
                        .serializers(builder -> {
                            builder.registerAnnotatedObjects(org.spongepowered.configurate.kotlin.ObjectMappingKt.objectMapperFactory());
                        }))
                .build();

        try {
            ConfigurationNode configNode = configLoader.load();
            Config config = configNode.get(Config.class);
            if (config == null) {
                throw new IllegalStateException("Could not read configuration");
            }

            if (!configPath.toFile().exists()) {
                configNode.set(config); // update the backing node to add defaults
                configLoader.save(configNode);
            }

            final var app = new App(config);
            Runtime.getRuntime().addShutdownHook(new Thread(app::stop));

            app.start();
        } catch (final Exception exception) {
            // cry
            throw new RuntimeException((exception));
        }
    }
}
