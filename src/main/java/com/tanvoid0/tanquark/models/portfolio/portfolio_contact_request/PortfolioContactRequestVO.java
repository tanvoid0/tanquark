package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import com.tanvoid0.tanquark.common.base.BaseVO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioContactRequestVO extends BaseVO {

    @Serial
    private static final long serialVersionUID = 8663017171721255140L;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 5, max = 100)
    private String subject;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 10, max = 1000)
    private String message;
}
