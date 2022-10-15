package ca.sperrer.forge.expnetstats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.neovisionaries.ws.client.*;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class WSClient<V> {
    boolean dependancy_check(String name) {
        return true;
    }

    DataAccumulator<V> version_detect() {
        return new DataAccumulator<V>();
    }

    public void send(V server, String filename) throws Exception {
        // Version detection
        DataAccumulator<V> da = version_detect();

        // Init vars
        Gson gson = new GsonBuilder().create();

        String server_data;
        if (this.dependancy_check("spark")) {
            Dictionary<Object, Object> server_map = SparkModule.get_server_info();

            server_map.put("memUsedMB", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576);
            server_map.put("memMaxMB", Runtime.getRuntime().maxMemory()/1048576);

            String server_json = gson.toJson(server_map);

            server_data = ",\"server\":" + server_json;
        } else {
            Dictionary<Object, Object> server_map = new Hashtable<>();

            server_map.put("memUsedMB", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1048576);
            server_map.put("memMaxMB", Runtime.getRuntime().maxMemory()/1048576);

            String server_json = gson.toJson(server_map);

            server_data = ",\"server\":" + server_json;
        }

        // Data collection
        Dictionary<Object, Object> data_map = da.get_player_data(server);

        String data_json = gson.toJson(data_map);

        String formatted = "{\"guid\":\"" + ConfigHandler.get_guid(filename) + "\",\"players\":" + data_json + server_data + "}";

        new WebSocketFactory()
                .createSocket("ws://panel.thexpnetwork.com:9001")
                .connect()
                .sendText(formatted, true)
                .disconnect();
    }
}