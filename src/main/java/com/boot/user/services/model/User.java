package com.boot.user.services.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2904101271253876784L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Size(min = 3, max = 15)
	private String userName;

	@Column
	private LocalDate birthDate;

	@Column
	private String password;

	@Column
	private String phoneNumber;

	@Column
	private String email;

	@Column
	private String deliveryAddress;

	@Column
	private LocalDate createdOn;

	@Column
	private LocalDate lastUpdatedOn;
}