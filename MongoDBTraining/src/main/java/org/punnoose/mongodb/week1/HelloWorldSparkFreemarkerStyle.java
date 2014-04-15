package org.punnoose.mongodb.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {
	public static void main(String[] args) {
		final Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class,
				"/");

		Spark.get(new Route("/") {
			@Override
			public Object handle(Request arg0, Response arg1) {
				Template template;
				StringWriter writer = new StringWriter();
				try {
					template = config.getTemplate("hello.ftl");
					Map<String, String> helloMap = new HashMap<>();
					helloMap.put("firstname", "Punnoose");
					template.process(helloMap, writer);
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