package com.search.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Collection {


    public static MongoCollection<Document> getCol(){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase db = mongoClient.getDatabase("process_db");
        MongoCollection<Document> col = db.getCollection("processed_col");
        return col;
    }
}
