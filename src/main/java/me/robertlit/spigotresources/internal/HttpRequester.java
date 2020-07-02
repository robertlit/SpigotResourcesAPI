package me.robertlit.spigotresources.internal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequester {
    public static String requestString(String urlString) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            if (connection.getResponseCode() == 404) {
                return null;
            }
            return new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public static BufferedImage requestImage(String urlString) {
        try {
            return ImageIO.read(new URL(urlString));
        } catch (IOException e) {
            return null;
        }
    }
}
