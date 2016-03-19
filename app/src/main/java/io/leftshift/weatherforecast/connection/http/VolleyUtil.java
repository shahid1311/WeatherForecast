package io.leftshift.weatherforecast.connection.http;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import io.leftshift.weatherforecast.bean.LatLongCoordBean;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.util.AppConstants;

/**
 * Created by shahid on 3/15/16.
 */
public class VolleyUtil {

    private static final Logger logger = new Logger(VolleyUtil.class.getName());

    public VolleyUtil(){
        //Empty Constructor
    }

    public JsonObjectRequest getCityWeather(String city,
                               Response.Listener<JSONObject> onResponseListener,
                               Response.ErrorListener volleyErrorListener){

        String requestUrl = AppConstants.URL.SERVER_URL+AppConstants.URL.CITY_WEATHER_NAME_PATH
                +AppConstants.QUERY_PARAMS.CITY_NAME+"="+city
                +"&"+AppConstants.QUERY_PARAMS.COUNT+"="+AppConstants.QUERY_PARAMS.NO_OF_DAYS
                +"&"+AppConstants.QUERY_PARAMS.APP_ID+"="+AppConstants.ApiKeys.APP_ID
                +"&"+AppConstants.QUERY_PARAMS.UNITS+"="+AppConstants.QUERY_PARAMS.METRIC;

        logger.debug("City weather Request URL : "+requestUrl);

        return new JsonObjectRequest(requestUrl, onResponseListener, volleyErrorListener);
    }

    public JsonObjectRequest getCityWeatherFromLatLong(LatLongCoordBean latLongBean,
                                            Response.Listener<JSONObject> onResponseListener,
                                            Response.ErrorListener volleyErrorListener){

        String requestUrl = AppConstants.URL.SERVER_URL+AppConstants.URL.CITY_WEATHER_NAME_PATH
                +AppConstants.QUERY_PARAMS.LATITUDE+"="+latLongBean.lat
                +"&"+AppConstants.QUERY_PARAMS.LONGITUDE+"="+latLongBean.lon
                +"&"+AppConstants.QUERY_PARAMS.COUNT+"="+AppConstants.QUERY_PARAMS.NO_OF_DAYS
                +"&"+AppConstants.QUERY_PARAMS.APP_ID+"="+AppConstants.ApiKeys.APP_ID
                +"&"+AppConstants.QUERY_PARAMS.UNITS+"="+AppConstants.QUERY_PARAMS.METRIC;

        logger.debug("City Lat long weather Request URL : "+requestUrl);

        return new JsonObjectRequest(requestUrl, onResponseListener, volleyErrorListener);
    }
}
