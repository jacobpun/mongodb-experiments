package org.punnoose.mongodb.week6;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

public class ReplicasetDemoForRead {
	public static void main(String args[]) throws UnknownHostException{
		MongoClient client = new MongoClient(Arrays.asList(
				new ServerAddress("localhost:30000"),
				new ServerAddress("localhost:30001")
				));
		
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("repl.test.java");
		
		DBCursor cursor = collection.find().setReadPreference(ReadPreference.secondaryPreferred());
		while (cursor.hasNext()){
			BasicDBObject obj = (BasicDBObject) cursor.next();
			System.out.println(obj);
		}
				
	}
}