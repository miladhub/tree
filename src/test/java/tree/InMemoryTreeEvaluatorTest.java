package tree;

public class InMemoryTreeEvaluatorTest extends AbstractTreeEvaluatorTest {
    @Override
    protected NodesRepo createRepo() {
        return new InMemNodesRepo();
    }
}