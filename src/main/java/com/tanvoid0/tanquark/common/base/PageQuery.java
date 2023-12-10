package com.tanvoid0.tanquark.common.base;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 136169505247539607L;
    
    @QueryParam("page")
    private int page;

    @QueryParam("size")
    private int size;

    @QueryParam("orderBy")
    private List<String> orderBy = new ArrayList<>();
}
