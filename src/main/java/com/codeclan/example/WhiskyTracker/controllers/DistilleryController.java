package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(
            @RequestParam(name="region", required=false) String region
    ){ if (region != null){
        return new ResponseEntity<List<Distillery>>(distilleryRepository.findByRegion(region), HttpStatus.OK);
    }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistillery(@PathVariable Long id){
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}/whiskies")
    public ResponseEntity getDistilleriesByWhiskyAge(@RequestParam(name="age") int age){
        List<Distillery> foundWhiskies = distilleryRepository.findByWhiskiesAge(age);
        return new ResponseEntity(foundWhiskies, HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity getWhiskiesByYear(@RequestParam(name="year") int year){
        List<Distillery> foundWhiskies = distilleryRepository.findByWhiskiesYearEquals(year);
        return new ResponseEntity(foundWhiskies, HttpStatus.OK);
    }


}
