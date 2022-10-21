package ca.sperrer.expnet.expnetstats.mixin;

import ca.sperrer.expnet.expnetstats.WSClient;
import ca.sperrer.expnet.expnetstats.WSClientFabric;
import ca.sperrer.expnet.expnetstats.ExpNetStats;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class) // ServerWorld, MinecraftServer, etc
public class FabricMixin implements ExpNetStats.FabricMixinAccess {
	@Unique
	private long ticksUntilSomething;
	private MinecraftServer mcserver;

	@Inject(method = "tick", at = @At("TAIL"))
	private void onTick(CallbackInfo ci) throws Exception { // Fix parameters as needed
		if (--this.ticksUntilSomething <= 0L) {
			try {
				WSClient<MinecraftServer> ws = new WSClientFabric();
				ws.send(mcserver, "config/ExpNetStats.json");
			}
			catch (Exception e) {
				ExpNetStats.LOGGER.info("Could not connect to the ExpNetStats backend, please contact ThePotatoKing#3452");
			}
			ticksUntilSomething = 20L;
		}
	}
	@Override
	public void pingtracker_setTimer(long ticksUntilSomething, MinecraftServer server) {
		this.ticksUntilSomething = ticksUntilSomething;
		this.mcserver = server;
	}
}

