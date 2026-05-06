import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;

public class level5test {
    static String startUrl = "https://www.hunter.cuny.edu";
    static PageFetcher fetcher = new PageFetcher();
    static LinkExtractor extractor = new LinkExtractor();
    static UrlFilter filter = new UrlFilter();

    static HashMap<String, Integer> naryTree = new HashMap<>();
    static int id = 1;

    static int maxDepth = 5;
    static int pagesFetched = 0;
    static int maxPagesToFetch = 40;
    static int maxNodes = 150;
    static int maxChildrenPerPage = 8;
    public static void main(String[] args) {
        Tree tree = new Tree("/", 0);
        naryTree.put("/", 0);

        buildBFS(tree.getRoot(), 0);
        // System.out.println("Level 5 Test: Output Dot Code");
        // System.out.println();

        tree.printTree();
    }

    public static void buildBFS(TreeNode parent, int depth) {
        if (depth >= maxDepth || pagesFetched >= maxPagesToFetch || id >= maxNodes) {
            return;
        }
        String nextURL = startUrl + parent.url;

        try {
            Document document = fetcher.fetch(nextURL);
            pagesFetched++;

            List<String> absoluteLinks = extractor.extractLinks(document);
            List<String> relativeHunterLinks = filter.filterToRelativeHunterLinks(absoluteLinks);

            List<TreeNode> nextChild= new ArrayList<>();
            int childrenAdded = 0;

            for (String link : relativeHunterLinks) {
                if (id >= maxNodes || childrenAdded >= maxChildrenPerPage) {
                    break;
                }
                if (!naryTree.containsKey(link)) {
                    naryTree.put(link, id);
                    TreeNode child = new TreeNode(link, id, depth + 1);
                    parent.addChild(child);
                    nextChild.add(child);
                    id++;
                    childrenAdded++;

                }
            }
            for (TreeNode child : nextChild) {
                buildBFS(child, depth + 1);
            }
        } catch (IOException e) {
            System.err.println("Could not fetch page: " + parent.url);
            System.err.println(e.getMessage());
        }
    }
}
