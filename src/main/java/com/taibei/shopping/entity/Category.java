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
@Table(name = "tb_category")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@Column(name = "cat_name", nullable = false, length = 255)
	private String catName;

	@OneToMany(mappedBy = "category")
	@JsonIgnore
	List<Product> products =new ArrayList();
}
