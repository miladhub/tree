package tree;

class Sum implements Node {
    private final String id;
    private final Node left;
    private final Node right;

    private Sum() {
        this(null, null, null);
    }

    Sum(String id, Node left, Node right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Integer eval() {
        return (int) left.eval() + (int) right.eval();
    }
}
