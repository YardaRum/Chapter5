package com.example.person.controller;


import com.example.person.model.Location;
import com.example.person.model.Person;
import com.example.person.model.Weather;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/{PersonId}/weather")
    public Weather findWeatherByPersonId(@PathVariable int PersonId){
        Person person = repository.findById(PersonId).get();
        Location location = restTemplate.getForObject("http://localhost:8082/location/name/" + person.getLocationName(), Location.class);
        Weather weather = restTemplate.getForObject(String.format("http://localhost:8082/location/%d/weather", location.getId()), Weather.class);
        return weather;
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }


}
