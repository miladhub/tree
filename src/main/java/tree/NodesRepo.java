package tree;

import java.util.Optional;

public interface NodesRepo {
    void add(Node node);
    Optional<Node> node(String nodeId);
}
