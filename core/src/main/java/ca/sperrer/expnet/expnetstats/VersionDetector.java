package ca.sperrer.expnet.expnetstats;

import org.bukkit.Bukkit;
import org.bukkit.Server;

public class VersionDetector {
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
            ExpNetStatsSpigot.getInstance().getLogger().info("ExpNetStats could not find a valid implementation for this server version.");
        }
        return da;
    }
}
