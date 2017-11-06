package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemNodesRepo implements NodesRepo {
    private List<Tree> trees = new ArrayList<>();

    @Override
    public void add(Tree node) {
        trees.add(node);
    }

    @Override
    public Optional<Tree> tree(String nodeId) {
        return trees.stream().filter(n -> n.id().equals(nodeId)).findFirst();
    }

    @Override
    public List<Tree> trees() {
        return trees;
    }
}
