package io.leftshift.weatherforecast.util;

/**
 * Created by shahid on 31/8/15.
 */
public class AppConstants {

    public static final String CITYLIST = "city_list";
    public static final String IS_LOCATION_BASED = "is_location_based";
    public static final String USER_LATLONG = "user_lat_long";

    public static final class ApiKeys {
        public static final String APP_ID = "12be7936504c00ca7430246833f96e3e";
    }

    public static final class URL {
        public static final String SERVER_URL = "http://api.openweathermap.org/";
        public static final String CITY_WEATHER_NAME_PATH = "data/2.5/forecast/daily?";
    }

    public static final class QUERY_PARAMS {
        public static final String APP_ID = "APP_ID";
        public static final String COUNT = "cnt";
        public static final int NO_OF_DAYS = 14;
        public static final String LATITUDE = "lat";
        public static final String LONGITUDE = "lon";
        public static final String CITY_NAME = "q";
        public static final String UNITS = "units";
        public static final String METRIC = "metric";
    }

    public class PrefClass {
        public static final String WEATHER_DATA = "weather_data";
    }
}
