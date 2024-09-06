package com.example.location.controller;

import com.example.location.model.Location;
import com.example.location.model.Weather;
import com.example.location.repository.LocationRepository;
import org.slf4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Iterable<Location> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> findById(@PathVariable int id){
        return repository.findById(id);
    }

    @GetMapping("/name/{city}")
    public Optional<Location> findByCity(@PathVariable String city){
        return repository.findByCity(city);
    }

    @GetMapping("/{locId}/weather")
    public Weather findWeatherByLocationId(@PathVariable int locId){
        Location location = repository.findById(locId).get();
        return restTemplate.getForObject(String.format(Locale.ROOT, "http://localhost:8081/weather/%f/%f",location.getLatitude(),location.getLongitude()), Weather.class);
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location){
        return repository.findById(location.getId()).isPresent()
                ? new ResponseEntity(repository.findById(location.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(repository.save(location),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable int id){
        repository.deleteById(id);
    }

}
