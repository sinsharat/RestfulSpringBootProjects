package com.sharat.restfulwebservice.usermanage;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserManageController {

	@Autowired
	private UserManageService userManageService;

	@GetMapping(path = { "/users" })
	public ResponseEntity<MappingJacksonValue> getAllUsers() {
		ResponseEntity<MappingJacksonValue> response = null;

		List<UserInfo> userInfoList = userManageService.getAllUsers();

		// filtering out password field and gender from query
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userId",
				"username", "name", "password", "gender");

		// this filter id needs to be defined in UserInfo bean as @JsonFilter
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoFilter", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfoList);
		mappingJacksonValue.setFilters(filters);

		if (CollectionUtils.isEmpty(userInfoList)) {
			response = ResponseEntity.noContent().build();
		} else {
			response = ResponseEntity.ok(mappingJacksonValue);
		}

		return response;
	}

	@GetMapping(path = { "/users-filtered" }) // using filters on values
	public ResponseEntity<MappingJacksonValue> getAllUsersWithFilteredFields() {
		ResponseEntity<MappingJacksonValue> response = null;

		List<UserInfo> userInfoList = userManageService.getAllUsers();

		// filtering out password field and gender from query
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userId",
				"username", "name");

		// this filter id needs to be defined in UserInfo bean as @JsonFilter
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoFilter", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfoList);
		mappingJacksonValue.setFilters(filters);

		if (CollectionUtils.isEmpty(userInfoList)) {
			response = ResponseEntity.noContent().build();
		} else {
			response = ResponseEntity.ok(mappingJacksonValue);
		}

		return response;
	}

	@RequestMapping(path = { "/users/{userId}" }, method = RequestMethod.GET)
	public ResponseEntity<MappingJacksonValue> getUserByUserId(@PathVariable long userId) {
		ResponseEntity<MappingJacksonValue> response = null;

		UserInfo userInfo = userManageService.getUser(userId).get();

		// filtering out password field and gender from query
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userId",
				"username", "name", "password", "gender");

		// this filter id needs to be defined in UserInfo bean as @JsonFilter
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoFilter", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfo);
		mappingJacksonValue.setFilters(filters);

		// HATEOAS start
		// This will create a link to go to/display all users
		Resource<UserInfo> resource = new Resource<UserInfo>(userInfo);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		// HATEOAS end

		if (null == userInfo) {
			response = ResponseEntity.noContent().build();
		} else {
			response = ResponseEntity.ok(mappingJacksonValue);
		}

		return response;
	}

	@RequestMapping(path = { "/users-filtered/{userId}" }, method = RequestMethod.GET)
	public ResponseEntity<MappingJacksonValue> getUserByUserIdWithFilteredFields(@PathVariable long userId) {
		ResponseEntity<MappingJacksonValue> response = null;

		UserInfo userInfo = userManageService.getUser(userId).get();

		// filtering out password field and gender from query
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userId",
				"username", "name");

		// this filter id needs to be defined in UserInfo bean as @JsonFilter
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoFilter", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userInfo);
		mappingJacksonValue.setFilters(filters);

		// HATEOAS start
		// This will create a link to go to/display all users
		Resource<UserInfo> resource = new Resource<UserInfo>(userInfo);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		// HATEOAS end

		if (null == userInfo) {
			response = ResponseEntity.noContent().build();
		} else {
			response = ResponseEntity.ok(mappingJacksonValue);
		}

		return response;
	}

	@PostMapping(path = { "/users" })
	public ResponseEntity<Long> addUser(@Valid @RequestBody UserInfo userInfo) {
		UserInfo addedUserInfo = userManageService.addUserInfo(userInfo);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(addedUserInfo.getUserId()).toUri();

		return ResponseEntity.created(location).body(addedUserInfo.getUserId());
	}

	@PutMapping(path = { "/users/{userId}" })
	public ResponseEntity<?> updateUser(@PathVariable String userId, @Valid @RequestBody UserInfo userInfo) {
		ResponseEntity<?> response = null;

		UserInfo updatedUserInfo = userManageService.addUserInfo(userInfo);

		if (null == updatedUserInfo) {
			response = ResponseEntity.badRequest().build();
		} else {
			response = ResponseEntity.ok().build();
		}

		return response;
	}

	@DeleteMapping(path = { "/users/{userId}" })
	public ResponseEntity<?> updateUser(@PathVariable long userId) {
		ResponseEntity<?> response = null;

		userManageService.deleteUserById(userId);
		response = ResponseEntity.ok().build();

		return response;
	}

}
