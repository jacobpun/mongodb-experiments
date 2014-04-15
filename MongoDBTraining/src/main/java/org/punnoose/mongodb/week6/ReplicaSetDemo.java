package org.punnoose.mongodb.week6;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class ReplicaSetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MongoClient client = new MongoClient(Arrays.asList(
					new ServerAddress("localhost:30000"),
					new ServerAddress("localhost:30001")
					));
			
			client.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
			
			DB db = client.getDB("course");
			DBCollection collection = db.getCollection("repl.test.java");
			collection.drop();
			
			BasicDBObject obj = new BasicDBObject("_id",2);
			collection.insert(obj);
			collection.insert(obj);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
