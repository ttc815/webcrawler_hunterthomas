# Hunter Crawler

This project uses jsoup to fetch the Hunter homepage, print the page title, and print all discovered links.

The link extraction logic is adapted from the jsoup ListLinks example:
https://jsoup.org/cookbook/extracting-data/example-list-links

## Build
mvn clean package

## Run
java -jar target/hunter-crawler-jar-with-dependencies.jar

Or with a custom URL:
java -jar target/hunter-crawler-jar-with-dependencies.jar https://www.hunter.cuny.edu/