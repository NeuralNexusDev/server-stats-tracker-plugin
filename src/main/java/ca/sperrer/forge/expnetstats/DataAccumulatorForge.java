package ca.sperrer.forge.expnetstats;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.util.Dictionary;
import java.util.Hashtable;

class DataAccumulatorForge extends DataAccumulator<MinecraftServer> {
    Dictionary<Object, Object> get_player_data(MinecraftServer server) {
        Dictionary<Object, Object> player_data = new Hashtable<>();

        for (EntityPlayerMP player : server.getPlayerList().getPlayers()) {
            Dictionary<Object, Object> data = new Hashtable<>();
            data.put("name", player.getName());
            data.put("ping", player.ping);
            data.put("ip", player.getPlayerIP());

            player_data.put(player.getUniqueID().toString(), data);
        }

        return player_data;
    }
}
