package com.boot.user.services.model;

import java.io.Serializable;

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
@Table(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714267227877816930L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String productName;

	@Column
	@Size(min = 3, max = 600)
	private String productDescription;

	@Column
	private long productPrice;

	@Column
	private String productPhotoLink;

	@Column
	@Size(min = 3, max = 30)
	private String productCategory;

	@Column
	private long productStock;

}