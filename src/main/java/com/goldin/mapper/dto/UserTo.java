package com.goldin.mapper.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTo {
    private long id;
    private String name;
    private String email;
    private String phone;
}
