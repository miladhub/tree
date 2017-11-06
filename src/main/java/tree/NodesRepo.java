package tree;

import java.util.List;
import java.util.Optional;

public interface NodesRepo {
    void add(Tree node);
    Optional<Tree> tree(String nodeId);
    List<Tree> trees();
}
