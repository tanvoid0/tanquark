package com.tanvoid0.tanquark.models.portfolio.portfolio.project;

import com.tanvoid0.tanquark.common.base.BaseVO;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class UpdateProjectPortfolioVO extends BaseVO {
    @Serial
    private static final long serialVersionUID = -7739855382203983079L;

    private List<String> tags = new ArrayList<>();
    //    private final List<ImageLinkVO> images = new ArrayList<>();
    @NotNull
    private String title;
    private String timeline;
    private String description;
    private String demo;
    private String source;
    private EProjectStatus status = EProjectStatus.UNKNOWN;
    private EPlatformType platform = EPlatformType.UNCATEGORIZED;
}
