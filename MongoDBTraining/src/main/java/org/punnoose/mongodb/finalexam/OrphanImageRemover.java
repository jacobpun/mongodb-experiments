package org.punnoose.mongodb.finalexam;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

public class OrphanImageRemover {

	private static final String ALBUMS_COLLECTION_NAME = "albums";
	private static final String IMAGES_COLLECTION_NAME = "images";
	private static final String DB_NAME = "q7";
	
	private static DBCollection albumsCollection = null;

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient(
				new ServerAddress("localhost:27017"));
		
		DB db = client.getDB(DB_NAME);
		DBCollection imagesCollection = db.getCollection(IMAGES_COLLECTION_NAME);
		albumsCollection = db.getCollection(ALBUMS_COLLECTION_NAME);

		DBCursor imagesCursor = imagesCollection.find();
		while(imagesCursor.hasNext()){
			DBObject imageObj = imagesCursor.next();
			int imageId = (int) imageObj.get("_id");
			if(isOrphanImage(imageId)){
				System.out.println(imageId + " is orphan. Hence removing");
				imagesCollection.remove(QueryBuilder.start("_id").is(imageId).get());
			}
		}
		
	}

	private static boolean isOrphanImage(int imageId) {
		return albumsCollection.count(QueryBuilder.start("images").is(imageId).get()) == 0;
	}
}
