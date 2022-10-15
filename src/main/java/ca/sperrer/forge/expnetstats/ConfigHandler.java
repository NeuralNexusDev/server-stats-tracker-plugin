package ca.sperrer.forge.expnetstats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ConfigHandler {
    public static String get_guid(String filename) throws IOException {
        String guid;
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            try {
                Scanner scan = new Scanner(f);
                String json = scan.nextLine();

                // Read Gson
                Gson gson = new Gson();
                Map gson_map = gson.fromJson(json, Map.class);

                guid = gson_map.get("guid").toString();
            }
            catch (FileNotFoundException e) {
                System.out.println("File was empty.");
                e.printStackTrace();

                guid = UUID.randomUUID().toString();

                //Write
                FileWriter writer = new FileWriter(filename);

                //Write Gson
                Map<String, String> guid_map = new HashMap<>();
                guid_map.put("guid", guid);

                Gson gson = new GsonBuilder().create();
                String guid_json = gson.toJson(guid_map);

                writer.write(guid_json);
                writer.close();
            }
        }
        else {
            guid = UUID.randomUUID().toString();

            try {
                //Write
                f.createNewFile();
                FileWriter writer = new FileWriter(filename);

                //Write Gson
                Map<String, String> guid_map = new HashMap<>();
                guid_map.put("guid", guid);

                Gson gson = new GsonBuilder().create();
                String guid_json = gson.toJson(guid_map);

                writer.write(guid_json);
                writer.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return guid;
    }
}
