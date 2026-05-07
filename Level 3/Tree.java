public class Tree {
    /*
     * Create a tree with a root node.
     * Building an n-ary tree so that "/" Hunter Homepage is the root
     * 
     * Visual For Dev:
     *         "/""
     *       /     \
     *  /about     /admissions
     */
    TreeNode root;

    public Tree(String rootUrl, int rootId) {
        /*
         * Should allow me to manually create the root when initializing the tree.
         */
        root = new TreeNode(rootUrl, rootId);
    }

    public void addChild(TreeNode node) {
        root.addChild(node);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void printTree() {
    printTree(root, 0);
}

    private void printTree(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        String indent = "";

        for (int i = 0; i < depth; i++) {
            indent += "  ";
        }

        System.out.println(indent + "- " + node.url + " (ID " + node.id + ")");

        for (TreeNode child : node.children) {
            printTree(child, depth + 1);
        }
    }

    public void printTreeSummary() {
        System.out.println("Root: " + root.url + " (ID " + root.id + ")");
        System.out.println("Number of children: " + root.children.size());
    }
}
