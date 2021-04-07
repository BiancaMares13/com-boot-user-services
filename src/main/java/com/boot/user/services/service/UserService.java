package com.boot.user.services.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.user.services.client.ProductServiceClient;
import com.boot.user.services.dto.UserDTO;
import com.boot.user.services.exception.DuplicateEntryException;
import com.boot.user.services.exception.EntityNotFoundException;
import com.boot.user.services.exception.InvalidInputDataException;
import com.boot.user.services.mapper.UserMapper;
import com.boot.user.services.model.Product;
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

	public UserDTO addProductToUserFavorites(String userName, String productName) throws DuplicateEntryException {

		Product product = ProductServiceClient.callGetProductByProductName(productName).getBody();

		User user = userRepository.getUserByUserName(userName);

		UserDTO userDTOMapped = UserMapper.UserEntityToDto(user);

		List<Product> favoriteProductList = new ArrayList<Product>();
		favoriteProductList = userDTOMapped.getFavoriteProductList();
	
		if(favoriteProductList.stream().anyMatch(p -> productName.equals(p.getProductName()))) {
			throw new DuplicateEntryException("Product: "+ productName +" was already added to favorites!");
		}
		else {
		favoriteProductList.add(product);					
		userDTOMapped.setFavoriteProductList(favoriteProductList);
		user = userRepository.save(UserMapper.updateDtoToUserEntity(user, userDTOMapped));

		return UserMapper.UserEntityToDto(user);
		}
	}

	public UserDTO addUser(UserDTO userDTO) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(userDTO.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		if (!userValidator.isUserDataSizeCorrect(userDTO.getEmail(), 3, 30)) {
			throw new InvalidInputDataException("Provided Email has to be between 3 and 30 characters long!");
		}
		if (!userValidator.isUsernameValid(userDTO.getUserName())) {
			throw new InvalidInputDataException("invalid username format!");
		}
		if (!userValidator.isUsernamePresent(userDTO.getUserName())) {
			throw new InvalidInputDataException("usename is already used!");
		}
		if (!userValidator.isUserDataSizeCorrect(userDTO.getUserName(), 3, 30)) {
			throw new InvalidInputDataException("Username has to be between 3 and 30 characters long!");
		}
		if (!userValidator.isUserDataSizeCorrect(userDTO.getFirstName(), 3, 30)) {
			throw new InvalidInputDataException("Name has to be between 3 and 30 characters long!");
		}
		if (!userValidator.isUserDataSizeCorrect(userDTO.getLastName(), 3, 30)) {
			throw new InvalidInputDataException("Surname has to be between 3 and 30 characters long!");
		}

		if (!userValidator.isUserDataSizeCorrect(userDTO.getDeliveryAddress(), 3, 300)) {
			throw new InvalidInputDataException("Adress has to be between 3 and 300 characters long!");
		}
		if (!userValidator.isPhoneNumberValid(userDTO.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}
		if (!userValidator.isUserDataSizeCorrect(userDTO.getPhoneNumber(), 10, 15)) {
			throw new InvalidInputDataException("Phone number has to be between 10 and 15 characters long!");
		}

		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		User user = userRepository.save(UserMapper.DtoToUserEntity(userDTO).setCreatedOn(LocalDate.now()));

		return UserMapper.UserEntityToDto(user);
	}

	public UserDTO updateUserByUserName(String userName, UserDTO userDTO) throws InvalidInputDataException {

		User user = userRepository.getUserByUserName(userName);

		UserDTO userDTOMapped = UserMapper.UserEntityToDto(user);

		if (!userValidator.isEmailValid(userDTO.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}

		if (!userValidator.isUserDataSizeCorrect(userDTO.getEmail(), 3, 30)) {
			throw new InvalidInputDataException("Provided Email has to be between 3 and 30 characters long!");
		}
		userDTOMapped.setEmail(userDTO.getEmail());

		if (!userValidator.isUsernameValid(userDTO.getUserName())) {
			throw new InvalidInputDataException("invalid username format!");
		}

		if (!userValidator.isUserDataSizeCorrect(userDTO.getUserName(), 3, 30)) {
			throw new InvalidInputDataException("Username has to be between 3 and 30 characters long!");
		}

		userDTOMapped.setUserName(userDTO.getUserName());

		if (!userValidator.isUserDataSizeCorrect(userDTO.getFirstName(), 3, 30)) {
			throw new InvalidInputDataException("Name has to be between 3 and 30 characters long!");
		}
		userDTOMapped.setFirstName(userDTO.getFirstName());

		if (!userValidator.isUserDataSizeCorrect(userDTO.getLastName(), 3, 30)) {
			throw new InvalidInputDataException("Surname has to be between 3 and 30 characters long!");
		}

		userDTOMapped.setLastName(userDTO.getLastName());

		if (!userValidator.isUserDataSizeCorrect(userDTO.getDeliveryAddress(), 3, 300)) {
			throw new InvalidInputDataException("Adress has to be between 3 and 300 characters long!");
		}

		userDTOMapped.setDeliveryAddress(userDTO.getDeliveryAddress());

		if (!userValidator.isPhoneNumberValid(userDTO.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}
		if (!userValidator.isUserDataSizeCorrect(userDTO.getPhoneNumber(), 10, 15)) {
			throw new InvalidInputDataException("Phone number has to be between 10 and 15 characters long!");
		}
		userDTOMapped.setPhoneNumber(userDTO.getPhoneNumber());

		userDTOMapped.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		user = userRepository
				.save(UserMapper.updateDtoToUserEntity(user, userDTOMapped).setLastUpdatedOn(LocalDate.now()));

		return UserMapper.UserEntityToDto(user);
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
