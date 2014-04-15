package org.punnoose.mongodb.week2;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class InsertTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("inserttest");
		collection.drop();
		DBObject document = new BasicDBObject().append("x", 1).append("date", new Date(11112321));
		DBObject document2 = new BasicDBObject().append("x", 2).append("date", new Date(11112321));
		System.out.println(document);
		System.out.println(document2);
		collection.insert(Arrays.asList(document, document2));
		System.out.println(document);
		System.out.println(document2);

	}

}
