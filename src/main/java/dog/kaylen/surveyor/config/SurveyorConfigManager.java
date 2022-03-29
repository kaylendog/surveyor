package dog.kaylen.surveyor.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import dog.kaylen.surveyor.SurveyorClient;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;

/** Config manager for the Surveyor plugin. */
@UtilityClass
public final class SurveyorConfigManager {
    private static Toml reader = new Toml();
    private static TomlWriter writer = new TomlWriter();

    /**
     * Get the default configuration.
     *
     * @return The default configuration.
     */
    public static SurveyorConfig getDefaults() {
        return new SurveyorConfig();
    }

    /**
     * Attempt to load the config from the Fabric configuration directory. If this fails, the
     * default configuration is returned.
     *
     * @return The loaded config, or default config if the operation failed.
     */
    public static SurveyorConfig loadConfig() {
        try {
            Path path = FabricLoader.getInstance().getConfigDir().resolve("surveyor.toml");
            return reader.read(path.toFile()).to(SurveyorConfig.class);
        } catch (IllegalStateException err) {
            SurveyorClient.getInstance()
                    .getLogger()
                    .warn("Failed to load Surveyor configuration - stack trace below");
            SurveyorClient.getInstance().getLogger().warn(err);
        }
        return getDefaults();
    }

    /**
     * Save the configuration to disk.
     *
     * @param config The configuration to save
     * @return True if the action was successful, false if it was not.
     */
    public static Boolean saveConfig(SurveyorConfig config) {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("surveyor.toml");
        try {
            writer.write(config, path.toFile());
            return true;
        } catch (IOException err) {
            SurveyorClient.getInstance()
                    .getLogger()
                    .warn("Failed to save Surveyor configuration - stack trace below");
            SurveyorClient.getInstance().getLogger().warn(err);
            return false;
        }
    }

    /**
     * Attempts to copy the default configuration to the config directory. Fails silently if a
     * config file already exists, warns if an exception occurred during writing.
     *
     * @return True if the action was successful, false otherwise.
     */
    public static Boolean tryCopyDefaults() {
        SurveyorConfig defaults = getDefaults();
        File file = FabricLoader.getInstance().getConfigDir().resolve("surveyor.toml").toFile();
        // check if the file exists
        if (file.exists()) {
            return false;
        }
        try {
            writer.write(defaults, file);
            return true;
        } catch (IOException err) {
            SurveyorClient.getInstance()
                    .getLogger()
                    .warn("Failed to save Surveyor configuration defaults - stack trace below");
            SurveyorClient.getInstance().getLogger().warn(err);
            return false;
        }
    }
}
