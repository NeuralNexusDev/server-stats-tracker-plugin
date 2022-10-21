package ca.sperrer.expnet.expnetstats;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Dictionary;
import java.util.Hashtable;

class DataAccumulator_1_17_to_1_19 extends DataAccumulator<Server> {
    @Override
    Dictionary<Object, Object> get_player_data(Server server) {
        Dictionary<Object, Object> player_data = new Hashtable<>();

        for (Player player : server.getOnlinePlayers()) {
            Dictionary<Object, Object> data = new Hashtable<>();
            data.put("name", player.getName());
            data.put("ping", player.getPing());
            data.put("ip", player.getAddress().getHostString());

            player_data.put(player.getUniqueId().toString(), data);
        }

        return player_data;
    }
}
