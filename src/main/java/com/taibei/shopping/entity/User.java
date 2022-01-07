package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

/**
 * @author Xinpu Wang
 * null
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name", nullable = false, length = 255)
	private String userName;

	@Column(name = "pass_word", nullable = false, length = 255)
	private String passWord;

	@Column(name = "first_name", nullable = true, length = 255)
	private String firstName;

	@Column(name = "last_name", nullable = true, length = 255)
	private String lastName;

	@Column(name = "phone_number", nullable = false, length = 255)
	private String phoneNumber;

	@Column(name = "email", nullable = false, length = 255)
	private String email;

	@Column(name = "address", length = 255)
	private String address;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="tb_user_role",
			joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
	public List<Role> roles = new ArrayList<>();

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "user")
//	@JsonIgnore
//	@ToString.Exclude
//	List<Order>  orders =new ArrayList<>();

	@Column(name="birth", columnDefinition="date")
	private Date birth;

	@Column(name = "photo_url")
	private String photoUrl;


	@Column(name = "type", length = 2)
	private String type="1";

	@Column(name="create_time",columnDefinition="timestamp default current_timestamp")
	private Date createdTime;




	public  User(){

	}
	public User(String userName, String passWord,String phoneNumber, String email) {
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.email = email;

	}
}
