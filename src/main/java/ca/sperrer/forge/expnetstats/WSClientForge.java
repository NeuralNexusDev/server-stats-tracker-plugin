package ca.sperrer.forge.expnetstats;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Loader;

public class WSClientForge extends WSClient<MinecraftServer> {
    @Override
    boolean dependancy_check(String name) {
        return Loader.isModLoaded(name);
    }
    @Override
    DataAccumulator<MinecraftServer> version_detect() {
        return new DataAccumulatorForge();
    }
}