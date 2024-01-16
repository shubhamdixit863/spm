package com.arealinterpolation.arealinterpolation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GISRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Map<String, Object> getDemographics(double latitude, double longitude, double distance) {
        String sql = "SELECT SUM(population) AS TotalPopulation, AVG(income) AS AverageIncome " +
                "FROM dfw_demo " +
                "WHERE ST_Intersects(SpatialObj, ST_Buffer(ST_SetSRID(ST_MakePoint(?, ?), 4326), ?))";

        return jdbcTemplate.queryForMap(sql, longitude, latitude, distance);
    }


    public List<String> getSpatialObjAsGeoJSON() {
        String sql = "SELECT ST_AsGeoJSON(SpatialObj) AS geojson FROM dfw_demo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("geojson"));
    }



}
