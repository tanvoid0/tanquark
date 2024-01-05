package com.tanvoid0.tanquark.models.portfolio.portfolio.project;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProjectPortfolioVO {
    @NotNull
    private String title;
    private String timeline;
    private String description;
    private String demo;
    private String source;
    private EProjectStatus status = EProjectStatus.UNKNOWN;
    private EPlatformType platform = EPlatformType.UNCATEGORIZED;
    private List<String> tags = new ArrayList<>();
//  private final List<NewImageLinkVO> images = new ArrayList<>();
}
