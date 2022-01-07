package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xinpu Wang
 * null
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tb_cart")
public class Cart implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="user_name")
	private String userName;

	@Column(name = "prod_id")
	private Integer productId;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity",length = 10)
	private Integer quantity;

	@JoinColumn(name="cat_id")
	@ManyToOne
	private Category category;

	@Column(name="state")
	private String state;

	@Column(name="create_time",columnDefinition="timestamp default current_timestamp")
	private Date createTime;

}
