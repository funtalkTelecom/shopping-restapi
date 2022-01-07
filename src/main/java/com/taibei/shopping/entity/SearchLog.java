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
@Table(name = "tb_searchlog")
public class SearchLog implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="user_name", nullable = false)
	private String userName;

	@Column(name ="search_key", nullable = false, length = 255)
	private String searchKey;

	@Column(name="search_time",columnDefinition="timestamp default current_timestamp")
	private Date searchTime;


}
