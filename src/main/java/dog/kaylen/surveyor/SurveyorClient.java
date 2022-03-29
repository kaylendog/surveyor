package dog.kaylen.surveyor;

import dog.kaylen.surveyor.config.SurveyorConfig;
import dog.kaylen.surveyor.config.SurveyorConfigManager;
import dog.kaylen.surveyor.event.UseMapItemHandler;
import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** The main clientside entrypoint. */
@Environment(EnvType.CLIENT)
public class SurveyorClient implements ClientModInitializer {
    /** A static reference to the mod singleton. */
    @Getter private static SurveyorClient instance;
    /** The mod logger instance. */
    @Getter private final Logger logger = LogManager.getLogger("Surveyor");
    /** The mod configuration */
    @Getter private SurveyorConfig config;

    @Override
    public void onInitializeClient() {
        this.logger.info("Initializing Surveyor...");
        instance = this;
        // try and copy the mod defaults
        SurveyorConfigManager.tryCopyDefaults();
        this.config = SurveyorConfigManager.loadConfig();
        // register event handlers
        new UseMapItemHandler().setup();
    }
}
