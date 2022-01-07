package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "tb_product")
public class Product implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "product_name", nullable = false, length = 255)
	private String productName;

	@Column(name = "image", nullable = false, length = 255)
	private String image;

	@Column(name = "images", columnDefinition="TEXT")
	private String images;

	@Column(name = "description", nullable = false, columnDefinition="TEXT")
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "quantity", nullable = false, length = 10)
	private Integer quantity;

	@Column(name = "short_desc", nullable = false, length = 255)
	private String shortDesc;

	@JoinColumn(name="cat_id")
	@ManyToOne
	private Category category;

	@Column(name = "area_type", nullable = false, length = 2)
	private Integer areaType;

}
