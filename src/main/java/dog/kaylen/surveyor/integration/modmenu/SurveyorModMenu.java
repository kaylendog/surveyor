package dog.kaylen.surveyor.integration.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dog.kaylen.surveyor.config.SurveyorConfig;
import dog.kaylen.surveyor.config.SurveyorConfigManager;
import dog.kaylen.surveyor.util.Translation;
import me.shedaniel.clothconfig2.api.ConfigBuilder;

/** Menu configuration integration with the ModMenu mod. */
public class SurveyorModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            SurveyorConfig config = SurveyorConfigManager.loadConfig();

            ConfigBuilder builder =
                    ConfigBuilder.create()
                            .setParentScreen(parent)
                            .setTitle(Translation.CONFIG_SCREEN_TITLE.asText());
            return builder.build();
        };
    }
}
