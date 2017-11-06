package tree;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MongoNodesRepo implements NodesRepo {
    private final Datastore store;

    MongoNodesRepo() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("tree");
        store = morphia.createDatastore(new MongoClient(), "tree");
    }

    @Override
    public void add(Tree node) {
        store.save(new MongoTreeEntity(node));
    }

    @Override
    public Optional<Tree> tree(String nodeId) {
        return Optional.ofNullable(store.get(MongoTreeEntity.class, nodeId))
                .map(e -> e.tree);
    }

    @Override
    public List<Tree> trees() {
        return store.find(MongoTreeEntity.class)
                .asList().stream()
                .map(e -> e.tree)
                .collect(Collectors.toList());
    }
}
