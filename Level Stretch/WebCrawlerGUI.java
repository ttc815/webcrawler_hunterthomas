import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class WebCrawlerGUI {
    public static void show(Tree tree, int pagesFetched, int uniqueUrlCount) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hunter Web Crawler Tree");

            JLabel header = new JLabel(
                "Hunter Web Crawler Tree | Pages fetched: " + pagesFetched +
                " | Unique URLs: " + uniqueUrlCount
            );

            header.setFont(new Font("SansSerif", Font.BOLD, 16));

            DefaultMutableTreeNode swingRoot = buildSwingNode(tree.getRoot());
            JTree jTree = new JTree(swingRoot);

            JScrollPane scrollPane = new JScrollPane(jTree);

            frame.setLayout(new BorderLayout());
            frame.add(header, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setSize(900, 650);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static DefaultMutableTreeNode buildSwingNode(TreeNode node) {
        DefaultMutableTreeNode swingNode =
            new DefaultMutableTreeNode("ID " + node.id + ": " + node.url);

        for (TreeNode child : node.children) {
            swingNode.add(buildSwingNode(child));
        }

        return swingNode;
    }
}