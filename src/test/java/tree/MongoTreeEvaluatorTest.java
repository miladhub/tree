package tree;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

public class MongoTreeEvaluatorTest extends AbstractTreeEvaluatorTest {
    private final Morphia morphia = new Morphia();
    private Datastore store = morphia.createDatastore(new MongoClient(), "nodes");

    @Override
    protected NodesRepo createRepo() {
        return new MongoNodesRepo();
    }

    @Before
    public void mongo() throws Exception {
        Query<MongoNodeEntity> query = store.find(MongoNodeEntity.class);
        store.delete(query);
    }
}
