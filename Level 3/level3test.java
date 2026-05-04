import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;

public class level3test {
    public static void main(String[] args) {
        String startUrl = "https://www.hunter.cuny.edu";

        PageFetcher fetcher = new PageFetcher();
        LinkExtractor extractor = new LinkExtractor();
        UrlFilter filter = new UrlFilter();

        HashMap<String, Integer> naryTree = new HashMap<>();
        Tree tree = new Tree("/", 0);

        // Initialize the tree with the root node
        int id = 1;
        int duplicates = 0;

        try {
            // Level 2 Test Code Copied Over
            Document document = fetcher.fetch(startUrl);

            List<String> absoluteLinks = extractor.extractLinks(document);
            List<String> relativeHunterLinks = filter.filterToRelativeHunterLinks(absoluteLinks);

            for (String link : relativeHunterLinks) {
                // Filters Duplicate Links
                if (!naryTree.containsKey(link)) {
                    naryTree.put(link, id);
                    tree.addChild(new TreeNode(link, id));
                    id++;
                }
            }
            System.out.println("Level 3 Test Using HashMap");
            System.out.println("Filtered Hunter links relative to https://www.hunter.cuny.edu/:");
            System.out.println();

            for (String link: naryTree.keySet()) {
                System.out.println("Id: " + naryTree.get(link) + ", Link: " + link);
            }
            System.out.println("Total unique links: " + naryTree.size());
            System.out.println("Total duplicates: " + duplicates);

        } catch (Exception e) {
            System.err.println("Could not fetch page: " + startUrl);
            System.err.println(e.getMessage());
        }


    }
}
