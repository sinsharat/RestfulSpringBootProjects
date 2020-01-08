package com.sharat.restfulwebservice.mybatis.example;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CityMapper {

	List<City> findAllCities();
	
	@Select("select * from city where statename = #{stateName}")
	@Results({
		@Result(property = "cityId", column = "cityid"),
        @Result(property = "cityName", column = "cityname"),
        @Result(property = "stateName", column = "statename")
    })
	City findByStateName(@Param("stateName") String stateName);
	
	@Select("select * from city where cityid = #{cityId}")
	@Results({
		@Result(property = "cityId", column = "cityid"),
        @Result(property = "cityName", column = "cityname"),
        @Result(property = "stateName", column = "statename")
    })
	City findByCityId(@Param("cityId") long cityId);

	@Insert("insert into city (statename, cityname) values (#{stateName}, #{cityName})")
	@Options(useGeneratedKeys=true, keyProperty="cityId")
	void insert(City city);
	
	@Update("update city set cityname = ifnull(#{cityName}, cityname), statename = ifnull(#{stateName}, statename) where cityId = #{cityId}")
	void update(City city);
	
	@Delete("delete from city where cityid = #{cityId}")
	void delete(@Param("cityId") long cityId);
	
}
