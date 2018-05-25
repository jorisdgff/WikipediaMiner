package nl.jorisdgff.wikipediaminer.saver;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import nl.jorisdgff.wikipediaminer.common.WikipediaArticle;
import org.bson.Document;

public class Saver {

    private static Saver instance;
    private MongoCollection<Document> collection;

    private Saver(){

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("WikipediaMiner");
        collection = database.getCollection("articles");
    }

    public void saveArticle(WikipediaArticle article){

        System.out.println("Saving article " + article.getTitle());

        Document doc = new Document();
        doc.append("title", article.getTitle());
        doc.append("text", article.getText());
        collection.insertOne(doc);
    }

    public static Saver getInstance() {

        if(instance == null){

            instance = new Saver();
        }

        return instance;
    }
}
