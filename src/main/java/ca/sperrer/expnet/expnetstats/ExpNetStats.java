package ca.sperrer.expnet.expnetstats;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpNetStats implements ModInitializer, ServerLifecycleEvents.ServerStarted {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("expnetstats");


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("ExpNetStats Has Loaded!");

		ServerLifecycleEvents.SERVER_STARTED.register(this);
	}
	public interface FabricMixinAccess {
		void pingtracker_setTimer ( long ticksUntilSomething, MinecraftServer server);
	}
	public void onServerStarted(MinecraftServer server) {
		LOGGER.info("EXP Network Stats Tracker Has Initialized!");
		try {
			((FabricMixinAccess) server).pingtracker_setTimer(100L, server);
		}
		catch (Exception e) {
			LOGGER.info("Could not connect to the ExpNetStats backend, please contact ThePotatoKing#3452");
		}
	}
}
