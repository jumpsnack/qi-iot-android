package com.example.jumpsnack.eddie_json_dynamic;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JUMPSNACK on 2016-07-16.
 */
public class PageAdapter extends PagerAdapter {

    /* Widgets */
    TextView textTime;
    TextView textCo2;
    TextView textCo;
    TextView textSo2;
    TextView textNo2;
    TextView textFineDust;
    TextView textO3;
    View viewActivityData;

    /* JSON var */
    JSONObject airData; /* To save data at every single time */

    /* Reference var */
    private LayoutInflater pageInflater; /* To get activity_data.xml */
    private JsonController jsonController; /* To get JSON data */


    /* Constructor */
    public PageAdapter(Context c, String jsonContents) {
        super();
        this.jsonController = new JsonController(jsonContents);
        this.pageInflater = LayoutInflater.from(c);

        if(!this.jsonController.getJsonDataParsing()){
            System.exit(0);
        }
    }


    /* Set air data to widgets at every single time */
    void setWidgetsData(JSONObject airData, int position) {
        try {
            initWidgets();

            /* Set data from JSON */
            textTime.setText("< T" + position + " : " + airData.getString("time") + " >");
            textCo2.setText(airData.getString("co2"));
            textCo.setText(airData.getString("co"));
            textSo2.setText(airData.getString("so2"));
            textNo2.setText(airData.getString("no2"));
            textFineDust.setText(airData.getString("pm2.5"));
            textO3.setText(airData.getString("o3"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Initialize widgets */
    void initWidgets() {
        textTime = (TextView) viewActivityData.findViewById(R.id.tv_data_time);
        textCo2 = (TextView) viewActivityData.findViewById(R.id.tv_data_co2);
        textCo = (TextView) viewActivityData.findViewById(R.id.tv_data_co);
        textSo2 = (TextView) viewActivityData.findViewById(R.id.tv_data_so2);
        textNo2 = (TextView) viewActivityData.findViewById(R.id.tv_data_no2);
        textFineDust = (TextView) viewActivityData.findViewById(R.id.tv_data_fine_dust);
        textO3 = (TextView) viewActivityData.findViewById(R.id.tv_data_o3);
    }


    @Override
    /* Set number of pages */
    public int getCount() {
        return jsonController.totalRows;
    }

    @Override
    /* Set page contents when pages are changed by user */
    public Object instantiateItem(View container, int position) {
        airData = jsonController.airData.get(position); /* Get air data for single time */
        viewActivityData = pageInflater.inflate(R.layout.activity_data, null); /* Set activity_data.xml */
        setWidgetsData(airData, position); /* Set data to widgets */
        ((ViewPager) container).addView(viewActivityData, 0); /* Set activity to viewPager */

        return viewActivityData;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
