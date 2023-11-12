package com.tanvoid0.tanquark.models.user.mapper;


import com.tanvoid0.tanquark.config.auth.vo.AuthenticatedUserVO;
import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import com.tanvoid0.tanquark.models.user.User;
import com.tanvoid0.tanquark.models.user.UserVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
class UserMapperImpl implements UserMapper {
    final ModelMapper mapper = new ModelMapper();

    @Override
    public User toEntity(RegisterRequestVO registerUser) {
        return mapper.map(registerUser, User.class);
    }

    @Override
    public User toEntity(UserVO user) {
        return mapper.map(user, User.class);
    }

    @Override
    public UserVO toVO(User user) {
        return mapper.map(user, UserVO.class);
    }

    @Override
    public AuthenticatedUserVO toAuthenticatedVO(User user) {
        return mapper.map(user, AuthenticatedUserVO.class);
    }
}
