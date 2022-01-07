package com.taibei.shopping.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Xinpu Wang
 * null
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="user_name", nullable = false)
	private String userName;

	@Column(name = "prod_id", nullable = false)
	Integer prodId;

	@Column(name = "product_name", nullable = false, length = 255)
	private String productName;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "quantity", nullable = false, length = 11)
	private Integer quantity;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "contact", nullable = true)
	private String contact;

	@Column(name = "state", nullable = false, columnDefinition = "varchar(2) default '1'")
	private String state;

	@Column(name="create_time",columnDefinition="timestamp default current_timestamp")
	private Date createTime;

	@Column(name="post_time",columnDefinition="timestamp")
	private Date postTime;

	@Column(name="cancel_time",columnDefinition="timestamp")
	private Date cancelTime;

	public Order(Integer quantity) {

		this.quantity = quantity;
	}
}
