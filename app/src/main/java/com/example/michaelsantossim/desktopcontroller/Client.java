package com.example.michaelsantossim.desktopcontroller;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Michael Santos Sim on 19/11/2016.
 */

public class Client extends AsyncTask<String, Void, Void> {

    private final Context context;

    public Client(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            URL url = new URL("192.168.0.101:800");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          //String urlParameters = "fizz=buzz";
            connection.setRequestMethod("POST");
          //connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
          //connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setDoOutput(true);
            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
          //dStream.writeBytes(urlParameters);
            dStream.flush();
            dStream.close();
            int responseCode = connection.getResponseCode();

            final StringBuilder output = new StringBuilder("Request URL " + url);
            output.append(System.getProperty("line.separator")  + "Response Code " + responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();

            while((line = br.readLine()) != null ) {
                responseOutput.append(line);
            }
            br.close();

        }catch(MalformedURLException e){
            Toast.makeText(context, "Error MalformedUrl: \n" + e.getMessage(), Toast.LENGTH_LONG).show();

        }catch (IOException e){
            Toast.makeText(context, "Error IOException: \n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, "Error: \n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return null;
    }
}
