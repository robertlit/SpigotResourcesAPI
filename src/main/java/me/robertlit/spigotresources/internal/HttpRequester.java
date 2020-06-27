package me.robertlit.spigotresources.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequester {
    public static JsonElement request(String urlString) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            String result = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            JsonElement jsonElement = JsonParser.parseString(result);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.get("code") != null) {
                    return null;
                }
            }
            return jsonElement;
        } catch (IOException e) {
            return null;
        }
    }
}
