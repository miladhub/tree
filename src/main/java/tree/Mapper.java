package tree;

public class Mapper implements Tree {
    private final String id;
    private final Applier applier;
    private final Tree tree;

    @SuppressWarnings("unused")
    public Mapper() {
        this(null, null, null);
    }

    Mapper(String id, Applier applier, Tree tree) {
        this.id = id;
        this.applier = applier;
        this.tree = tree;
    }

    @Override
    public String id() {
        return id;
    }

    Applier getApplier() {
        return applier;
    }

    public Tree getTree() {
        return tree;
    }
}
