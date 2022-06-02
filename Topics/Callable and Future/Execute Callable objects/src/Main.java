import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;


class FutureUtils {

    public static int executeCallableObjects(List<Future<Callable<Integer>>> items) {
        Collections.reverse(items);

        int ret = 0;
        try {
            for (var f : items) {
                ret += f.get().call();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }
}