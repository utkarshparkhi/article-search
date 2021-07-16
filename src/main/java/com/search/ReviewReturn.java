package com.search;

public class ReviewReturn {
    private String summary;
    private String xsum;
    private String url;
    private String domain;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getXsum() {
        return xsum;
    }

    public void setXsum(String xsum) {
        this.xsum = xsum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    private Float rating;
}
