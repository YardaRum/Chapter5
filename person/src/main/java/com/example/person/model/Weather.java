package com.example.person.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weather {
    @Id
    @GeneratedValue
    private int id;

    private String weatherType;
    @NonNull
    private int temperature;
    @NonNull
    private String latitude;
    @NonNull
    private String longitude;

    public Weather( String weatherType, @NonNull int temperature, @NonNull String latitude, @NonNull String longitude) {
        this.weatherType = weatherType;
        this.temperature = temperature;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
