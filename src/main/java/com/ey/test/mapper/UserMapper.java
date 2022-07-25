package com.ey.test.mapper;

import com.ey.test.dao.entity.UserEntity;
import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.util.DatetimeUtil;

import java.util.UUID;

public class UserMapper {

    public static UserEntity toUserEntity(UserRequestDTO userRequestDTO, String token){
        return UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .token(token)
                .name(userRequestDTO.getName().toLowerCase())
                .email(userRequestDTO.getEmail().toLowerCase())
                .password(userRequestDTO.getPassword())
                .modified(DatetimeUtil.getCurrentDateTime())
                .lastLogin(DatetimeUtil.getCurrentDateTime())
                .created(DatetimeUtil.getCurrentDateTime())
                .isActive(true)
                .build();

    }
}
