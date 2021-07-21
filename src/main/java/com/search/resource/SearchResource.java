package com.search.resource;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.search.DAO.SearchDAO;
import com.search.bean.Reviews;
import com.search.db.Collection;

import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
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
    public ArrayList<Reviews> search(
            @QueryParam("q") String q,
            @QueryParam("debug") String d){
        boolean debug;
        if(Objects.isNull(d)){
            debug=false;
        }
        else if(d.toLowerCase(Locale.ROOT).equals("false") || d.equals("0")){
            debug= false;
        }
        else{

            debug = true;
        }
        return SearchDAO.search(q,debug);
    }

}
