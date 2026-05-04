import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    // Create nodes
    String url;
    int id;
    List<TreeNode> children;
    
    // Initialize the node
    public TreeNode(String url, int id) {
        this.url = url;
        this.id = id;
        this.children = new ArrayList<>();
    }

    // Add a child node
    public void addChild(TreeNode child) {
        children.add(child);
    }
}
