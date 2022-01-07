package com.taibei.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", nullable = false, length = 255)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy="roles")
    @JsonIgnore
    @ToString.Exclude
    List<User>  users =new ArrayList<>();

}
