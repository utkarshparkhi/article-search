package com.search;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.search.db.Collection;

import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Vector;

@Path("/search")
public class SearchResource {

    @Path("/all")
    @GET
    public String hello(){
        MongoCollection<Document> col = Collection.getCol();
        MongoCursor<Document> it = col.find().iterator();
        Vector<String> res = new Vector<>();
        while (it.hasNext()) {
            Document dc = it.next();
            res.add(dc.toJson());
        }
        return res.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Review> search(
            @QueryParam("q") String q){
        ArrayList<Review> r = SearchDAO.search(q);
        return r;
    }

}
