package com.search.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class Review extends Reviews {
    @JsonIgnore
    private String text;
    private String summary;
    private String xsum;
    private String url;
    @JsonIgnore
    private String domain;
    private double rating;
    private double sentiment;
    @JsonIgnore
    private Date pub_date;
    @JsonIgnore
    private int comments;
    private double score ;
    private double text_score;
    private List<String> keywords;



}
