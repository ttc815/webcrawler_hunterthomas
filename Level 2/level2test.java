import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class level2test {
    public static void main(String[] args) {
        String startUrl = "https://www.hunter.cuny.edu/";

        PageFetcher fetcher = new PageFetcher();
        LinkExtractor extractor = new LinkExtractor();
        UrlFilter filter = new UrlFilter();

        try {
            Document document = fetcher.fetch(startUrl);

            List<String> absoluteLinks = extractor.extractLinks(document);
            List<String> relativeHunterLinks = filter.filterToRelativeHunterLinks(absoluteLinks);

            System.out.println("Filtered Hunter links relative to https://www.hunter.cuny.edu/:");
            System.out.println();

            for (String link : relativeHunterLinks) {
                System.out.println(link);
            }
        } catch (IOException e) {
            System.err.println("Could not fetch page: " + startUrl);
            System.err.println(e.getMessage());
        }
    }
}