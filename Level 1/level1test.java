import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class level1test {
    public static void main(String[] args) {
        String startUrl = "https://www.hunter.cuny.edu/";

        PageFetcher fetcher = new PageFetcher();
        LinkExtractor extractor = new LinkExtractor();

        try {
            Document document = fetcher.fetch(startUrl);

            System.out.println("Page title:");
            System.out.println(document.title());
            System.out.println();

            System.out.println("Links:");
            List<String> links = extractor.extractLinks(document);

            for (String link : links) {
                System.out.println(link);
            }
        } catch (IOException e) {
            System.err.println("Could not fetch page: " + startUrl);
            System.err.println(e.getMessage());
        }
    }
}