package io.leftshift.weatherforecast.connection.http;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

import io.leftshift.weatherforecast.bean.LatLongCoordBean;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.util.AppConstants;

/**
 * Created by shahid on 10/9/15.
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

        logger.debug("City Latlong weather Request URL : "+requestUrl);

        return new JsonObjectRequest(requestUrl, onResponseListener, volleyErrorListener);
    }


/*
    public JsonObjectRequest saveCareGiverDetails(String email,
                                                   final String authToken,
                                                   Response.Listener<JSONObject> onResponseListener,
                                                   Response.ErrorListener volleyErrorListener){

        String requestUrl = AppConstants.URL.SERVER_URL+AppConstants.URL.SAVE_MOBILE_DETAILS;

        DeviceDataModel  deviceDataModel = DeviceUtil.deviceFeatures(context);
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put(AppConstants.RequestParams.EMAIL, email);
            jsonObject.put(AppConstants.RequestParams.MANUFACTURER_NAME, deviceDataModel.deviceManufacturer);
            jsonObject.put(AppConstants.RequestParams.DEVICE_NAME, deviceDataModel.deviceModel);
            jsonObject.put(AppConstants.RequestParams.DEVICE_OS, deviceDataModel.deviceOS);
            jsonObject.put(AppConstants.RequestParams.OS_VERSION, deviceDataModel.osVersion);
            jsonObject.put(AppConstants.RequestParams.AUTHTOKEN, authToken);
        }catch (Exception e){
            logger.error(e);
        }

        JsonObjectRequest requestObject = new JsonObjectRequest(Request.Method.GET, requestUrl, jsonObject,
                onResponseListener, volleyErrorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap();
                params.put("Content-Type", "Application/json");
                params.put(AppConstants.RequestParams.AUTHTOKEN, authToken);

                return params;
            }
        };

        return requestObject;
    }

    public JsonObjectRequest getNotitificationRequest(NotificationRequestModel notificationModel,
                                                      final String authToken,
                                             Response.Listener<JSONObject> onResponseListener,
                                             Response.ErrorListener volleyErrorListener){

        String requestUrl = AppConstants.URL.SERVER_URL+AppConstants.URL.NOTIFICATION_PATH;

        JSONObject requestJsonObject = new JSONObject();
        try{
            requestJsonObject.put(AppConstants.RequestParams.USERID, notificationModel.userModel.user_id);
            requestJsonObject.put(AppConstants.RequestParams.USEREMAIL, notificationModel.userModel.email_id);
            requestJsonObject.put(AppConstants.RequestParams.DATA, "Test Data");
            requestJsonObject.put(AppConstants.RequestParams.NOTIFICATION_TYPE, notificationModel.notificationType);

            if(!TextUtils.isEmpty(notificationModel.gpsModel.latitude) &&
                    TextUtils.isEmpty(notificationModel.gpsModel.longitude)){
                JSONObject locationJsonObject = new JSONObject();
                locationJsonObject.put(AppConstants.RequestParams.LATITUDE, notificationModel.gpsModel.latitude);
                locationJsonObject.put(AppConstants.RequestParams.LONGITUDE, notificationModel.gpsModel.longitude);

                requestJsonObject.put(AppConstants.RequestParams.GPS_COORDS, locationJsonObject);

            }

            if(notificationModel.contactList.size() > 0){
                JSONArray contactArray = new JSONArray();
                for(EmergencyContactModel contactModel : notificationModel.contactList){
                    JSONObject contactJsonObject = new JSONObject();
                    contactJsonObject.put(AppConstants.RequestParams.NAME, contactModel.name);
                    contactJsonObject.put(AppConstants.RequestParams.EMAILID, contactModel.email_id);
                    contactJsonObject.put(AppConstants.RequestParams.MOBILE, contactModel.mobile);

                    contactArray.put(contactJsonObject);
                }
                if(contactArray!=null){
                    requestJsonObject.put(AppConstants.RequestParams.EMEREGENCY_CONTACT, contactArray);
                }
            }
        }catch (Exception e){
            logger.error(e);
        }

        JsonObjectRequest requestObject = new JsonObjectRequest(requestUrl, requestJsonObject,
                onResponseListener, volleyErrorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap();
                params.put("Content-Type", "Application/json");
                params.put(AppConstants.RequestParams.AUTHTOKEN, authToken);

                return params;
            }
        };
        return requestObject;
    }

    public JsonObjectRequest getShortenUrlRequest(String url,
                                                  Response.Listener<JSONObject> onResponseListener,
                                                  Response.ErrorListener volleyErrorListener) {

        String requestUrl = AppConstants.URL.GOOGLE_SHORTEN_URL + AppConstants.ApiKeys.GOOGLE_SHORTEN_URL_API_KEY;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConstants.RequestParams.LONG_URL, url);
        } catch (Exception e) {
            logger.error(e);
        }

        JsonObjectRequest requestObject = new JsonObjectRequest(requestUrl, jsonObject,
                onResponseListener, volleyErrorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap();
                params.put("Content-Type", "Application/json");

                return params;
            }
        };

        return requestObject;
    }*/

}
