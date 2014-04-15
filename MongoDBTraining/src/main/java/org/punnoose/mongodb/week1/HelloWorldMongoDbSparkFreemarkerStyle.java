package org.punnoose.mongodb.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldMongoDbSparkFreemarkerStyle {
	public static void main(String[] args) throws UnknownHostException {
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldMongoDbSparkFreemarkerStyle.class,
				"/");

		MongoClient client = new MongoClient(new ServerAddress("localhost",
				27017));
		DB database = client.getDB("test");
		final DBCollection collection = database.getCollection("names");
		
		Spark.get(new Route("/") {
			@Override
			public Object handle(Request arg0, Response arg1) {
				Template template;
				StringWriter writer = new StringWriter();
				try {
					template = config.getTemplate("hello.ftl");
					template.process(collection.findOne(), writer);
				} catch (IOException e) {
					e.printStackTrace();
					halt(500);
				} catch (TemplateException e) {
					e.printStackTrace();
					halt(500);
				}
				return writer;
			}
		});
	}
}