package com.example.mehme.haberappk;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class MainActivity extends AppCompatActivity {

    ServiceManager serviceManager;

   // Button btnHit;
   // TextView txtJson;
    ProgressDialog pd;
    List<Haber> listContents;// = new ArrayList<String>(length);
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       myListView = (ListView) findViewById(R.id.mylist);
     /*   serviceManager=new ServiceManager();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
             //  serviceManager.BegenmeUpdate("1","5");
                serviceManager.HaberListesi("%");
            }
        });

        thread.start(); */


            new JsonTask().execute("http://172.20.10.2/WebServiceXML-JSON/WebService1.asmx/GetHaber");
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                JSONObject jsonObj = null;
                try {
                    jsonObj = XML.toJSONObject(buffer.toString());
                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }

                Log.d("XML", buffer.toString());

                Log.d("JSON", jsonObj.toString());

                JSONObject temp = jsonObj.getJSONObject("string");
               // JSONArray tempr =  temp.getJSONArray("content");


                Log.d("temp ::: ", temp.getString("content"));
                JSONArray jsonArray = new JSONArray(temp.getString("content"));

                    Log.d("temp 2 ::" , jsonArray.get(0).toString());


                Haber[] items = new Gson().fromJson(temp.getString("content"), Haber[].class);
                listContents = new ArrayList<>(Arrays.asList(items));

                return jsonArray.get(0).toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        private void parseXML() {
            XmlPullParserFactory parserFactory;
            try {
                parserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = parserFactory.newPullParser();
                InputStream is = getAssets().open("data.xml");
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(is, null);

                //processParsing(parser);

            } catch (XmlPullParserException e) {

            } catch (IOException e) {
            }
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }

            myListView.setAdapter(new Adapter(MainActivity.this, listContents));

          //  Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
          //  txtJson.setText(result);
        }
    }



}
