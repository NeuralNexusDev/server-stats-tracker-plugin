package ca.sperrer.forge.expnetstats;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventHandler {
    MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
    int count = 0;
    @SubscribeEvent
    public void servertick(TickEvent.ServerTickEvent event) {
        if (count % 2400 == 0) {
            try {
                WSClient<MinecraftServer> ws = new WSClientForge();
                ws.send(server, "config/ExpNetStats.json");
            } catch (Exception e) {
                System.out.println("Could not connect to the ExpNetStats backend, please contact ThePotatoKing#3452");
            }
            count = 0;
        }
        count ++;
    }
}
