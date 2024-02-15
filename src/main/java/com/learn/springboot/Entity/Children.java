package com.learn.springboot.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "children")
public class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "child_id")
    private int child_id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Parent parent;
}
