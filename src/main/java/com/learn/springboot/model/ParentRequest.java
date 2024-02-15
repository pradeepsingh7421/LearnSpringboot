package com.learn.springboot.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ParentRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
}
