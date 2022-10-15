package ca.sperrer.expnet.expnetstats;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public final class ExpNetStatsBungee extends Plugin {
    ProxyServer server = ProxyServer.getInstance();

    @Override
    public void onEnable() {
        getLogger().info("has loaded!");
        ScheduledTask scheduledTask = getProxy().getScheduler().schedule(this, () -> {
            try {
                WSClient<ProxyServer> ws = new WSClientBungee();
                ws.send(server, "plugins/ExpNetStats.json");
            } catch (Exception e) {
                getLogger().info("Could not connect to the ExpNetStats backend, please contact ThePotatoKing#3452");
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
