import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class WebCrawlerGUI {
    public static void show(Tree tree, int pagesFetched, int uniqueUrlCount) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hunter Web Crawler Tree");

            JLabel title = new JLabel("Hunter Web Crawler Tree");
            title.setFont(new Font("SansSerif", Font.BOLD, 18));

            JLabel subtitle = new JLabel(
                "Pages fetched: " + pagesFetched +
                " | Unique URLs: " + uniqueUrlCount +
                " | Tree shows first-discovered crawler links"
            );
            subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.add(title, BorderLayout.NORTH);
            headerPanel.add(subtitle, BorderLayout.SOUTH);

            DefaultMutableTreeNode swingRoot = buildSwingNode(tree.getRoot());
            JTree jTree = new JTree(swingRoot);

            jTree.setRootVisible(true);
            jTree.setShowsRootHandles(true);
            jTree.setFont(new Font("SansSerif", Font.PLAIN, 14));
            jTree.setRowHeight(24);

            expandFirstRows(jTree, 25);

            JScrollPane scrollPane = new JScrollPane(jTree);

            frame.setLayout(new BorderLayout());
            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static DefaultMutableTreeNode buildSwingNode(TreeNode node) {
        DefaultMutableTreeNode swingNode =
            new DefaultMutableTreeNode(
                "ID " + node.id +
                " | Depth " + node.depth +
                " | " + node.url
            );

        for (TreeNode child : node.children) {
            swingNode.add(buildSwingNode(child));
        }

        return swingNode;
    }

    private static void expandFirstRows(JTree tree, int rowsToExpand) {
        int row = 0;

        while (row < tree.getRowCount() && row < rowsToExpand) {
            tree.expandRow(row);
            row++;
        }
    }
}