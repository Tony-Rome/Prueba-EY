package com.ey.test.service.impl;

import com.ey.test.dao.entity.PhoneEntity;
import com.ey.test.dao.entity.UserEntity;
import com.ey.test.dao.repository.PhoneRepository;
import com.ey.test.dao.repository.UserRepository;
import com.ey.test.dto.request.UserRequestDTO;
import com.ey.test.dto.response.UserResponseDTO;
import com.ey.test.exception.UserManagementException;
import com.ey.test.mapper.PhoneMapper;
import com.ey.test.mapper.UserMapper;
import com.ey.test.service.JwtService;
import com.ey.test.service.UserManagementService;
import com.ey.test.service.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ey.test.util.ErrorMessageUtil.EMAIL_EXISTS_ERR_DESC;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final ValidateService validateService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Override
    public UserResponseDTO signup(UserRequestDTO userRequestDTO) {
        validateService.validateData(userRequestDTO);

        if(userRepository.findByEmail(userRequestDTO.getEmail()) != null)
            throw new UserManagementException(EMAIL_EXISTS_ERR_DESC, HttpStatus.BAD_REQUEST);

        String jwt = jwtService.generateJwt(userRequestDTO.getEmail());
        UserEntity userEntity = UserMapper.toUserEntity(userRequestDTO, jwt);
        userRepository.save(userEntity);

        List<PhoneEntity> phoneEntityList = PhoneMapper.toPhoneEntityList(userRequestDTO.getPhones(), userEntity);
        phoneRepository.saveAll(phoneEntityList);

        return getUserResponse(userEntity);
    }

    private UserResponseDTO getUserResponse(UserEntity user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.isActive()).build();
    }
}
