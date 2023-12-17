package dev.mizule.ares.app.config;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.nio.file.Path;

public class ConfigManager {

    public static Config loadConfig(final @NotNull Path path) {
        ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder()
                .path(path)
                .prettyPrinting(true)
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

            if (!path.toFile().exists()) {
                configNode.set(config); // update the backing node to add defaults
                configLoader.save(configNode);
            }

            return config;
        } catch (final Exception exception) {
            // cry
            throw new RuntimeException((exception));
        }
    }
}
