import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class LinkExtractor {
    public List<String> extractLinks(Document document) {
        List<String> links = new ArrayList<>();

        Elements linkElements = document.select("a[href]");

        for (Element linkElement : linkElements) {
            String absoluteUrl = linkElement.attr("abs:href");

            if (!absoluteUrl.isEmpty()) {
                links.add(absoluteUrl);
            }
        }

        return links;
    }
}