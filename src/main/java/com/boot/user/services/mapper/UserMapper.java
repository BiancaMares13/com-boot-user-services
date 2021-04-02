package com.boot.user.services.mapper;

import java.time.LocalDate;
import java.time.Period;


import com.boot.user.services.dto.UserDTO;
import com.boot.user.services.model.User;

public class UserMapper {

	public static UserDTO UserEntityToDto(User user) {
		return new UserDTO()
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setUserName(user.getUserName())
				.setBirthDate(user.getBirthDate())
				.setPassword(user.getPassword())
				.setPhoneNumber(user.getPhoneNumber())
				.setEmail(user.getEmail())
				.setDeliveryAddress(user.getDeliveryAddress())
				.setUserOrder(checkUserAge(user.getBirthDate()));
	}

	private static boolean checkUserAge(LocalDate birthday) {
		LocalDate currentDate = LocalDate.now();
		Period p = Period.between(currentDate, birthday);
		System.out.println("User has 18 years: " + (p.getYears() >= 18 ? true : false));
		return p.getYears() >= 18 ? true : false;
	}

	
	public static User DtoToUserEntity(UserDTO userDto) {
		return new User()
				.setFirstName(userDto.getFirstName())
				.setLastName(userDto.getLastName())
				.setUserName(userDto.getUserName())
				.setBirthDate(userDto.getBirthDate())
				.setPassword(userDto.getPassword())
				.setPhoneNumber(userDto.getPhoneNumber())
				.setDeliveryAddress(userDto.getDeliveryAddress())
				.setEmail(userDto.getEmail());
	}

}
