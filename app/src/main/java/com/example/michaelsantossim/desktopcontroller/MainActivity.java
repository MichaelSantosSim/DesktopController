package com.example.michaelsantossim.desktopcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String command = "";
    private String serverAddress = "";
    private String serverFile = "desktopControllerServer.txt";

    public static MainActivity instance;

    TextView serverIpInput;
    EditText et;
    Button cancelShutdownButton;

    Button toggleFullscreen;
    Button shutdownButton;
    Button saveIpButton;
    Button volumeMinus;
    Button volumePlus;
    Button brightUp;
    Button brightDown;
    Button flushDnsButton;
    Button reload;
    Button channels;


    public MainActivity(){
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadComponents();
        setSendButtonAction();
        loadServerAddressFromFile();

        serverIpInput.setText(serverAddress);

    }

    private void loadComponents(){
        serverIpInput = (TextView)findViewById(R.id.serverIpInput);
        et = (EditText)findViewById(R.id.minutes);
        cancelShutdownButton = (Button) findViewById(R.id.cancelShutdownButton);

        toggleFullscreen = (Button)findViewById(R.id.togglefullscreen);
        shutdownButton = (Button) findViewById(R.id.shutdownButton);
        saveIpButton = (Button) findViewById(R.id.saveIpButton);
        volumeMinus = (Button)findViewById(R.id.volumeMinus);
        volumePlus = (Button)findViewById(R.id.volumePlus);
        flushDnsButton = (Button)findViewById(R.id.renew);
        reload = (Button)findViewById(R.id.reload);
        channels = (Button)findViewById(R.id.channels);
        brightUp = (Button) findViewById(R.id.brightUp);
        brightDown = (Button) findViewById(R.id.brightDown);
    }

    public void loadServerAddressFromFile(){
        File sdcard = Environment.getExternalStorageDirectory();

        File file = new File(sdcard,serverFile);

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
        serverAddress = text.toString();
    }

    public void writeToFile(){
        try {
            FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + serverFile);
            fos.write(serverAddress.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendPostRequest(String command) {
        this.command = command;
        new PostClass(this).execute();
    }

    public void setSendButtonAction(){


        shutdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int time = Integer.valueOf(et.getText().toString()) * 60;

            sendPostRequest("shutdown:-s -t " + time);
            Toast.makeText(getApplicationContext(), "Setting shutdown", Toast.LENGTH_LONG).show();
            }
        });


        cancelShutdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendPostRequest("shutdown:-a");
                Toast.makeText(getApplicationContext(), "Cancelling shutdown", Toast.LENGTH_LONG).show();
            }
        });




        flushDnsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("ipconfig:-renew");
                Toast.makeText(getApplicationContext(), "Sending flushdns", Toast.LENGTH_LONG).show();
            }
        });

        volumePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("volume:+");
            }
        });



        volumeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("volume:-");
            }
        });

        brightUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("brightness:+");
            }
        });

        brightDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("brightness:-");
            }
        });

        toggleFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("togglefullscreen:dummy");
            }
        });


        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest("reload:dummy");
            }
        });

        saveIpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverAddress = serverIpInput.getText().toString();
                writeToFile();

                Toast.makeText(getApplicationContext(), "File Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        channels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.instance, ChannelListActivity.class);
                startActivity(intent);
            }
        });


    }

    public void setCommand(String command) {
        this.command = command;
    }

    private class PostClass extends AsyncTask<String, Void, Void> {

        private final Context context;

        public PostClass(Context c){
            this.context = c;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {


                URL url = new URL("http://" + serverAddress + ":8000");

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setConnectTimeout(30000);    // 30 segs de timeout
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.write(command.getBytes());
                dStream.flush();
                dStream.close();
                int responseCode = connection.getResponseCode();

                final StringBuilder output = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();

                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }
                br.close();

                output.append(responseOutput.toString());

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.instance.getApplicationContext(), "Response: " + output, Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute() {
            Toast.makeText(MainActivity.instance.getApplicationContext(), "Sending...", Toast.LENGTH_SHORT).show();
        }

    }
}
