package org.punnoose.mongodb.week1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HelloWorldFreemarkerStyle {
	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
		try {
			Template template = config.getTemplate("hello.ftl");
			StringWriter writer = new StringWriter();
			Map<String, String> helloMap = new HashMap<>();
			helloMap.put("firstname", "Punnoose");
			template.process(helloMap, writer);
			System.out.println(writer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}
}
