package tree;

public class Power implements Node {
    private final String id;
    private final Node input;

    Power() {
        this(null,null);
    }

    Power(String id, Node input) {
        this.id = id;
        this.input = input;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Object eval() {
        int input = (int) this.input.eval();
        return input * input;
    }
}
