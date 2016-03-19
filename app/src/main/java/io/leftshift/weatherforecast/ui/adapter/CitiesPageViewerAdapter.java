package io.leftshift.weatherforecast.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.leftshift.weatherforecast.bean.WeatherForecastResponseBean;
import io.leftshift.weatherforecast.logger.Logger;
import io.leftshift.weatherforecast.ui.fragment.CityForecastFragment;

public class CitiesPageViewerAdapter extends FragmentPagerAdapter {

    private ArrayList<WeatherForecastResponseBean> citiesWeatherBean;
    private static int mCount = 0;
 	private Context context;
    private static final Logger logger = new Logger(CitiesPageViewerAdapter.class.getName());


    public CitiesPageViewerAdapter(FragmentManager fm, ArrayList<WeatherForecastResponseBean> citiesWeatherBean,
                                   Context context) {
        super(fm);
        this.citiesWeatherBean = citiesWeatherBean;
        this.context=context;
        mCount = this.citiesWeatherBean.size();
    }

    
    @Override
    public Fragment getItem(int position) {
        WeatherForecastResponseBean cityForecastBean = citiesWeatherBean.get(position);
    	logger.debug("City Name : "+cityForecastBean.city.name);

    	return CityForecastFragment.newInstance(cityForecastBean, this.context);
    }

    @Override
    public int getCount() {
        return this.citiesWeatherBean.size();
    }
}