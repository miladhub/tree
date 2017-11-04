package tree;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Optional;

public class MongoNodesRepo implements NodesRepo {
    private final Datastore store;

    MongoNodesRepo() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.accenture.aaap.sampleApp");
        store = morphia.createDatastore(new MongoClient(), "nodes");
    }

    @Override
    public void add(Node node) {
        store.save(new MongoNodeEntity(node));
    }

    @Override
    public Optional<Node> node(String nodeId) {
        return Optional.ofNullable(store.get(MongoNodeEntity.class, nodeId))
                .map(e -> e.node);
    }
}
