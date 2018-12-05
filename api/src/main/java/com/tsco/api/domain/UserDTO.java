package com.tsco.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String gender;
    private Date birthday;
    private String address;
    private String userRole;
    private Date createdAt;
    private Date updatedAt;

}

