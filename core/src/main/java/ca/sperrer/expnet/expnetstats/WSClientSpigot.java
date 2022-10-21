package ca.sperrer.expnet.expnetstats;

import org.bukkit.Bukkit;
import org.bukkit.Server;

class WSClientSpigot extends WSClient<Server> {
    @Override
    boolean dependancy_check(String name) {
        return (Bukkit.getServer().getPluginManager().getPlugin(name) != null);
    }
    @Override
    DataAccumulator<Server> version_detect() {
        return ExpNetStatsSpigot.VersionDetector.main();
    }
}