package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

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
@Table(name = "tb_expense")
public class Expense implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name", nullable = false, length = 255)
	private String userName;

	@Column(name = "expense_type", nullable = false, length = 50)
	private String expenseType;

	@Column(name = "amount", nullable = false, length = 255)
	private Double amount;

	@Column(name = "description", nullable = false, length = 255)
	private String description;

	@Column(name = "expense_date", nullable = false, length = 255)
	private String expenseDate;

	@Column(name="create_time",columnDefinition="timestamp default current_timestamp")
	private Date createTime;


	public Expense(){

	}

	@Override
	public String toString() {
		return "Expense{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", amount='" + amount + '\'' +
				", description='" + description + '\'' +
				", expenseDate=" + expenseDate +
				", createdTime=" + createTime +
				'}';
	}
}
