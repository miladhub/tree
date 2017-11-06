package tree;

class Number implements ValueNode {
    private final Integer value;

    @SuppressWarnings("unused")
    private Number() {
        this(null);
    }

    Number(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
