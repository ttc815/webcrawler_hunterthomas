import java.io.File;
import java.io.IOException;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.parse.Parser;
import guru.nidi.graphviz.model.MutableGraph;

public class GraphVizImageGenerator {

    public static void generateSvg(Tree tree, String outputFileName) throws IOException {
        String dot = tree.toDotString();

        MutableGraph graph = new Parser().read(dot);

        Graphviz.fromGraph(graph)
                .width(1400)
                .render(Format.SVG)
                .toFile(new File(outputFileName));
    }
}