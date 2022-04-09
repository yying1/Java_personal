//Frank Yue Ying | yying2@

package com.cmu.edu.part1;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class db {
    MongoCollection<Document> db_collection;

    public MongoCollection<Document> setup_db (){
    // referenced from https://www.mongodb.com/docs/atlas/tutorial/insert-data-into-your-cluster/
        ConnectionString connectionString = new ConnectionString("mongodb://yying2:wywdzy@cluster0-shard-00-00.vw2o4.mongodb.net:27017,cluster0-shard-00-01.vw2o4.mongodb.net:27017,cluster0-shard-00-02.vw2o4.mongodb.net:27017/test?w=majority&retryWrites=true&tls=true&authMechanism=SCRAM-SHA-1");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("FPL");
        MongoCollection<Document> collection = database.getCollection("test");
        return collection;
    }

    public void insert_collection (MongoCollection<Document> collection, Hashtable<String, String> parameters){
        try {
            // referenced from https://www.mongodb.com/docs/atlas/tutorial/insert-data-into-your-cluster/
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("request_time", parameters.get("time"))
                    .append("action_type", parameters.get("action"))
                    .append("player1", parameters.get("p1"))
                    .append("player2", parameters.get("p2"))
                    .append("result", parameters.get("result"))
                    .append("duration", parameters.get("duration"))
                    );
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }

    public static void main( String[] args ) {

//        Document doc = collection.find(eq("name", "Mercedes Tyler")).first();
//        System.out.println(doc.toJson());

    }

}
