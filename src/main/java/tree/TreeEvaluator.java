package tree;

import java.util.List;
import java.util.Optional;

class TreeEvaluator {
    private final NodesRepo repo;

    TreeEvaluator(NodesRepo repo) {
        this.repo = repo;
    }

    Object eval(String treeId) {
        return repo.tree(treeId)
                .flatMap(this::eval)
                .orElseGet(() -> {
                    List<Tree> trees = repo.trees();
                    return TreeTraverse.traverseTo(trees, treeId)
                            .flatMap(this::eval)
                            .orElse(null);
                });
    }

    private Optional<Object> eval(Tree t) {
        if (t instanceof Leaf) {
            ValueNode node = ((Leaf) t).getValueNode();
            return Optional.ofNullable(node.getValue());
        } else if (t instanceof Branch) {
            Branch b = (Branch) t;
            Reducer reducer = b.getReducer();
            Optional<Object> left = eval(b.getLeft());
            Optional<Object> right = eval(b.getRight());
            return left.flatMap(
                    l -> right.flatMap(
                            r -> Optional.ofNullable(reducer.reduce(l, r))
                    )
            );
        } else if (t instanceof Mapper) {
            Mapper mapper = (Mapper) t;
            Applier applier = mapper.getApplier();
            Optional<Object> input = eval(mapper.getTree());
            return input.flatMap(
                    i -> Optional.ofNullable(applier.apply(i))
            );
        }
        return Optional.empty();
    }
}
