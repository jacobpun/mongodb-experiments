package org.punnoose.mongodb.week2.hw;

import java.net.UnknownHostException;
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

public class Hw22 {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("students");
		DBCollection collection = database.getCollection("grades");

		DBObject query = QueryBuilder.start("type").is("homework").get();

		try (DBCursor cursor = collection.find(query).sort(
				new BasicDBObject("student_id", 1).append("score", 1))) {
			int prevStudentId = -1;
			while (cursor.hasNext()) {
				DBObject document = cursor.next();
				
				int currentStudentId = getStudentId(document);
				
				if (studentIdChanged(prevStudentId, currentStudentId)){
				
					System.out.println("Removing document: " + document);
					ObjectId idToBeRemoved = getObjectId(document);
					collection.remove(new BasicDBObject("_id", idToBeRemoved));
				
				}else{
					
					System.out.println("Retaining document: " + document);
				
				}
				
				prevStudentId = currentStudentId;
			}
		}

		System.out.println("\nCount: " + collection.count(query));
	}

	private static boolean studentIdChanged(int prevStudentId, int studentId) {
		return prevStudentId != studentId;
	}

	private static ObjectId getObjectId(DBObject document) {
		return (ObjectId) document.get("_id");
	}

	private static int getStudentId(DBObject document) {
		return (int) document.get("student_id");
	}
}
