import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    // Create nodes
    String url;
    int id;
    List<TreeNode> children;
    // Level 4: Confirm Height of 3;
    int depth;
    
    // Initialize the node
    public TreeNode(String url, int id, int depth) {
        this.url = url;
        this.id = id;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    // Add a child node
    public void addChild(TreeNode child) {
        children.add(child);
    }
}
