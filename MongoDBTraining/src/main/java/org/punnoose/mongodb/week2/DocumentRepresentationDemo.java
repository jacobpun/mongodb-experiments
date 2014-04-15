package org.punnoose.mongodb.week2;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationDemo {

	public static void main(String[] args) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", "Punnoose");
		doc.put("birthDate", new Date(111113434));
		doc.put("programmer", true);
		doc.put("languages", Arrays.asList("Java", "C++"));
		doc.put("address", new BasicDBObject("street", "center grove rd")
				.append("state", "NJ").append("country", "US"));
		System.out.println(doc);
	}
}
