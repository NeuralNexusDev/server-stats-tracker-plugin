package ca.sperrer.expnet.expnetstats;


import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.Dictionary;
import java.util.Hashtable;

class DataAccumulatorForge extends DataAccumulator<MinecraftServer> {
    @Override
    Dictionary<Object, Object> get_player_data(MinecraftServer server) {
        Dictionary<Object, Object> player_data = new Hashtable<>();

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            Dictionary<Object, Object> data = new Hashtable<>();
            data.put("name", player.getName().getString());
            data.put("ping", player.latency);
            data.put("ip", player.getIpAddress());

            player_data.put(player.getStringUUID(), data);
        }

        return player_data;
    }
}
