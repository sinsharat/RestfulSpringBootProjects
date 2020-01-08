package com.sharat.restfulwebservice.mybatis.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

	@Autowired
	private CityMapper cityMapper;
	
	public List<City> queryAllCities()
	{
		return cityMapper.findAllCities();
	}

	public City queryCitiesByState(String stateName)
	{
		return cityMapper.findByStateName(stateName);
	}
	
	public City queryCityByCityId(long cityId)
	{
		return cityMapper.findByCityId(cityId);
	}
	
	public long addCity(City city) {
		cityMapper.insert(city);
		return city.getCityId();
	}
	
	public void updateCity(City city) {
		cityMapper.update(city);
	}
	
	public void deleteCity(long cityId) {
		cityMapper.delete(cityId);
	}
}
