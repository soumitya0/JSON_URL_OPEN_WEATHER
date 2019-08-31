package com.example.a2json_from_url_open_weather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {


    Button click;
    public static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.tv);
        click=(Button)findViewById(R.id.btn);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsynTaskDownload asynTaskDownload= new AsynTaskDownload();
                asynTaskDownload.execute();
            }
        });
    }


    class AsynTaskDownload extends AsyncTask<Void,Void,Void>
    {


        String data="";
        @Override
        protected Void doInBackground(Void... voids) {

            URL url = null;
            try {
                url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Kolkata&units=metric&APPID=0e948b3ecd877b785634d8ebdd15621e");

                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();

                BufferedReader bufferedReader= new BufferedReader( new InputStreamReader(inputStream));

                String line="";
                while (line!=null)
                {
                    line=bufferedReader.readLine();
                    data= data+line;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            MainActivity.tv.setText(this.data);
        }
    }
}


