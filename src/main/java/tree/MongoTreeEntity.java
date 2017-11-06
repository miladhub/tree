package tree;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("trees")
class MongoTreeEntity {
    @Id @SuppressWarnings("unused")
    private final String id;
    private final Tree tree;

    @SuppressWarnings("unused")
    private MongoTreeEntity() {
        this.id = null;
        this.tree = null;
    }

    MongoTreeEntity(Tree tree) {
        this.id = tree.id();
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }
}
