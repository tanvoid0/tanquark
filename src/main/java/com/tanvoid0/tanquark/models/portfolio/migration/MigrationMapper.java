package com.tanvoid0.tanquark.models.portfolio.migration;

import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class MigrationMapper {

    public final ModelMapper mapper = new ModelMapper();

    public RegisterRequestVO toNewUserVO(final NewPortfolioUserMigrationVO request) {
        return mapper.map(request, RegisterRequestVO.class);
    }
}
