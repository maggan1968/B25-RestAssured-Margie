package com.cydeo.pojo;

import lombok.*;

import java.util.List;

@Getter //from lombok dependency
@Setter
@ToString
public class Region {

    private int region_id;
    private String region_name;
    private List<Link> links;


    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor

    public class Regions {

        private List<Region> items;
        private boolean hasMore;
        private int limit, offset, count;
        private List<Link> links;
    }
}