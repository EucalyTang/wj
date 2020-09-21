package com.ciel.wj.dto;

import com.ciel.wj.dto.base.OutputConverter;
import com.ciel.wj.pojo.AdminRole;
import com.ciel.wj.pojo.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {
    private int id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

    private List<AdminRole> roles;

}
