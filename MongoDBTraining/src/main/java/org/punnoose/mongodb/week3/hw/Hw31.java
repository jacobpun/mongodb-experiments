package org.punnoose.mongodb.week3.hw;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class Hw31 {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("school");
		DBCollection collection = database.getCollection("students");

		DBObject query = QueryBuilder.start("scores.type").is("homework").get();

		try (DBCursor cursor = collection.find(query)) {
			while (cursor.hasNext()) {
				DBObject document = cursor.next();
				double minHomeworkScore = getMinimumHomeworkScore(document);
				System.out.println(document.get("_id") + ": " + minHomeworkScore);
				collection
						.update(QueryBuilder.start("_id")
								.is(document.get("_id")).get(),
								new BasicDBObject("$pull", new BasicDBObject(
										"scores", new BasicDBObject("type",
												"homework").append("score",
												minHomeworkScore))));
			}
		}

	}

	private static double getMinimumHomeworkScore(final DBObject document) {

		List<DBObject> scores = (List<DBObject>) document.get("scores");

		double minHomeworkScore = Double.MAX_VALUE;

		for (DBObject score : scores) {
			if (score.get("type").equals("homework")) {
				double currentScore = (double) score.get("score");

				if (currentScore < minHomeworkScore) {
					minHomeworkScore = currentScore;
				}
			}
		}

		return minHomeworkScore;

	}

}