package io.leftshift.weatherforecast.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.leftshift.weatherforecast.R;
import io.leftshift.weatherforecast.bean.DayWeatherBean;
import io.leftshift.weatherforecast.logger.Logger;

/**
 * Created by Shahid on 3/19/2016.
 */
public class DaysRecyclerViewAdapter extends RecyclerView
        .Adapter<DaysRecyclerViewAdapter
        .DataObjectHolder> {

    private static final Logger logger = new Logger(DaysRecyclerViewAdapter.class.getName());

    private ArrayList<DayWeatherBean> dayWeatherBeans;
    private ArrayList<String> dates;
    private Context context;

    public DaysRecyclerViewAdapter(ArrayList<DayWeatherBean> dayWeatherBeans, ArrayList<String> date,
                                   Context context) {
        this.dayWeatherBeans = dayWeatherBeans;
        this.dates = date;
        this.context = context;
    }

    @Override
    public DaysRecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_weather_forecast, parent, false);

        DaysRecyclerViewAdapter.DataObjectHolder dataObjectHolder = new DaysRecyclerViewAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DaysRecyclerViewAdapter.DataObjectHolder holder, int position) {
        DayWeatherBean dayWeather = dayWeatherBeans.get(position);
        holder.tvDate.setText(dates.get(position));
        holder.tvWeatherCondition.setText(dayWeather.weather.get(0).description);

        holder.tvMinTemp.setText(context.getString(R.string.default_min_temp, dayWeather.temp.min));
        holder.tvMaxTemp.setText(context.getString(R.string.default_max_temp, dayWeather.temp.max));
        holder.tvHumidity.setText(context.getString(R.string.default_humidity, dayWeather.humidity));
        holder.tvPressure.setText(context.getString(R.string.default_pressure, dayWeather.pressure));

        try{
            int identifier = getIdentifierByKey(dayWeather.weather.get(0).icon);
            if(identifier!=0){
                holder.ivWeatherCondition.setImageResource(identifier);
            }
        }catch (Exception e){
            logger.error(e);
        }

    }

    @Override
    public int getItemCount() {
        return dayWeatherBeans.size();
    }

    private int getIdentifierByKey(String imgName){
        int identifier = -1;
        try{
            String subString = imgName.substring((imgName.length()-1), imgName.length());
            imgName = subString + imgName.substring(0, (imgName.length()-1));
            logger.debug("Image Name - "+imgName);
            identifier = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        }catch(Exception e){
            logger.error(e);
        }
        logger.debug("Identifier - "+identifier);
        return identifier;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvWeatherCondition;
        TextView tvMinTemp;
        TextView tvMaxTemp;
        TextView tvHumidity;
        TextView tvPressure;
        ImageView ivWeatherCondition;

        public DataObjectHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvWeatherCondition = (TextView) itemView.findViewById(R.id.tv_weather_condition);
            tvMinTemp = (TextView) itemView.findViewById(R.id.tv_min_temp);
            tvMaxTemp = (TextView) itemView.findViewById(R.id.tv_max_temp);
            tvHumidity = (TextView) itemView.findViewById(R.id.tv_humidity);
            tvPressure = (TextView) itemView.findViewById(R.id.tv_pressure);
            ivWeatherCondition = (ImageView) itemView.findViewById(R.id.iv_weather_icon);
        }
    }
}