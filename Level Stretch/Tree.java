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
        System.out.println("Root: " + root.url);

        for (TreeNode child : root.children) {
            System.out.println("  Child: " + child.url + " (ID " + child.id + ")");
            
        }
    }

    public TreeIterator iterator() {
        return new TreeIterator(root);
    }

    public void printTreeLevelOrder() {
        
        System.out.println("digraph HunterTree {");
        // Used GenAI for styling in order to make the tree more visually appealing
        System.out.println("    rankdir=TB;");
        System.out.println("    ratio=compress;");
        System.out.println("    nodesep=0.2;");
        System.out.println("    ranksep=0.35;");

        System.out.println("    node [");
        System.out.println("        shape=box,");
        System.out.println("        style=\"rounded,filled\",");
        System.out.println("        fillcolor=lightblue,");
        System.out.println("        fontsize=8,");
        System.out.println("        width=1.2,");
        System.out.println("        height=0.3,");
        System.out.println("        margin=0.04");
        System.out.println("    ];");

        System.out.println("    edge [");
        System.out.println("        arrowsize=0.4");
        System.out.println("    ];");
        TreeIterator iterator = iterator();

        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
            System.out.println("node" + node.id + " [label=\"" + node.url + "\"];");
            for (TreeNode child : node.children) {
                System.out.println("node" + node.id + " -> node" + child.id + ";");
            }
        }
        System.out.println("}");
    }

    public String toDotString() {
        StringBuilder dot = new StringBuilder();

        dot.append("digraph HunterTree {\n");
        dot.append("    rankdir=TB;\n");
        dot.append("    ratio=compress;\n");
        dot.append("    nodesep=0.2;\n");
        dot.append("    ranksep=0.35;\n");

        dot.append("    node [\n");
        dot.append("        shape=box,\n");
        dot.append("        style=\"rounded,filled\",\n");
        dot.append("        fillcolor=lightblue,\n");
        dot.append("        fontsize=8,\n");
        dot.append("        width=1.2,\n");
        dot.append("        height=0.3,\n");
        dot.append("        margin=0.04\n");
        dot.append("    ];\n");

        dot.append("    edge [\n");
        dot.append("        arrowsize=0.4\n");
        dot.append("    ];\n");

        TreeIterator iterator = iterator();

        while (iterator.hasNext()) {
            TreeNode node = iterator.next();

            dot.append("    node")
            .append(node.id)
            .append(" [label=\"")
            .append(escapeDot(node.url))
            .append("\"];\n");

            for (TreeNode child : node.children) {
                dot.append("    node")
                .append(node.id)
                .append(" -> node")
                .append(child.id)
                .append(";\n");
            }
        }

        dot.append("}\n");

        return dot.toString();
    }

    private String escapeDot(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

