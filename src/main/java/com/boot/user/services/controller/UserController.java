package com.boot.user.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.user.services.dto.UserDTO;
import com.boot.user.services.exception.DuplicateEntryException;
import com.boot.user.services.exception.EntityNotFoundException;
import com.boot.user.services.exception.InvalidInputDataException;
import com.boot.user.services.model.User;
import com.boot.user.services.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) throws InvalidInputDataException {
		UserDTO newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PutMapping("/addProductToUserFavorites/{userName}/{productName}")
	public ResponseEntity<UserDTO> addProductToUserFavorites(@PathVariable("userName") String userName,@PathVariable("productName") String productName)
			throws EntityNotFoundException, InvalidInputDataException, DuplicateEntryException {
		UserDTO user = userService.addProductToUserFavorites(userName, productName);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/updateUserByUserName/{userName}")
	public ResponseEntity<UserDTO> updateUserByUsername(@RequestBody UserDTO userDTO,
			@PathVariable("userName") String userName) throws EntityNotFoundException, InvalidInputDataException {
		UserDTO user = userService.updateUserByUserName(userName, userDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/getUserById")
	@ResponseBody
	public ResponseEntity<User> findByUserId(@RequestParam long id) throws EntityNotFoundException {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/getAllUsers")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers() throws EntityNotFoundException {
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@GetMapping("/getUserByUserName")
	@ResponseBody
	public ResponseEntity<User> getUserByUsername(@RequestParam String userName) throws EntityNotFoundException {
		User user = userService.getUserByUserName(userName);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") long id) throws EntityNotFoundException {
		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
