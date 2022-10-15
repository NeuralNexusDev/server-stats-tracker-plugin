package ca.sperrer.expnet.expnetstats;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Dictionary;
import java.util.Hashtable;

class DataAccumulatorFabric extends DataAccumulator<MinecraftServer> {
    @Override
    Dictionary<Object, Object> get_player_data(MinecraftServer server) {
        Dictionary<Object, Object> player_data = new Hashtable<>();
        for (ServerPlayerEntity player : PlayerLookup.all(server)) {
            Dictionary<Object, Object> data = new Hashtable<>();
            data.put("name", player.getEntityName());
            data.put("ping", player.pingMilliseconds);
            data.put("ip", player.getIp());

            player_data.put(player.getUuidAsString(), data);
        }

        return player_data;
    }
}
