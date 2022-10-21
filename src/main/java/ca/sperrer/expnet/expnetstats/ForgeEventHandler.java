package ca.sperrer.expnet.expnetstats;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeEventHandler {
    int count = 0;
    @SubscribeEvent
    public void servertick(TickEvent.ServerTickEvent event) {
        if (count % 40 == 0) {
            try {
                WSClient<MinecraftServer> ws = new WSClientForge();
                ws.send(event.getServer(), "config/ExpNetStats.json");
            } catch (Exception e) {
                System.out.println("Could not connect to the ExpNetStats backend, please contact ThePotatoKing#3452");
            }
            count = 0;
        }
        count ++;
    }
}
