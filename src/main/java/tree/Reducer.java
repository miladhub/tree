package tree;

public interface Reducer extends Node {
    Object reduce(Object left, Object right);
}
