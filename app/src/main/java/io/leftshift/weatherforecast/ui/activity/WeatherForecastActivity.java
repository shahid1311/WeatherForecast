package io.leftshift.weatherforecast.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.bean.LatLongCoordBean;
import io.leftshift.weatherforecast.bean.WeatherForecastResponseBean;
import io.leftshift.weatherforecast.connection.http.VolleyUtil;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.ui.adapter.CitiesPageViewerAdapter;
import io.leftshift.weatherforecast.ui.dialog.CustomProgressDialog;
import io.leftshift.weatherforecast.ui.dialog.DialogFactory;
import io.leftshift.weatherforecast.util.AppConstants;
import io.leftshift.weatherforecast.util.WeatherApplication;

/**
 * Created by Shahid on 3/13/2016.
 */
public class WeatherForecastActivity extends AppCompatActivity {

    private static final Logger logger = new Logger(WeatherForecastActivity.class.getName());
    private CitiesPageViewerAdapter mAdapter;
    private ViewPager mPager;
    private ArrayList<WeatherForecastResponseBean> citiesForecast;
    private int responseCount = 0;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        initializeUI();

        citiesForecast = new ArrayList<>();

        boolean isLocationBased = getIntent().getBooleanExtra(AppConstants.IS_LOCATION_BASED, false);
        if (!isLocationBased) {
            ArrayList<String> cityList = getIntent().getStringArrayListExtra(AppConstants.CITYLIST);
            fetchCitiesData(cityList);
        } else {
            LatLongCoordBean latLongCoordBean = (LatLongCoordBean) getIntent()
                    .getSerializableExtra(AppConstants.USER_LATLONG);
            fetchCurrentLocation(latLongCoordBean);
        }
    }

    private void initializeUI() {
        mPager = (ViewPager) findViewById(R.id.viewPager);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(getString(R.string.app_name));
        } else {
            //TODO:Handle if actionBar is NULL
            logger.warn("ActionBar is NULL");
        }
    }

    private void fetchCurrentLocation(final LatLongCoordBean latLongCoordBean) {
        if (latLongCoordBean == null) {
            return;
        }

        showProgressDialog();
        Response.Listener<JSONObject> onResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();

                WeatherForecastResponseBean cityForecast = gson.fromJson(response.toString(),
                        WeatherForecastResponseBean.class);
                if (cityForecast != null) {
                    citiesForecast.add(cityForecast);
                }
                //Hide the progress dialog when response has been received for the current location
                hideProgressDialog();
                initializeViewPager();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO - handle Error response
                Toast.makeText(WeatherForecastActivity.this, "Error fetching city data", Toast.LENGTH_SHORT).show();

                //Hide the progress dialog
                hideProgressDialog();
                initializeViewPager();
            }
        };

        VolleyUtil volleyUtil = new VolleyUtil();
        JsonObjectRequest request = volleyUtil.getCityWeatherFromLatLong(latLongCoordBean,
                onResponseListener, errorListener);
        // add the request object to the queue to be executed
        WeatherApplication.getInstance().addToRequestQueue(request);
    }


    private void fetchCitiesData(final ArrayList<String> cityList) {
        if (cityList == null || cityList.size() <= 0) {
            return;
        }

        showProgressDialog();
        Response.Listener<JSONObject> onResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseCount++;
                Gson gson = new Gson();

                WeatherForecastResponseBean cityForecast = gson.fromJson(response.toString(),
                        WeatherForecastResponseBean.class);
                if (cityForecast != null) {
                    citiesForecast.add(cityForecast);
                }
                //Hide the progress dialog when all response has been received for the cities
                if (responseCount == cityList.size()) {
                    initializeViewPager();
                    hideProgressDialog();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseCount++;
                //TODO - handle Error response
                Toast.makeText(WeatherForecastActivity.this, "Error fetching city data", Toast.LENGTH_SHORT).show();

                //Hide the progress dialog when all response has been received for the cities
                if (responseCount == cityList.size()) {
                    initializeViewPager();
                    hideProgressDialog();
                }
            }
        };

        VolleyUtil volleyUtil = new VolleyUtil();
        for (String city : cityList) {
            JsonObjectRequest request = volleyUtil.getCityWeather(city,
                    onResponseListener, errorListener);
            // add the request object to the queue to be executed
            WeatherApplication.getInstance().addToRequestQueue(request);
        }
    }


    private void initializeViewPager() {
        mAdapter = new CitiesPageViewerAdapter(getSupportFragmentManager(), citiesForecast,
                WeatherForecastActivity.this);

        mPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    private void showProgressDialog() {
        customProgressDialog = DialogFactory.createProgressDialog(WeatherForecastActivity.this,
                getString(R.string.fetching_weather));
        customProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (customProgressDialog != null) {
            customProgressDialog.closeDialog();
        }
    }
}
