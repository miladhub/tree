package tree;

class TreeEvaluator {
    private final NodesRepo repo;

    TreeEvaluator(NodesRepo repo) {
        this.repo = repo;
    }

    Object eval(String nodeId) {
        return repo.node(nodeId)
                .map(Node::eval)
                .orElse(null);
    }
}
