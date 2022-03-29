package dog.kaylen.surveyor.event;

import dog.kaylen.surveyor.SurveyorClient;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;

/** Event handler that listens for players creating maps. */
public class UseMapItemHandler implements BaseEventHandler {
    @Override
    public void setup() {
UseItemCallback.EVENT.register(
    (player, world, hand) -> {
        // check if hand item is an empty map
        SurveyorClient.getInstance().getLogger().info("event fired");
        SurveyorClient.getInstance().getLogger().info(player.getMainHandStack());
        // ensure we are the client
        if (!world.isClient) {
            return TypedActionResult.pass(ItemStack.EMPTY);
        }
        // check if the stack is correct
        if (!player.getMainHandStack().isOf(Registry.ITEM.get(new Identifier("minecraft:map")))) {
            return TypedActionResult.pass(ItemStack.EMPTY);
        }
        SurveyorClient.getInstance().getLogger().info("map created");
        return TypedActionResult.pass(ItemStack.EMPTY);
    });
    }
}
