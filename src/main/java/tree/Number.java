package tree;

class Number implements Node {
    private final String id;
    private final int value;

    private Number() {
        this(null, 0);
    }

    Number(String id, int value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Integer eval() {
        return value;
    }
}
