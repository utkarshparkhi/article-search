package com.search.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class ReviewReturn extends Reviews {
    @JsonIgnore
    private String text;
    @JsonIgnore
    private String domain;
    @JsonIgnore
    private double sentiment;
    @JsonIgnore
    private Date pub_date;
    @JsonIgnore
    private int comments;
    @JsonIgnore
    private double score ;
    @JsonIgnore
    private double text_score;
    @JsonIgnore
    private List<String> keywords;
    @JsonIgnore
    private List<String> queries;


}
