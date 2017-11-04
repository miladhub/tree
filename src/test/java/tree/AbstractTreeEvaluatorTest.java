package tree;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractTreeEvaluatorTest {
    private final NodesRepo repo = createRepo();
    private final TreeEvaluator evaluator = new TreeEvaluator(repo);

    protected abstract NodesRepo createRepo();
    
    @Test
    public void evaluatesSingleNode() throws Exception {
        repo.add(new Number("num", 42));
        assertEquals(42, evaluator.eval("num"));
    }

    @Test
    public void sumOfSum() throws Exception {
        Number one = new Number("one", 1);
        Number two = new Number("two", 2);
        Sum three = new Sum("three", one, two);
        Number five = new Number("five", 5);
        Sum eight = new Sum("eight", three, five);

        repo.add(eight);

        assertEquals(8, evaluator.eval("eight"));
    }

    @Test
    public void sumOfPower() throws Exception {
        Number two = new Number("two", 2);
        Number three = new Number("three", 3);
        Power four = new Power("four", two);
        Power nine = new Power("nine", three);
        Sum thirteen = new Sum("thirteen", four, nine);

        repo.add(thirteen);

        assertEquals(13, evaluator.eval("thirteen"));
    }

    @Test @Ignore
    public void innerNode() throws Exception {
        Number two = new Number("two", 2);
        Number three = new Number("three", 3);
        Power four = new Power("four", two);
        Power nine = new Power("nine", three);
        Sum thirteen = new Sum("thirteen", four, nine);

        repo.add(thirteen);

        assertEquals(4, evaluator.eval("four"));
    }

    @Test
    public void twoTrees() throws Exception {
        Number one = new Number("one", 1);
        Number two = new Number("two", 2);
        Sum three = new Sum("three", one, two);
        Number five = new Number("five", 5);
        Sum eight = new Sum("eight", three, five);

        Number node = new Number("num", 42);

        repo.add(eight);
        repo.add(node);

        assertEquals(8, evaluator.eval("eight"));
        assertEquals(42, evaluator.eval("num"));
    }
}
