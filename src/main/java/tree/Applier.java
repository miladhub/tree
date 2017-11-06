package tree;

public interface Applier extends Node {
    Object apply(Object input);
}
