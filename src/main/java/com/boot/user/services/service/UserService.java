package com.boot.user.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.boot.user.services.exception.EntityNotFoundException;
import com.boot.user.services.exception.InvalidInputDataException;
import com.boot.user.services.model.User;
import com.boot.user.services.repository.UserRepository;
import com.boot.user.services.validator.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidator userValidator;

	public User addUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(user.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		if (!userValidator.isUserDataSizeCorrect(user.getEmail(), 3, 30)) {
			throw new InvalidInputDataException("Provided Email has to be between 3 and 30 characters long!");
		}
		if (!userValidator.isUsernameValid(user.getUserName())) {
			throw new InvalidInputDataException("invalid username format!");
		}
		if (!userValidator.isUsernamePresent(user.getUserName())) {
			throw new InvalidInputDataException("usename is already used!");
		}
		if (!userValidator.isUserDataSizeCorrect(user.getUserName(), 3, 30)) {
			throw new InvalidInputDataException("Username has to be between 3 and 30 characters long!");
		}
		if (!userValidator.isUserDataSizeCorrect(user.getFirstName(), 3, 30)) {
			throw new InvalidInputDataException("Name has to be between 3 and 30 characters long!");
		}
		if (!userValidator.isUserDataSizeCorrect(user.getLastName(), 3, 30)) {
			throw new InvalidInputDataException("Surname has to be between 3 and 30 characters long!");
		}

		if (!userValidator.isUserDataSizeCorrect(user.getDeliveryAddress(), 3, 300)) {
			throw new InvalidInputDataException("Adress has to be between 3 and 300 characters long!");
		}
		if (!userValidator.isPhoneNumberValid(user.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}
		if (!userValidator.isUserDataSizeCorrect(user.getPhoneNumber(), 10, 15)) {
			throw new InvalidInputDataException("Phone number has to be between 10 and 15 characters long!");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	public User getUserById(long id) throws EntityNotFoundException {
		if (!userValidator.isIdPresent(id)) {
			throw new EntityNotFoundException("Id: " + id + " not found in the Database!");
		}
		return userRepository.getUserById(id);
	}

	public List<User> getAllUsers() throws EntityNotFoundException {
		if (userRepository.findAll() == null || userRepository.findAll().isEmpty()) {
			throw new EntityNotFoundException("No user found in the Database!");
		}
		return userRepository.findAll();
	}

	public User getUserByUserName(String userName) throws EntityNotFoundException {

		if (userValidator.isUsernamePresent(userName)) {
			throw new EntityNotFoundException("UserName: " + userName + " not found in the Database!");
		}
		return userRepository.getUserByUserName(userName);
	}

	public void deleteUserById(long id) throws EntityNotFoundException {
		if (!userValidator.isIdPresent(id)) {
			throw new EntityNotFoundException("Id: " + id + " not found in the Database!");
		}
		userRepository.deleteById(id);
	}

}
