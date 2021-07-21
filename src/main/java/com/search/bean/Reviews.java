package com.search.bean;


import org.bson.Document;

import java.util.Date;
import java.util.List;

public class Reviews {
    private String text;
    private String summary;
    private String xsum;
    private String url;
    private String domain;
    private double rating;
    private double sentiment;
    private Date pub_date;
    private int comments;
    private double score ;
    private double text_score;
    private List<String> keywords;
    private List<String> queries;

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    public double getText_score() {
        return text_score;
    }

    public void setText_score(double text_score) {
        this.text_score = text_score;
    }

    public double getScore() {
        setScore();
        return score;
    }

    public void setScore() {
        double score = 0;
        double pub_date_score = ((double) this.pub_date.getTime())/((double) new Date().getTime());
        double sentiment_score = this.sentiment;
        double rating_score = this.rating;
        double text_score = this.text_score;
        score = (pub_date_score*25) + (sentiment_score * 25) + (rating_score*15) + (35*text_score);
        this.score = score;
    }

    public String getXsum() {
        return xsum;
    }

    public void setXsum(String xsum) {
        this.xsum = xsum;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getSentiment() {
        return sentiment;
    }

    public void setSentiment(double sentiment) {
        this.sentiment = sentiment;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public int getComments() {
        return comments;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
    public static Document getFields(Document doc, boolean d) {
        doc.append("suma1", 1)
                .append("onel1", 1)
                .append("url", 1)
                .append("rating", 1)
                .append("comments", 1)
                .append("pub_date", 1)
                .append("domain", 1)
                .append("text", 1)
                .append("sentiment", 1)
                .append("keywords", 1)
                .append("text_score", new Document("$meta", "textScore"))
                .append("queries",1);
        return doc;
    }


}
