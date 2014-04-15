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

public class DotNotationDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("course");
		DBCollection collection = database.getCollection("inserttest");
		collection.drop();

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			BasicDBObject obj = new BasicDBObject("_id", i);
			obj.append(
					"start",
					new BasicDBObject("x", rand.nextInt(10)).append("y",
							rand.nextInt(10)));
			obj.append(
					"end",
					new BasicDBObject("x", rand.nextInt(10)).append("y",
							rand.nextInt(10)));
			collection.insert(obj);
		}

		QueryBuilder builder = QueryBuilder.start("start.x").greaterThan(5);

		System.out.println("\nFind All");
		try (DBCursor cursor = collection.find(builder.get(),
				new BasicDBObject("end.y", true).append("_id", false))) {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		}

		System.out.println("\nSort, skip & Limit");
		try (DBCursor cursor = collection.find()
				.sort(new BasicDBObject("start.x", -1).append("start.y", 1)).skip(2).limit(5)) {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		}

		System.out.println("\nCount: " + collection.count(builder.get()));
	}
}
