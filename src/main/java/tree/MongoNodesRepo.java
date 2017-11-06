package tree;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MongoNodesRepo implements NodesRepo {
    private final Datastore store;

    MongoNodesRepo() {
        store = new Morphia().createDatastore(new MongoClient(), "tree");
    }

    @Override
    public void add(Tree node) {
        store.save(new MongoTreeEntity(node));
    }

    @Override
    public Optional<Tree> tree(String nodeId) {
        return Optional.ofNullable(store.get(MongoTreeEntity.class, nodeId))
                .map(MongoTreeEntity::getTree);
    }

    @Override
    public List<Tree> trees() {
        return store.find(MongoTreeEntity.class)
                .asList().stream()
                .map(MongoTreeEntity::getTree)
                .collect(Collectors.toList());
    }
}
