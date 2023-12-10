package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import com.tanvoid0.tanquark.models.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PortfolioContactRequestResponseVO {
    private String message;
    private UserVO guest;
}
