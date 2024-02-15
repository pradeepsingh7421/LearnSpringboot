package com.learn.springboot.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parent_id")
    private int parent_id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Children> children;
}
