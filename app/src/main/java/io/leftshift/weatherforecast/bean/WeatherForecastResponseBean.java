package io.leftshift.weatherforecast.bean;

import java.util.ArrayList;

/**
 * Created by Shahid on 3/18/2016.
 */
public class WeatherForecastResponseBean {
    public CityBean city;
    public String cod;
    public float message;
    public int cnt;
    public ArrayList<DayWeatherBean> list;
}
