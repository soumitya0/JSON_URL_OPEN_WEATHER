package com.example.json_from_url_open_weather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    click=(Button)findViewById(R.id.btn);
    tv=(TextView)findViewById(R.id.tv);

    click.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            task task= new task();
            task.execute();

        }
    });
    }


    class task extends AsyncTask<Void,Void,Void>
    {

        String data="";

        String dataParsed="";
        String SingleParsed="";

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                URL url= new URL("https://api.myjson.com/bins/zo7a7");

                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                InputStream inputStream= httpURLConnection.getInputStream();

                BufferedReader bufferedReader= new BufferedReader( new InputStreamReader(inputStream));

                String line="";
                while (line!=null)
                {
                    line=bufferedReader.readLine();
                    data= data+line;
                }

                JSONArray jsonArray= new JSONArray(data);
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=(JSONObject) jsonArray.get(i);

                    SingleParsed="Name:"+jsonObject.get("firstName")+"\n"+
                            "LastName:"+jsonObject.get("lastName")+"\n"+"\n";

                    dataParsed =dataParsed+SingleParsed;


                }

            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (JSONException e)
            {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            MainActivity.tv.setText(this.dataParsed);
        }
    }
}
