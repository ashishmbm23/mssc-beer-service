package com.ashish.msscbeerservice.web.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Beer page list model")
public class BeerPagedList extends PageImpl<BeerDto> implements Serializable {
    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }
}
