package com.tts.weatherApp.respository;

import com.tts.weatherApp.model.WeatherSearch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherSearchRepo extends CrudRepository<WeatherSearch, Long> {
//    List<WeatherSearch> findByZipCode(String zipCode);
}
