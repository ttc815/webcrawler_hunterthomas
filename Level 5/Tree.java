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

    public void printTree() {
        System.out.println("digraph Hunter_Web_Crawler {");
        System.out.println("    node [shape=box, style=filled];");

        TreeIterator iterator = iterator();
        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
                System.out.println("    " + "node" + node.id + " [label=\"" + node.url + "\"];\n");
                for (TreeNode child : node.children) {
                String childName = "node" + child.id;
                System.out.println("    " + "node" + node.id + " -> " + childName + ";");
            }
        }

        System.out.println("}");
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
}
