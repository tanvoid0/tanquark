package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NewPortfolioContactRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5394616727783713001L;


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

    @NotEmpty
    private String portfolioUserUsername;

}
