package dog.kaylen.surveyor.util;

import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

/**
 * Utility methods for sending messages to the local player.
 */
@UtilityClass
public final class Chat {
    public static void sendMessage(Text text) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) {
            return;
        }
        player.sendMessage(text, false);
    }
}
