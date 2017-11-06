package tree;

public class Leaf implements Tree {
    private final String id;
    private final ValueNode node;

    @SuppressWarnings("unused")
    public Leaf() {
        this(null, null);
    }

    Leaf(String id, ValueNode node) {
        this.id = id;
        this.node = node;
    }

    @Override
    public String id() {
        return id;
    }

    ValueNode getValueNode() {
        return node;
    }
}
