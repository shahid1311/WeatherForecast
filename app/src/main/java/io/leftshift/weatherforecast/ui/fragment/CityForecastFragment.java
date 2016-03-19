package io.leftshift.weatherforecast.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.bean.WeatherForecastResponseBean;
import io.leftshift.weatherforecast.ui.adapter.DaysRecyclerViewAdapter;
import io.leftshift.weatherforecast.util.DateUtil;

public final class CityForecastFragment extends Fragment {

    private WeatherForecastResponseBean mContent;
	private Context context;
    private RecyclerView mRecyclerView;
    private DaysRecyclerViewAdapter mAdapter;

	public static CityForecastFragment newInstance(WeatherForecastResponseBean cityWeatherResponse,Context context) {
		CityForecastFragment fragment = new CityForecastFragment();
		fragment.mContent   = cityWeatherResponse;
		fragment.context= context;
        return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather_forecast, null);
        TextView tvCityName = (TextView) layout.findViewById(R.id.tv_city_name);
        tvCityName.setText(getString(R.string.city_name, mContent.city.name, mContent.city.country));

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new DaysRecyclerViewAdapter(mContent.list, DateUtil.getFutureDates(), context);
        mRecyclerView.setAdapter(mAdapter);

        return layout;
	    
	}

}
