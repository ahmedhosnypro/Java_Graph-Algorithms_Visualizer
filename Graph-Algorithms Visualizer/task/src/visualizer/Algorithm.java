package visualizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Algorithm {
    Vertex startPoint;

    abstract String search();
}
