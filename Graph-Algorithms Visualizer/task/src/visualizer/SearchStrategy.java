package visualizer;

public class SearchStrategy {
    static Algorithm strategy;

    public static void setStrategy(Algorithm strategy) {
        SearchStrategy.strategy = strategy;
    }

    static String search() {
        return strategy.search();
    }
}
