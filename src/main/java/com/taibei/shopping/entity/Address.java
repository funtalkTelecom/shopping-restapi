package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Xinpu Wang
 * null
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tb_address")
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="user_name", nullable = false)
	private String userName;

	@Column(name = "country", nullable = true)
	private String country;

	@Column(name = "postcode", nullable = false)
	private String postcode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "contact", nullable = false)
	private String contact;

	@Column(name = "state", nullable = false, columnDefinition = "varchar(2) default '1'")
	private String state;


}
