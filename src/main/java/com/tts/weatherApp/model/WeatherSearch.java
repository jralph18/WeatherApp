package com.tts.weatherApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String zipCode;
    private String cityName;
    private String temperature;
    private String cloudCover;
    private String wind;
    private String conditions;

    public WeatherSearch(String zipCode, String cityName, String temperature, String cloudCover, String wind, String conditions) {
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.temperature = temperature;
        this.cloudCover = cloudCover;
        this.wind = wind;
        this.conditions = conditions;
    }
}
