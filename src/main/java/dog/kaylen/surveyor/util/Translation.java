package dog.kaylen.surveyor.util;

import lombok.Getter;
import net.minecraft.text.*;

/** An enum of translation keys. */
public enum Translation {
    CONFIG_SCREEN_TITLE("title.surveyor.config");
    /** The internal name-spaced key of this translation. */
    @Getter private final String key;

    Translation(String key) {
        this.key = key;
    }

    /**
     * Get this `Translation` instance as a `TranslatableText` instance.
     *
     * @return A `TranslatableText` instance.
     */
    public TranslatableText asText() {
        return new TranslatableText(this.key);
    }
}
