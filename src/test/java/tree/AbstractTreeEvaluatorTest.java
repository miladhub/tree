package tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractTreeEvaluatorTest {
    private final NodesRepo repo = createRepo();
    private final TreeEvaluator evaluator = new TreeEvaluator(repo);

    protected abstract NodesRepo createRepo();

    @Test
    public void evaluatesSingleNode() throws Exception {
        repo.add(new Leaf("answer", new Number(42)));
        assertEquals(42, evaluator.eval("answer"));
    }


    @Test
    public void sum() throws Exception {
        Branch three = new Branch("three", new Sum(),
                new Leaf("one", new Number(1)),
                new Leaf("two", new Number(2)));

        repo.add(three);

        assertEquals(3, evaluator.eval("three"));
    }

    @Test
    public void sumOfSum() throws Exception {
        Branch eight = new Branch("eight", new Sum(),
                new Leaf("five", new Number(5)),
                new Branch("three", new Sum(),
                        new Leaf("one", new Number(1)),
                        new Leaf("two", new Number(2))));

        repo.add(eight);

        assertEquals(8, evaluator.eval("eight"));
    }

    @Test
    public void sumOfPower() throws Exception {
        Branch thirteen = new Branch("thirteen", new Sum(),
                new Mapper("four", new Power(), new Leaf("two", new Number(2))),
                new Mapper("nine", new Power(), new Leaf("three", new Number(3))));

        repo.add(thirteen);

        assertEquals(13, evaluator.eval("thirteen"));
    }

    @Test
    public void twoTrees() throws Exception {
        Branch thirteen = new Branch("thirteen", new Sum(),
                new Mapper("four", new Power(), new Leaf("two", new Number(2))),
                new Mapper("nine", new Power(), new Leaf("three", new Number(3))));

        Leaf answer = new Leaf("answer", new Number(42));

        repo.add(thirteen);
        repo.add(answer);

        assertEquals(13, evaluator.eval("thirteen"));
        assertEquals(42, evaluator.eval("answer"));
    }

    @Test
    public void innerNode() throws Exception {
        Branch thirteen = new Branch("thirteen", new Sum(),
                new Mapper("four", new Power(), new Leaf("two", new Number(2))),
                new Mapper("nine", new Power(), new Leaf("three", new Number(3))));

        repo.add(thirteen);

        assertEquals(4, evaluator.eval("four"));
    }
}
