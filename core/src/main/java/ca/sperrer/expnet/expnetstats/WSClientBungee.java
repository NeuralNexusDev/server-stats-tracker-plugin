package ca.sperrer.expnet.expnetstats;

import net.md_5.bungee.api.ProxyServer;

class WSClientBungee extends WSClient<ProxyServer>{
    @Override
    boolean dependancy_check(String name) {
        return (ProxyServer.getInstance().getPluginManager().getPlugin(name) != null);
    }
    @Override
    DataAccumulator<ProxyServer> version_detect() {
        return new DataAccumulatorBungee();
    }
}