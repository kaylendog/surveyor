package dog.kaylen.surveyor.mixin;

import net.minecraft.client.render.MapRenderer;
import org.spongepowered.asm.mixin.Mixin;

/** MapRender mixin that catches map render information. */
@Mixin(MapRenderer.class)
public class MapRendererMixin {}
