package com.tanvoid0.tanquark.models.user.mapper;

import com.tanvoid0.tanquark.config.auth.vo.AuthenticatedUserVO;
import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import com.tanvoid0.tanquark.models.user.User;
import com.tanvoid0.tanquark.models.user.UserVO;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(RegisterRequestVO registerUser);

    User toEntity(UserVO user);

    UserVO toVO(User user);

    AuthenticatedUserVO toAuthenticatedVO(User user);
}