package com.tts.weatherApp.controller;

import com.tts.weatherApp.model.Request;
import com.tts.weatherApp.model.Response;
import com.tts.weatherApp.model.WeatherSearch;
import com.tts.weatherApp.respository.WeatherSearchRepo;
import com.tts.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherSearchRepo weatherSearchRepo;
    private static List<WeatherSearch> searches = new ArrayList<>();

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("request", new Request());
        return "index";
    }

    @PostMapping
    public String postIndex(Request request, Model model) {
        // get response from API using input zip code
        // do nothing if zip invalid
        Response data = weatherService.getForecast(request.getZipCode());
        model.addAttribute("data", data);
        if(data == null || data.getName().equals("error")) return "index";

        // save weather search to db
        WeatherSearch weatherSearch = new WeatherSearch(request.getZipCode(), data.getName(), data.getMain().get("temp"),
                data.getClouds().get("all"), data.getWind().get("speed"), data.getWeather().get(0).get("description"));
        weatherSearchRepo.save(weatherSearch);

        // update searches list from db
        for(WeatherSearch search : weatherSearchRepo.findAll()) {
            if (!searches.contains(search)) searches.add(0, search);
        }
        // pare searches down to ten items and add to model
        if(searches.size() > 10) searches.remove(searches.size() - 1);
        model.addAttribute("searches", searches);
        return "index";
    }
}
