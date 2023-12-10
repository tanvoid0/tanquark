package com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio;

import com.tanvoid0.tanquark.common.base.BaseOrderedVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPortfolioVO extends BaseOrderedVO {
    @Serial
    private static final long serialVersionUID = 7328599951956651550L;

    private String title;
    private String timeline;
    private String description;
    private String demo;
    private String source;
    private EPlatformType platform = EPlatformType.UNCATEGORIZED;
    private List<String> tags = new ArrayList<>();
    private String coverImage;
    private List<String> images = new ArrayList<>();
    private Long orderSeq;
}
