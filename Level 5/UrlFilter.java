import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class UrlFilter {
    private static final String HUNTER_HOST = "hunter.cuny.edu";
    private static final String HUNTER_WWW_HOST = "www.hunter.cuny.edu";

    public List<String> filterToRelativeHunterLinks(List<String> absoluteLinks) {
        List<String> filteredLinks = new ArrayList<>();

        for (String link : absoluteLinks) {
            if (isAcceptedHunterUrl(link)) {
                filteredLinks.add(toRelativeUrl(link));
            }
        }

        return filteredLinks;
    }

    public boolean isAcceptedHunterUrl(String url) {
        try {
            URI uri = new URI(url);

            String scheme = uri.getScheme();
            String host = uri.getHost();

            if (scheme == null || host == null) {
                return false;
            }

            scheme = scheme.toLowerCase();
            host = host.toLowerCase();

            boolean isHttp = scheme.equals("http") || scheme.equals("https");
            boolean isHunterHost = host.equals(HUNTER_HOST) || host.equals(HUNTER_WWW_HOST);

            return isHttp && isHunterHost;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public String toRelativeUrl(String url) {
        try {
            URI uri = new URI(url);

            String path = uri.getRawPath();
            String query = uri.getRawQuery();
            //Commented out for now to lessen amount of pages being crawled
            // String fragment = uri.getRawFragment();

            if (path == null || path.isEmpty()) {
                path = "/";
            }

            String relativeUrl = path;

            if (query != null && !query.isEmpty()) {
                relativeUrl += "?" + query;
            }

            // if (fragment != null && !fragment.isEmpty()) {
            //     relativeUrl += "#" + fragment;
            // }

            return relativeUrl;
        } catch (URISyntaxException e) {
            return "";
        }
    }
}