package org.punnoose.mongodb.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {

	public static void main(String[] args) {
		
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(SparkFormHandling.class,
				"/");

		Spark.get(new Route("/"){

			@Override
			public Object handle(Request arg0, Response arg1) {
				Template template;
				StringWriter writer = new StringWriter();
				try {
					template = config.getTemplate("fruits.ftl");
					Map<String, List<String>> fruitsMap = new HashMap<>();
					fruitsMap.put("fruits", Arrays.asList("Apple", "Orange", "Peach"));
					template.process(fruitsMap, writer);
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
		
		Spark.post(new Route("/favfruit"){
			@Override
			public Object handle(Request req, Response res) {
				return "Your Favorate fruit is: " + req.queryParams("fruit");
			}
		});
		
	}

}
