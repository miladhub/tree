package tree;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("nodes")
class MongoNodeEntity {
    @Id
    private String id;
    Node node;

    private MongoNodeEntity() {}

    MongoNodeEntity(Node node) {
        this.id = node.id();
        this.node = node;
    }
}
