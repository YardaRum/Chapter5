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
public class Location {
    @Id
    @GeneratedValue
    private int id;

    private String city;
    @NonNull
    private String countryCode;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;

    public Location( String city, @NonNull String countryCode, @NonNull Double longitude, @NonNull Double latitude) {
        this.city = city;
        this.countryCode = countryCode;
        this.longitude = longitude;
        this.latitude = latitude;

    }
}
