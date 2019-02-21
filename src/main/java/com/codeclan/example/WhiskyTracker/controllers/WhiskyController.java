package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping
    public List<Whisky> getAllWhisky() {
        return whiskyRepository.findAll();
    }

    @GetMapping(value = "/year/{year}")
    public List<Whisky> getAllWhiskiesWithYear(@PathVariable int year) {
        return whiskyRepository.findWhiskiesByYear(year);
    }

    @GetMapping(value = "/distillery/{distilleryId}/age/{age}")
    public List<Whisky> getWhiskyByAgeAndDistillery(@PathVariable Long distilleryId, @PathVariable int age) {
        return whiskyRepository.findWhiskyByAgeAndDistilleryId(age, distilleryId);
    }

    @GetMapping(value = "region/{region}")
    public List<Whisky> getByRegion(@PathVariable String region) {
        return whiskyRepository.findWhiskyByRegion(region);
    }
}
