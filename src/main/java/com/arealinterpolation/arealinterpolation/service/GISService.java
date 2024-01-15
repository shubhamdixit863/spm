package com.arealinterpolation.arealinterpolation.service;

import com.arealinterpolation.arealinterpolation.dto.GisRequest;
import com.arealinterpolation.arealinterpolation.repository.GISRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GISService {
    @Autowired
    private GISRepository gisRepository;

    public Map<String, Object> getDemographicsData(GisRequest gisRequest){
        return gisRepository.getDemographics(gisRequest.latitude,gisRequest.longitude,gisRequest.distance);
    }
}
