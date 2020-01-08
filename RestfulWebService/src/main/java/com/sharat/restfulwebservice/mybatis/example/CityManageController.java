package com.sharat.restfulwebservice.mybatis.example;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CityManageController {

	@Autowired
	private CityService cityService;

	@GetMapping(path = { "/states/cities" })
	public List<City> getAllCities() {
		return cityService.queryAllCities();
	}

	@GetMapping(path = { "/states/{stateName}/cities" })
	public City getAllCitiesByStateName(@PathVariable String stateName) {
		return cityService.queryCitiesByState(stateName);
	}

	@GetMapping(path = { "/states/cities/{cityid}" })
	public City getCityByCityID(@PathVariable long cityId) {
		return cityService.queryCityByCityId(cityId);
	}

	@PostMapping("/states/cities")
	public ResponseEntity<Long> addCityInfo(@RequestBody City city) {
		cityService.addCity(city);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cityId}").buildAndExpand(city.getCityId())
				.toUri();
		
		return ResponseEntity.created(location).body(city.getCityId());
	}
	
	@PutMapping("/states/cities/{cityId}")
	public void updateCityInfo(@PathVariable long cityId, @RequestBody City city) {
		city.setCityId(cityId);
		cityService.updateCity(city);
	}

	@DeleteMapping("/states/cities/{cityId}")
	public void deleteCityInfo(@PathVariable long cityId) {
		cityService.deleteCity(cityId);
	}
}
