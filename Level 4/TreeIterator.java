import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
    
public class TreeIterator implements Iterator<TreeNode> {
    private Queue<TreeNode> queue = new LinkedList<>();

    public TreeIterator(TreeNode root) {
        if (root != null) {
            queue.offer(root);
        }
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public TreeNode next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("No more elements");
        }
        TreeNode current = queue.remove();
        for (TreeNode child : current.children) {
            queue.offer(child);
        }
        return current;
    }
}
