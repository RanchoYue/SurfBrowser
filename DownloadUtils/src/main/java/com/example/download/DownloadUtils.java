package com.example.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtils {

    public static void main(String[] args) {
        String fileUrl = "https://ucan.25pp.com/Wandoujia_web_seo_baidu_homepage.apk";
        String destinationPath = "D:\\download\\demo.apk";

        try {
            downloadFile(fileUrl, destinationPath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void downloadFile(String fileUrl, String destinationPath) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method to GET
        connection.setRequestMethod("GET");

        // Open the connection
        connection.connect();

        // Get the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response
            InputStream in = connection.getInputStream();
            FileOutputStream fos = new FileOutputStream(destinationPath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            // Close the streams
            fos.close();
            in.close();

            // Disconnect
            connection.disconnect();
        } else {
            throw new IOException("Server responded with: " + responseCode);
        }
    }
}