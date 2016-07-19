package com.example.jumpsnack.eddie_json_dynamic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by JUMPSNACK on 2016-07-14.
 */

/* Control the JSON file, mainly parse */
public class JsonController {

    /*Variables*/
    Vector<JSONObject> airData; /* Save air data for each time as collection */

    JSONObject jsonReader; /* Save original JSON converted to String */
    JSONObject jsonDataSensorData; /* Save sensor data from jsonReader */
    static JSONObject jsonDataSensorInfo; /* Save sensor info from jsonReader */
    JSONArray jsonDataDataArray; /* Save data array from jsonDataSensorData */

    String sensorName; /* Save sensor name from jsonReader */
    int totalRows; /* Save total rows from jsonDataSensorData */


    /* Constructor - Initialize jsonReader & airData collection */
    public JsonController(String jsonContents) {
        /* Initialize some variables */
        try {
            jsonReader = new JSONObject(jsonContents);
            airData = new Vector<>();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Parse data from JSON */
    public boolean getJsonDataParsing() {
        try {

            jsonDataSensorData = jsonReader.getJSONObject("sensorData");
            jsonDataSensorInfo = jsonReader.getJSONObject("sensorInfo");
            jsonDataDataArray = jsonDataSensorData.getJSONArray("dataArray");

            sensorName = jsonDataSensorInfo.getString("name");
            totalRows = Integer.parseInt(jsonDataSensorData.getString("totalRows"));

            /* Check actual number of JSON data <-> totalRows value in the JSON file */
            if(jsonDataDataArray.length() < totalRows)
                return false;

            /* Set each data to vector type of JSONObject*/
            for (int i = 0; i < totalRows; i++)
                airData.add(jsonDataDataArray.getJSONObject(i));

        } catch (JSONException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
