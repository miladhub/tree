package tree;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import static org.junit.Assert.assertEquals;

public class MongoTreeEvaluatorTest extends AbstractTreeEvaluatorTest {
    private final Morphia morphia = new Morphia();
    private Datastore store = morphia.createDatastore(new MongoClient(), "tree");

    @Override
    protected NodesRepo createRepo() {
        return new MongoNodesRepo();
    }

    @Before
    public void mongo() throws Exception {
        Query<MongoTreeEntity> query = store.find(MongoTreeEntity.class);
        store.delete(query);
    }

    @Test
    public void sumOfSumFromJson() throws Exception {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("tree");
        MongoCollection<Document> coll = db.getCollection("trees");
        coll.insertOne(Document.parse(
                "{\n" +
                "\t\"_id\" : \"eight\",\n" +
                "\t\"className\" : \"tree.MongoTreeEntity\",\n" +
                "\t\"tree\" : {\n" +
                "\t\t\"className\" : \"tree.Branch\",\n" +
                "\t\t\"id\" : \"eight\",\n" +
                "\t\t\"reducer\" : {\n" +
                "\t\t\t\"className\" : \"tree.Sum\"\n" +
                "\t\t},\n" +
                "\t\t\"left\" : {\n" +
                "\t\t\t\"className\" : \"tree.Leaf\",\n" +
                "\t\t\t\"id\" : \"five\",\n" +
                "\t\t\t\"node\" : {\n" +
                "\t\t\t\t\"className\" : \"tree.Number\",\n" +
                "\t\t\t\t\"value\" : 5\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"right\" : {\n" +
                "\t\t\t\"className\" : \"tree.Branch\",\n" +
                "\t\t\t\"id\" : \"three\",\n" +
                "\t\t\t\"reducer\" : {\n" +
                "\t\t\t\t\"className\" : \"tree.Sum\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"left\" : {\n" +
                "\t\t\t\t\"className\" : \"tree.Leaf\",\n" +
                "\t\t\t\t\"id\" : \"one\",\n" +
                "\t\t\t\t\"node\" : {\n" +
                "\t\t\t\t\t\"className\" : \"tree.Number\",\n" +
                "\t\t\t\t\t\"value\" : 1\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"right\" : {\n" +
                "\t\t\t\t\"className\" : \"tree.Leaf\",\n" +
                "\t\t\t\t\"id\" : \"twelve\",\n" +
                "\t\t\t\t\"node\" : {\n" +
                "\t\t\t\t\t\"className\" : \"tree.Number\",\n" +
                "\t\t\t\t\t\"value\" : 12\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n"));

        TreeEvaluator evaluator = new TreeEvaluator(createRepo());

        assertEquals(18, evaluator.eval("eight"));
    }
}
