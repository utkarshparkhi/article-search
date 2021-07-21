package com.search.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.search.bean.Review;
import com.search.bean.ReviewReturn;
import com.search.bean.Reviews;
import com.search.db.Collection;
import org.bson.Document;

import java.util.*;

public class SearchDAO {
    public static ArrayList<Reviews> search(String q, boolean d){
        MongoCollection col = Collection.getCol();

        MongoCursor<Document> res = col.aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.text(q)),
                        Aggregates.project(Review.getFields(new Document(),d)),
                        Aggregates.sort(new Document("text_score",-1)),
                        Aggregates.match(new Document("text_score",new Document("$gte",16))),
                        Aggregates.limit(10)
                )
        ).iterator();
        if(Objects.isNull(res)){
            System.out.println("res is null");
        }
        else{
            System.out.println(res.hasNext());
        }
        ArrayList<Reviews> revs =new ArrayList<>();
        double mtext_score = 0;
        while (res.hasNext()){
            Document dc = res.next();
            Reviews nr;
            if(d) {
                 nr = new Review();
            }
            else{
                nr = new ReviewReturn();
            }
            if(dc.containsKey("rating")) {
                nr.setRating(dc.getDouble("rating"));
            }
            else{
                nr.setRating(0);
            }
            if(dc.containsKey("suma1")) {
                nr.setSummary(dc.getString("suma1"));
            }
            else{
                nr.setSummary("");
            }
            if(dc.containsKey("onel1")){
                nr.setXsum(dc.getString("onel1"));
            }
            else{
                nr.setXsum("");
            }
            if(dc.containsKey("domain")) {
                nr.setDomain(dc.getString("domain"));
            }
            else{
                nr.setDomain("dom");
            }
            if(dc.containsKey("url")) {
                nr.setUrl(dc.getString("url"));
            }
            else{
                nr.setUrl("url");
            }
            if(dc.containsKey("pub_date")){
                nr.setPub_date(dc.getDate("pub_date"));
            }
            else{
                nr.setPub_date(new Date(0));
            }
            if(dc.containsKey("sentiment")){
                nr.setSentiment(dc.getDouble("sentiment"));
            }
            else{
                nr.setSentiment(0);
            }
            if(dc.containsKey("text")){
                nr.setText(dc.getString("text"));
            }
            else{
                nr.setText("");
            }
            if(dc.containsKey("comments")){
                nr.setComments(dc.getInteger("comments"));
            }
            else{
                nr.setComments(0);
            }
            if(dc.containsKey("text_score")){
                nr.setText_score(dc.getDouble("text_score"));
                mtext_score = Math.max(mtext_score,dc.getDouble("text_score"));
            }
            else{
                nr.setText_score(0);
            }
            if(dc.containsKey("keywords")){
                nr.setKeywords((List<String>) dc.get("keywords"));
            }
            if(dc.containsKey("queries")){
                nr.setQueries((List<String>) dc.get("queries"));
            }
            revs.add(nr);
        }
        final double mts = mtext_score;
        revs.forEach((doc) -> doc.setText_score(doc.getText_score()/mts));
        Collections.sort(revs, (o1, o2) -> {
            double score1 = o1.getScore();
            double score2 = o2.getScore();
//            int com1 = o1.getComments();
//            int com2 = o2.getComments();
//
//            if(Math.max(com1,com2) != 0){
//                score1 = score1 + 5*(double) com1/Math.max(com1,com2);
//                score2 = score2 + 5*(double) com2/Math.max(com1,com2);
//            }
            double res1 = score2-score1;

            if (res1 >0){
                return (int) res1+ 1;
            }
            else{
                return (int) res1;
            }
        });
        return revs;
    }
}
