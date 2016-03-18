package io.leftshift.weatherforecast.preferences;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;

import io.leftshift.weatherforecast.bean.WeatherForecastResponseBean;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.util.AppConstants;


/**
 * Created by shahid on 16/3/15.
 */
public class WeatherPreference extends PrefManager{

    private static final Logger logger = new Logger(WeatherPreference.class.getName());

    public WeatherPreference(Context context) {
        super(context, AppConstants.PrefClass.WEATHER_DATA);
    }

    /**
     * Returns weather forecast of 14 days for the city passed.
     * @return - WeatherForecastResponseBean created from the json string stored in shared preferences
     */
    public WeatherForecastResponseBean getCityWeather(String cityName) {
        Gson gson = new Gson();
        String jsonString = getString(cityName, "");
        WeatherForecastResponseBean weatherBean = null;
        try{
            weatherBean = gson.fromJson(jsonString, WeatherForecastResponseBean.class);
        }catch(Exception exception){
            logger.error(exception);
        }

        return weatherBean;
    }

    /**
     * Put the weather forecast of 14 days for the city passed in shared preference
     * @param cityName - City name in string format
     * @param weatherJson - json string fetched from the server
     */
    public void setCityWeather(String cityName, String weatherJson) {
        put(cityName, weatherJson);
    }

    /**
     * Clears all cities json data from the shared preference as the user has pressed back
     * @param cityList - ArrayList of string of all the cities entered by the user
     */
    public void clearCitiesData(ArrayList<String> cityList) {
        for (String city: cityList){
            removePreference(city);
        }
    }

    /**
     * Clears the city json data from shared preference as the user has pressed back
     * @param cityName - string of city entered by the user
     */
    public void clearCityData(String cityName) {
        removePreference(cityName);
    }


}
