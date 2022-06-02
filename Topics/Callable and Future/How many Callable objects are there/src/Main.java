import java.util.concurrent.*;


class FutureUtils {

    public static int determineCallableDepth(Callable callable) throws Exception {
        int depth = 1;
        while (true) {
            var result = callable.call();
            if (result instanceof Callable) {
                callable = (Callable) result;
                depth++;
            } else {
                return depth;
            }
        }
    }
}