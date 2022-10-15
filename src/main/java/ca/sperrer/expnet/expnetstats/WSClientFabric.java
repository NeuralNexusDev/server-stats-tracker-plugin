package ca.sperrer.expnet.expnetstats;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

public class WSClientFabric extends WSClient<MinecraftServer> {
    @Override
    boolean dependancy_check(String name) {
        return (FabricLoader.getInstance().isModLoaded(name));
    }
    @Override
    DataAccumulator<MinecraftServer> version_detect() {
        return new DataAccumulatorFabric();
    }
}