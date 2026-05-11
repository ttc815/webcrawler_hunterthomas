# Hunter Crawler

This project uses jsoup to fetch the Hunter homepage, print the page title, and print all discovered links.

The link extraction logic is adapted from the jsoup ListLinks example:
https://jsoup.org/cookbook/extracting-data/example-list-links

# Project Structure
WEBCRAWLER_HUNTERTHOMAS/
├── Level 1/ Fetches www.hunter.cuny.edu from the Internet and URLs extracted from main page.
├── Level 2/ Uses the URLs fetched from Level 1, changes to relative links and filters out deeper subdomains.
├── Level 3/ Creates hashmap to build n-ary tree with the relative url as the "key". Filters out duplicates. All urls link to the mainpage in this tree currently.
├── Level 4/ Builds TreeIterator to traverse the tree in level-order during the runtime that is at least a height of 3.
├── Level 5/ Adds Dot Formatting to the TreeIterator to produce image of graph.
├── lib/
│   └── jsoup-1.22.2.jar
├── Resources/
│   └── Screenshots/
│   └── Project Log/
└── README.md
## Build
mvn clean package

## Run
java -jar target/hunter-crawler-jar-with-dependencies.jar

Or with a custom URL:
java -jar target/hunter-crawler-jar-with-dependencies.jar https://www.hunter.cuny.edu/

cd "Level 5"
javac -cp ".;../lib/jsoup-1.22.2.jar" *.java
jar cfe level5.jar level5test *.class
java -cp "level5.jar;../lib/jsoup-1.22.2.jar" level5test

Hedley, J. (n.d.). ListLinks.java. GitHub. Retrieved April 24, 2026, from https://github.com/jhy/jsoup/blob/master/src/main/java/org/jsoup/examples/ListLinks.java