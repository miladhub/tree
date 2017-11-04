package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemNodesRepo implements NodesRepo {
    private List<Node> nodes = new ArrayList<>();

    @Override
    public void add(Node node) {
        nodes.add(node);
    }

    @Override
    public Optional<Node> node(String nodeId) {
        return nodes.stream().filter(n -> n.id().equals(nodeId)).findFirst();
    }
}
