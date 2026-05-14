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
        System.out.print(toDotString());
    }

    public String toDotString() {
        StringBuilder dot = new StringBuilder();

        dot.append("digraph HunterTree {\n");
        dot.append("    rankdir=LR;\n");
        dot.append("    nodesep=0.25;\n");
        dot.append("    ranksep=0.75;\n");

        dot.append("    node [\n");
        dot.append("        shape=box,\n");
        dot.append("        style=\"rounded,filled\",\n");
        dot.append("        fillcolor=lightblue,\n");
        dot.append("        fontsize=10,\n");
        dot.append("        margin=0.06\n");
        dot.append("    ];\n");

        dot.append("    edge [\n");
        dot.append("        arrowsize=0.5\n");
        dot.append("    ];\n");

        TreeIterator iterator = iterator();

        while (iterator.hasNext()) {
            TreeNode node = iterator.next();

            dot.append("    node")
            .append(node.id)
            .append(" [label=\"")
            .append(escapeDot(shortenLabel(node.url)))
            .append("\", ")
            .append(getDepthStyle(node.depth))
            .append("];\n");

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

    private String getDepthStyle(int depth) {
        if (depth == 0) {
            return "fillcolor=\"#1f4e79\", fontcolor=\"white\", fontsize=18, penwidth=3";
        } else if (depth == 1) {
            return "fillcolor=\"#5b9bd5\", fontcolor=\"white\", fontsize=14, penwidth=2.5";
        } else if (depth == 2) {
            return "fillcolor=\"#9dc3e6\", fontcolor=\"black\", fontsize=12, penwidth=2";
        } else if (depth == 3) {
            return "fillcolor=\"#bdd7ee\", fontcolor=\"black\", fontsize=10, penwidth=1.5";
        } else {
            return "fillcolor=\"#ddebf7\", fontcolor=\"black\", fontsize=9, penwidth=1";
        }
    }

    private String shortenLabel(String text) {
        if (text.length() > 45) {
            return text.substring(0, 42) + "...";
        }

        return text;
    }

    private String escapeDot(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

