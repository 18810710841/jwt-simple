package com.drj.jwt.jwtsimple.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserModule implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String icon;

    private Integer status;

}