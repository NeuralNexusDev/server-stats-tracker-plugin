package ca.sperrer.expnet.expnetstats;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class ExpNetStatsSpigot extends JavaPlugin {
    private static ExpNetStatsSpigot instance;
    Server server = this.getServer();

    public static ExpNetStatsSpigot getInstance() {
        return ExpNetStatsSpigot.instance;
    }

    @Override
    public void onEnable() {
        ExpNetStatsSpigot.instance = this;

        this.getLogger().info("Attempting compatibility for version: " + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(instance, () -> {
            try {
                WSClient<Server> ws = new WSClientSpigot();
                ws.send(server, "plugins/ExpNetStats.json");
            }
            catch(Exception e) {
                this.getLogger().info("Could not connect to the ExpNetStats backend, please contact ThePotatoKing#3452");
            }
        }, 20L * 10L, 20L);
    }

    public static class VersionDetector {
        public static DataAccumulator<Server> main() {
            DataAccumulator<Server> da = null;
            try {
                String packageName = ExpNetStatsSpigot.class.getPackage().getName();
                String internalsName = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
                String da_class = "";

                switch (internalsName) {
                    case "v1_8_R1":
                    case "v1_8_R2":
                    case "v1_8_R3":
                    case "v1_9_R1":
                    case "v1_9_R2":
                    case "v1_10_R1":
                    case "v1_11_R1":
                    case "v1_12_R1":
                        da_class = "DataAccumulator_1_8_to_1_12";
                        break;
                    case "v1_13_R1":
                    case "v1_13_R2":
                    case "v1_14_R1":
                    case "v1_15_R1":
                    case "v1_16_R1":
                    case "v1_16_R2":
                    case "v1_16_R3":
                        da_class = "DataAccumulator_1_13_to_1_16";
                        break;
                    case "v1_17_R1":
                    case "v1_18_R1":
                    case "v1_18_R2":
                    case "v1_19_R1":
                        da_class = "DataAccumulator_1_17_to_1_19";
                        break;
                }
                da = (DataAccumulator<Server>) Class.forName(packageName + "." + da_class).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException exception) {
                getInstance().getLogger().info("ExpNetStats could not find a valid implementation for this server version.");
            }
            return da;
        }
    }
}