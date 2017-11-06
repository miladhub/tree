package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class TreeTraverse {
    static Optional<Tree> traverseTo(List<Tree> trees, String nodeId) {
        return trees.stream()
                .map(t -> nodeById(t, nodeId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private static Optional<Tree> nodeById(Tree t, String nodeId) {
        List<Tree> asList = asList(new ArrayList<>(), t);
        return asList.stream()
                .filter(n -> n.id().equals(nodeId))
                .findFirst();
    }

    private static List<Tree> asList(List<Tree> list, Tree t) {
        if (t instanceof Leaf) {
            ArrayList<Tree> res = new ArrayList<>(list);
            res.add(t);
            return res;
        } else if (t instanceof Branch) {
            Branch b = (Branch) t;
            List<Tree> fromLeft = asList(list, b.getLeft());
            List<Tree> fromRight = asList(list, b.getRight());

            ArrayList<Tree> res = new ArrayList<>(list);
            res.add(b.getLeft());
            res.addAll(fromLeft);
            res.add(b.getRight());
            res.addAll(fromRight);
            return res;
        } else if (t instanceof Mapper) {
            Mapper m = (Mapper) t;
            List<Tree> fromTree = asList(list, m.getTree());

            ArrayList<Tree> res = new ArrayList<>(list);
            res.add(m.getTree());
            res.addAll(fromTree);
            return res;
        }
        return list;
    }
}
