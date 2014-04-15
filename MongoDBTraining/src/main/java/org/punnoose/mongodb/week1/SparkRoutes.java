package org.punnoose.mongodb.week1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoutes {

	public static void main(String[] args) {
		Spark.get(new Route("/echo/:thing"){
			@Override
			public Object handle(Request request, Response response) {
				return request.params(":thing");
			}
		});
	}
}
