package tree;

public class Branch implements Tree {
    private final String id;
    private final Reducer reducer;
    private final Tree left;
    private final Tree right;

    @SuppressWarnings("unused")
    public Branch() {
        this(null, null, null, null);
    }

    Branch(String id, Reducer reducer, Tree left, Tree right) {
        this.id = id;
        this.reducer = reducer;
        this.left = left;
        this.right = right;
    }

    @Override
    public String id() {
        return id;
    }

    Reducer getReducer() {
        return reducer;
    }

    Tree getLeft() {
        return left;
    }

    Tree getRight() {
        return right;
    }
}
