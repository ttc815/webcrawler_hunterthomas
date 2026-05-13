import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;


public class levelstretchtest {
    static String startUrl = "https://www.hunter.cuny.edu";
    static PageFetcher fetcher = new PageFetcher();
    static LinkExtractor extractor = new LinkExtractor();
    static UrlFilter filter = new UrlFilter();

    static HashMap<String, Integer> naryTree = new HashMap<>();
    static int id = 1;

    static int maxDepth = 5;
    static int pagesFetched = 0;
    static int maxPagesToFetch = 40;

    public static void main(String[] args) {
        System.out.println("Level Stretch Test");
        System.out.println("==================");
        System.out.println();

        System.out.println("Building Hunter web crawler tree...");
        Tree tree = new Tree("/", 0);
        naryTree.put("/", 0);

        buildDFS(tree.getRoot(), 0);

        System.out.println("Tree built.");
        System.out.println("Pages fetched: " + pagesFetched);
        System.out.println("Unique URLs: " + naryTree.size());
        System.out.println();

        System.out.println("Stretch Goal 1: Generate GraphViz image directly");
        try {
            GraphVizImageGenerator.generateSvg(tree, "hunter_tree.svg");
            System.out.println("SUCCESS: SVG image generated: hunter_tree.svg");
        } catch (Throwable e) {
            System.err.println("FAILED: Could not generate SVG image.");
            System.err.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Stretch Goal 2: Open GUI tree viewer");
        System.out.println("Opening GUI window...");

        WebCrawlerGUI.show(tree, pagesFetched, naryTree.size());
    }
    public static void buildDFS(TreeNode parent, int depth) {
        if (depth >= maxDepth || pagesFetched >= maxPagesToFetch) {
            return;
        }

        String nextURL = startUrl + parent.url;

        try {
            Document document = fetcher.fetch(nextURL);
            pagesFetched++;

            List<String> absoluteLinks = extractor.extractLinks(document);
            List<String> relativeHunterLinks = filter.filterToRelativeHunterLinks(absoluteLinks);

            List<TreeNode> nextChildren = new ArrayList<>();

            for (String link : relativeHunterLinks) {
                if (!naryTree.containsKey(link)) {
                    naryTree.put(link, id);

                    TreeNode child = new TreeNode(link, id, depth + 1);
                    parent.addChild(child);
                    nextChildren.add(child);

                    id++;
                }
            }

            for (TreeNode child : nextChildren) {
                buildDFS(child, depth + 1);
            }

        } catch (IOException e) {
            System.err.println("Could not fetch page: " + nextURL);
            System.err.println(e.getMessage());
        }
    }
}