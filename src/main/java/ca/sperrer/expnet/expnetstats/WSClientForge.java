package ca.sperrer.expnet.expnetstats;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.ModList;

public class WSClientForge extends WSClient<MinecraftServer> {
    @Override
    boolean dependancy_check(String name) {
        return (ModList.get().isLoaded(name));
    }
    @Override
    DataAccumulator<MinecraftServer> version_detect() {
        return new DataAccumulatorForge();
    }
}