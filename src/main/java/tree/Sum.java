package tree;

class Sum implements Reducer {
    public Integer reduce(Object left, Object right) {
        return (int) left + (int) right;
    }
}
