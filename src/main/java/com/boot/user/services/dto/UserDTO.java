package com.boot.user.services.dto;

import java.time.LocalDate;




import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserDTO {

	
	private String firstName;

	private String lastName;

	private String userName;

	private LocalDate birthDate;

	private String password;

	private String phoneNumber;

	private String email;

	private String deliveryAddress;

	// userOrder : boolean--- user can order if he is over 18 years
	private boolean userOrder;


}