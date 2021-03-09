package com.boot.user.services.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDTO {

	@NotNull
	private String firstName;

	private String lastName;

	private String userName;

	private Date birthDate;

	private String password;

	private String phoneNumber;

	private String email;

	private String deliveryAddress;

	// userOrder : boolean--- user can order if he is over 18 years
	private boolean userOrder;
}