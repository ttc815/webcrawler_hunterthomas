# Hunter Crawler

This project uses jsoup to fetch the Hunter homepage, print the page title, and print all discovered links.

The link extraction logic is adapted from the jsoup ListLinks example:
https://jsoup.org/cookbook/extracting-data/example-list-links

# Project Structure
```text
WEBCRAWLER_HUNTERTHOMAS/
│
├── Level 1/
│   └── Fetches www.hunter.cuny.edu and extracts URLs
│
├── Level 2/
│   └── Filters URLs, converts to relative links,
│       removes deeper subdomains and duplicates
│
├── Level 3/
│   └── Builds an n-ary tree using a HashMap
│       with relative URLs as keys
│
├── Level 4/
│   └── Builds a TreeIterator and performs
│       level-order traversal on the runtime tree
│
├── Level 5/
│   └── Generates Graphviz DOT output to
│       visualize the crawler tree
│
├── lib/
│   └── jsoup-1.22.2.jar
│
├── Resources/
│
├── Screenshots/
│
├── Project Log/
│
└── README.md
```
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

## References
Hedley, J. (n.d.). ListLinks.java. GitHub. Retrieved April 24, 2026, from https://github.com/jhy/jsoup/blob/master/src/main/java/org/jsoup/examples/ListLinks.java

GenAI - used for GraphViz Formatting