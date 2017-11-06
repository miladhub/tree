package tree;

class Power implements Applier {
    @Override
    public Integer apply(Object input) {
        return ((int) input) * ((int) input);
    }
}
