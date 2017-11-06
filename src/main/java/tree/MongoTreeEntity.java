package tree;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("trees")
class MongoTreeEntity {
    @Id
    private String id;
    Tree tree;

    private MongoTreeEntity() {}

    MongoTreeEntity(Tree tree) {
        this.id = tree.id();
        this.tree = tree;
    }
}
