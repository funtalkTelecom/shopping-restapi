package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Xinpu Wang
 * null
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tb_hotkey")
public class HotKey implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="key_name", nullable = false)
	private String keyName;

	@Column(name = "country", nullable = true)
	private String country;

	@Column(name = "priority", nullable = true)
	private String priority;

	@Column(name="create_time",columnDefinition="timestamp default current_timestamp")
	private Date createTime;


}
