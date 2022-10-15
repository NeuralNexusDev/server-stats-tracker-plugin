package ca.sperrer.expnet.expnetstats;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Dictionary;
import java.util.Hashtable;

class DataAccumulatorBungee extends DataAccumulator<ProxyServer> {
    @Override
    public Dictionary<Object, Object> get_player_data(ProxyServer server) {
        Dictionary<Object, Object> player_data = new Hashtable<>();

        for (ProxiedPlayer player : server.getPlayers()) {
            Dictionary<Object, Object> data = new Hashtable<>();
            data.put("name", player.getName());
            data.put("ping", player.getPing());
            data.put("ip", player.getAddress().getAddress().getHostAddress());

            player_data.put(player.getUniqueId().toString(), data);
        }

        return player_data;
    }
}
