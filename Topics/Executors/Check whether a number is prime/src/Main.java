import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            executor.submit(() -> new PrintIfPrimeTask(number).run());
        }

        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        BigInteger bigInteger = BigInteger.valueOf(number);
        if (number >= 2 && bigInteger.isProbablePrime((int) Math.log(number))) {
            System.out.println(number);
        }
    }
}