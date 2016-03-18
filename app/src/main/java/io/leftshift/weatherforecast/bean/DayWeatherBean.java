package io.leftshift.weatherforecast.bean;

import java.util.ArrayList;

/**
 * Created by Shahid on 3/18/2016.
 */
public class DayWeatherBean {
    public long dt;
    public TemperatureBean temp;
    public float pressure;
    public int humidity;
    public ArrayList<WeatherBean> weather;
    public float speed;
    public int deg;
    public int clouds;
}
