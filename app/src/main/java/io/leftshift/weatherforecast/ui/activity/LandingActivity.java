package io.leftshift.weatherforecast.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.bean.LatLongCoordBean;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.ui.dialog.CustomProgressDialog;
import io.leftshift.weatherforecast.ui.dialog.DialogFactory;
import io.leftshift.weatherforecast.util.AppConstants;
import io.leftshift.weatherforecast.util.GeoLocation;
import io.leftshift.weatherforecast.util.WeatherUtility;

/**
 * Created by Shahid on 3/13/2016.
 */
public class LandingActivity extends AppCompatActivity {

    private static final Logger logger = new Logger(LandingActivity.class.getName());
    private CustomProgressDialog customProgressDialog;
    private EditText etCityNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        initializeUI();
    }


    private void initializeUI() {
        Button btnLocateMe = (Button) findViewById(R.id.btn_locate_me);
        btnLocateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the device has internet connectivity for fetching data from the server
                if (WeatherUtility.isNetworkConnected(LandingActivity.this)) {
                    requestForLocation();
                } else {
                    Toast.makeText(LandingActivity.this, getString(R.string.not_connected),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        etCityNames = (EditText) findViewById(R.id.et_city_names);

        Button btnDone = (Button) findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSubmitCities();
            }
        });
    }

    /**
     * This method validates useer input in the cities field and then submits it for fetching weather details
     */
    private void validateAndSubmitCities() {
        String cityNames = etCityNames.getText().toString();
        if (cityNames.length() > 0) {
            ArrayList<String> cities = new ArrayList<>();

            //Split city names based on ','
            String[] splitCities = cityNames.split(",");
            for (int i = 0; i < splitCities.length; i++) {
                if (splitCities[i].trim().length() > 0)
                    cities.add(splitCities[i].trim());
            }

            if (cities.size() > 0) {
                //If the city list size  created is greater than 0,
                // then check for network connection and proceed further
                if (WeatherUtility.isNetworkConnected(LandingActivity.this)) {
                    callWeatherForeCastActivity(cities);
                } else {
                    Toast.makeText(LandingActivity.this, getString(R.string.not_connected),
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LandingActivity.this,
                        getString(R.string.enter_valid_cities), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LandingActivity.this,
                    getString(R.string.empty_cities), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method checks if the user has granted permission for getting user's current latitude and longitude
     */
    private void requestForLocation() {
        //First check if the user has already accepted and allowed the Access_coarse_location,
        //if not then request for permission at runtime
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.MY_PERMISSION_ACCESS_COURSE_LOCATION);
        } else {
            //User permission is already validated. Hence get location coords
            locateUser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppConstants.MY_PERMISSION_ACCESS_COURSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    locateUser();

                } else {
                    // permission denied, boo!
                    Toast.makeText(LandingActivity.this,
                            getString(R.string.location_disallowed), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    /**
     * This method fetches the user in Latitude and Longitude and passes it to the next screen
     */
    private void locateUser() {
        final GeoLocation gps = new GeoLocation(LandingActivity.this);
        if (gps.canGetLocation()) {
            LatLongCoordBean latLongBean = new LatLongCoordBean();
            latLongBean.lat = gps.getLatitude();
            latLongBean.lon = gps.getLongitude();
            logger.debug("Latitude : " + latLongBean.lat + " Longitude : " + latLongBean.lon);

            //If the device is unable to fetch the latitude and longitude immediately,
            //then we have a wait preiod before again checking the latitude and longitude
            if (latLongBean.lat == 0) {
                showProgressDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        if (gps.canGetLocation()) {
                            LatLongCoordBean latLongBean = new LatLongCoordBean();
                            latLongBean.lat = gps.getLatitude();
                            latLongBean.lon = gps.getLongitude();
                            logger.debug("Latitude : " + latLongBean.lat + " Longitude : " + latLongBean.lon);
                            callWeatherForeCastActivity(latLongBean);
                        } else {
                            gps.showSettingsAlert();
                        }
                    }
                }, 2000);
            } else {
                callWeatherForeCastActivity(latLongBean);
            }
            logger.debug("Latitude : " + latLongBean.lat + " Longitude : " + latLongBean.lon);
        }
    }

    /**
     * This method calls the Weather forecast activity by passing
     * the lat long coords and displaying the weather data.
     *
     * @param latLongBean - LatLongCoordBean containing the user's current lat and long.
     */
    private void callWeatherForeCastActivity(LatLongCoordBean latLongBean) {
        Intent weatherIntent = new Intent(LandingActivity.this, WeatherForecastActivity.class);
        weatherIntent.putExtra(AppConstants.IS_LOCATION_BASED, true);
        weatherIntent.putExtra(AppConstants.USER_LATLONG, latLongBean);
        startActivity(weatherIntent);
    }

    /**
     * This method calls the Weather forecast activity by passing the arraylist of city names
     *
     * @param cities - ArrayList<String> of the cities entered by the user
     */
    private void callWeatherForeCastActivity(ArrayList<String> cities) {
        Intent weatherIntent = new Intent(LandingActivity.this, WeatherForecastActivity.class);
        weatherIntent.putExtra(AppConstants.IS_LOCATION_BASED, false);
        weatherIntent.putStringArrayListExtra(AppConstants.CITYLIST, cities);
        startActivity(weatherIntent);
    }


    /**
     * Show progress dialog while fetching user location
     */
    private void showProgressDialog() {
        customProgressDialog = DialogFactory.createProgressDialog(LandingActivity.this,
                getString(R.string.fetching_user_location));
        customProgressDialog.show();
    }

    /**
     * Hide the progress dialog
     */
    private void hideProgressDialog() {
        if (customProgressDialog != null) {
            customProgressDialog.closeDialog();
        }
    }
}
