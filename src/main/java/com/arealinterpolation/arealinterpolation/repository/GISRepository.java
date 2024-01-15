package com.arealinterpolation.arealinterpolation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class GISRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getDemographics(double latitude, double longitude, double distance) {
        String sql = "SELECT SUM(d.population) AS total_population, ROUND(AVG(d.income),2) AS average_income " +
                "FROM dfw_demo d " +
                "WHERE ST_DWithin(ST_SetSRID(ST_MakePoint(?, ?), 4326), d.SpatialObj, ?) " +
                "AND ST_Intersects(d.SpatialObj, ST_Buffer(ST_SetSRID(ST_MakePoint(?, ?), 4326), ?))";

        return jdbcTemplate.queryForMap(sql, longitude, latitude, distance, longitude, latitude, distance);
    }


}
