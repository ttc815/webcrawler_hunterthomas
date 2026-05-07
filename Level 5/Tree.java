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
        root = new TreeNode(rootUrl, rootId, 0);
    }

    public void addChild(TreeNode node) {
        root.addChild(node);
    }

    public TreeNode getRoot() {
        return root;
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

    public TreeIterator iterator() {

        return new TreeIterator(root);
    }

    public void printTreeLevelOrder() {
        TreeIterator iterator = iterator();
        int currentdepth = -1;

        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
            if (node.depth != currentdepth) {
                currentdepth = node.depth;
                System.out.println("Current Layer: " + node.depth);
            }
            System.out.println("Id: " + node.id + " Url: " + node.url);
        }
    }

    public void printDot() {
        System.out.println("digraph HunterTree {");
        System.out.println("  node [shape=box];");

        TreeIterator iterator = iterator();

        while (iterator.hasNext()) {
            TreeNode node = iterator.next();

            System.out.println("  node" + node.id + " [label=\"" + escapeDot(node.url) + "\"];");

            for (TreeNode child : node.children) {
                System.out.println("  node" + node.id + " -> node" + child.id + ";");
            }
        }

        System.out.println("}");
    }

    private String escapeDot(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

}
