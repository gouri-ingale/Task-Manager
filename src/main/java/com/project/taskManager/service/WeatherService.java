package com.project.taskManager.service;

import com.project.taskManager.api.response.WeatherResponse;
import com.project.taskManager.cache.AppCache;
import com.project.taskManager.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" +city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY,city).replace(PlaceHolders.API_KEY,apiKey);

//        null mhanje request entity like apan kay header vagere send krto aahe ka, hya case madhe null aahe
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

//        the process of converting the json response to the corresponding java object (POJO) is called deserilization

            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
}
