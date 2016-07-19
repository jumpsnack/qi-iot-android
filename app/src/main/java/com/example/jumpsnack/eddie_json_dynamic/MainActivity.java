package com.example.jumpsnack.eddie_json_dynamic;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    /* Constants Var */
    private final String JSON_FILE_NAME = "sensordata2.json";

    /* Reference Var */
    InputStream jsonInputStream;

    /* Variables */
    Vector<String> Elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main_ground);

        PageAdapter adapter = new PageAdapter(this, getJsonFile());

        viewPager.setAdapter(adapter);

        setActionBarTitle(JsonController.jsonDataSensorInfo);
    }

    /* Get JSON file */
    public String getJsonFile() {
        String jsonContents = null; /* Variable for saving original JSON converted to String */

        /* Read JSON file from "assets" DIR */
        try {
            jsonInputStream = getAssets().open(JSON_FILE_NAME);
            int size = jsonInputStream.available();
            byte[] buffer = new byte[size];
            jsonInputStream.read(buffer);
            jsonInputStream.close();
            jsonContents = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonContents;
    }

    /* Set action bar title using JSON data */
    public void setActionBarTitle(JSONObject jsonDataSensorInfo) {
        try {
            setTitle(jsonDataSensorInfo.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
