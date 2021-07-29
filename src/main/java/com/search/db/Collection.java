package com.search.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Collection {


    public static MongoCollection<Document> getCol() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = new FileInputStream("config.properties");
            prop.load(inputStream);
            String host = prop.getProperty("host");
            int port = Integer.parseInt(prop.getProperty("port"));
            String db_name = prop.getProperty("db_name");
            String col_name = prop.getProperty("col_name");
            MongoClient mongoClient = new MongoClient(host, port);
            MongoDatabase db = mongoClient.getDatabase(db_name);
            return db.getCollection(col_name);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
