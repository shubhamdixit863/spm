package com.arealinterpolation.arealinterpolation.controllers;

import com.arealinterpolation.arealinterpolation.dto.GisRequest;
import com.arealinterpolation.arealinterpolation.dto.HealthCheck;
import com.arealinterpolation.arealinterpolation.service.GISService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GISController {
    @Autowired
    private GISService gisService;

    @GetMapping("/")
    public ResponseEntity<HealthCheck> healthCheck() {
        var response=new HealthCheck();
        response.setMessage("Service Running");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/gis")
    public ResponseEntity<Map<String,Object>> GisData(@RequestBody GisRequest gisRequest){
        var data=gisService.getDemographicsData(gisRequest);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
