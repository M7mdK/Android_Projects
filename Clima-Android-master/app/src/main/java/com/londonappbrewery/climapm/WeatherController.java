package com.londonappbrewery.climapm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class WeatherController extends AppCompatActivity {

    final int REQ_CODE = 123;
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    final String APP_ID = "e72ca729af228beabd5d20e3b7749713";
    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    String LOCATION_PROVIDER = LocationManager.NETWORK_PROVIDER;

    TextView mCityLabel;
    ImageView mWeatherImage;
    TextView mTemperatureLabel;

    LocationManager mLocationManager;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        mCityLabel = (TextView) findViewById(R.id.locationTV);
        mWeatherImage = (ImageView) findViewById(R.id.weatherSymbolIV);
        mTemperatureLabel = (TextView) findViewById(R.id.tempTV);
        ImageButton changeCityButton = (ImageButton) findViewById(R.id.changeCityButton);

        changeCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherController.this, ChangeCityController.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent newCityIntent = getIntent();
        String newCity = newCityIntent.getStringExtra("city");
        if(newCity != null)
            getWeatherForNewCity(newCity);
        else
            getWeatherForCurrentLocation();
    }

    private void getWeatherForNewCity(String newCity){
        RequestParams params = new RequestParams();
        params.put("q" , newCity);
        params.put("appid" , APP_ID);
        letsDoSomeNetworking(params);
    }

    private void getWeatherForCurrentLocation() {
        Toast.makeText(this, "Accessing your current location...", Toast.LENGTH_SHORT).show();
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());

                RequestParams params = new RequestParams();
                params.put("lon" , longitude);
                params.put("lat" , latitude);
                params.put("appid" , APP_ID);
                letsDoSomeNetworking(params);
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { }
            @Override
            public void onProviderEnabled(String provider) { }
            @Override
            public void onProviderDisabled(String provider) { }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION} , REQ_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    private void letsDoSomeNetworking(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL , params , new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode , Header[] headers , JSONObject response){
                WeatherDataModel weatherData = WeatherDataModel.fromJson(response);
                updateUI(weatherData);
                Toast.makeText(WeatherController.this, "Weather loading completed!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int statusCode , Header[] headers , Throwable e , JSONObject response){
                Toast.makeText(WeatherController.this, "Http Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQ_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getWeatherForCurrentLocation();
            }
            else{
                Toast.makeText(getApplicationContext(), "permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateUI(WeatherDataModel weather){
        mTemperatureLabel.setText(weather.getTemp());
        mCityLabel.setText(weather.getCity());

        int resourceID = getResources().getIdentifier(weather.getIconName() , "drawable" , getPackageName());
        mWeatherImage.setImageResource(resourceID);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mLocationManager != null)
            mLocationManager.removeUpdates(mLocationListener);
    }



}
