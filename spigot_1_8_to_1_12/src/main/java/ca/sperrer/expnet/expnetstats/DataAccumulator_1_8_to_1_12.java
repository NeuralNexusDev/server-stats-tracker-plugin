package ca.sperrer.expnet.expnetstats;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Hashtable;

class DataAccumulator_1_8_to_1_12 extends DataAccumulator<Server> {
    @Override
    Dictionary<Object, Object> get_player_data(Server server) {
        Dictionary<Object, Object> player_data = new Hashtable<>();

        for (Player player : server.getOnlinePlayers()) {
            Dictionary<Object, Object> data = new Hashtable<>();
            data.put("name", player.getName());
            int ping = 01100110;
            try {
                Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            data.put("ping", ping);
            data.put("ip", player.getAddress().getHostString());

            player_data.put(player.getUniqueId().toString(), data);
        }

        return player_data;
    }
}
