package org.punnoose.mongodb.week2;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class FindDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("inserttest");
		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			BasicDBObject obj = new BasicDBObject("x", rand.nextInt(2));
			obj.append("y", rand.nextInt(100));
			obj.append("z", rand.nextInt(1000));
			collection.insert(obj);
		}

		/*
		 * DBObject query = new BasicDBObject("x", 1).append("y", new
		 * BasicDBObject("$gt", 50).append("$lt", 80));
		 */

		QueryBuilder builder = QueryBuilder.start("x").is(1).and("y")
				.greaterThan(50).lessThan(80);

		System.out.println("Find One");
		DBObject obj = collection.findOne(builder.get());
		System.out.println(obj);

		System.out.println("\nFind All");
		try (DBCursor cursor = collection.find(builder.get(),
				new BasicDBObject("y", true).append("_id", false))) {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		}

		System.out.println("\nCount: " + collection.count(builder.get()));
	}
}
