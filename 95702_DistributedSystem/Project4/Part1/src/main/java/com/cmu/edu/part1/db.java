//Frank Yue Ying | yying2@

package com.cmu.edu.part1;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.sql.Timestamp;
import java.util.Hashtable;

public class db {
    MongoCollection<Document> db_collection;
    int today_count=0;
    int search_count =0;
    int compare_count = 0;
    double avg_duration = 0.0;


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
            System.out.println("error");
        }
    }

    public void search_count (){
        String now_date = (new Timestamp(System.currentTimeMillis())).toString().split(" ")[0];
        int today_count = 0;
        int search_count =0;
        int compare_count = 0;
        int total_duration = 0;

        //Referenced from https://kb.objectrocket.com/mongo-db/how-to-iterate-through-mongodb-query-results-through-a-function-using-java-388
        FindIterable<Document> iterable = this.db_collection.find(); // (1)
        MongoCursor<Document> cursor = iterable.iterator(); // (2)
        try {
            while(cursor.hasNext()) {
                Document item = cursor.next();
                if (item.get("action_type").toString().contains("compare")){
                    compare_count++;
                } else {
                    search_count++;
                }
                if (item.get("request_time").toString().contains(now_date)){
                    today_count++;
                }
                total_duration+=Integer.parseInt(item.get("duration").toString());
            }
        } finally {
            cursor.close();
        }
//        //doesn't work Referenced from https://www.mongodb.com/docs/atlas/atlas-search/tutorial/run-query/
//        Document agg = new Document("query", now_date).append("path","request_time");
//        AggregateIterable<Document> today = this.db_collection.aggregate(Arrays.asList(eq("$search", eq("text", agg)),count()));
        this.today_count = today_count;
        this.search_count = search_count;
        this.compare_count = compare_count;
        this.avg_duration = (double) total_duration/(search_count+compare_count);
    }


    public static void main( String[] args ) {
        db FPLDB = new db();
        FPLDB.db_collection = FPLDB.setup_db();
//        System.out.println(FPLDB.search_count());

    }

}
