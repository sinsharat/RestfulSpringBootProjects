package com.sharat.restfulwebservice.usermanage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("All details of a user") // swagger
@Entity
@JsonFilter("UserInfoFilter")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private long userId;
	
	@NotBlank(message = "{username.NotBlank}")
	@Size(min = 2, message = "{username.Size}")
	private String username;
	
	@NotBlank(message = "{password.NotBlank}")
	@Size(min = 5, message = "{password.Size}")
	private String password;

	@NotBlank(message = "{name.NotBlank}")
	@Size(min = 2, message = "{name.Size}")
	@ApiModelProperty(notes = "Name should atleast have two characters") // swagger
	private String name;
	
	@DecimalMin(value = "0", message = "{gender.NotValid}")
	@DecimalMax(value = "1", message = "{gender.NotValid}") 
	private int gender;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", gender=" + gender + "]";
	}
	
}
