package com.ey.test.mapper;

import com.ey.test.dao.entity.PhoneEntity;
import com.ey.test.dao.entity.UserEntity;
import com.ey.test.dto.request.PhoneRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneMapper {

    public static List<PhoneEntity> toPhoneEntityList(List<PhoneRequestDTO> phoneRequestDTO, UserEntity user){
        return phoneRequestDTO
                .stream()
                .map(phone -> PhoneEntity.builder()
                        .number(phone.getNumber())
                        .cityCode(phone.getCitycode())
                        .countryCode(phone.getCountrycode().toUpperCase())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
    }
}
