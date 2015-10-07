package com.silion.jsonsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2015/8/25.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button jsonBtn = (Button) findViewById(R.id.readJson);
        jsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Information> informations = new ArrayList<Information>();
                try {
                    InputStream in = getResources().getAssets().open("data.txt");
                    informations = readJsonStream(in);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(Information inf : informations) {
                    android.util.Log.v("slong.liang", "inf = " + inf);
                }
            }
        });
    }

    public List readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List readMessagesArray(JsonReader reader) throws IOException {
        List<Information> informations = new ArrayList<Information>();
        reader.beginArray();
        while (reader.hasNext()) {
            informations.add(readMessage(reader));
        }
        reader.endArray();
        return informations;
    }

    public Information readMessage(JsonReader reader) throws IOException {
        Information information = new Information();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if(name.equals("id")) {
                information.setId(reader.nextLong());
            } else if(name.equals("text")) {
                information.setText(reader.nextString());
            } else if(name.equals("geo") && reader.peek() != JsonToken.NULL) {
                information.setGeo(readDoubleArray(reader));
            } else if(name.equals("user")) {
                information.setUser(readUser(reader));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return information;
    }

    public double[] readDoubleArray(JsonReader reader) throws IOException {
        double[] geo = new double[50];
        int i = 0;
        reader.beginArray();
        while (reader.hasNext()) {
            geo[i++] = reader.nextDouble();
        }
        reader.endArray();
        return geo;
    }

    public Information.User readUser(JsonReader reader) throws IOException {
        Information.User user = new Information.User();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            if(name.equals("name")) {
                user.setName(reader.nextString());
            } else if(name.equals("followers_count")) {
                user.setCf(reader.nextInt());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return user;
    }
}
