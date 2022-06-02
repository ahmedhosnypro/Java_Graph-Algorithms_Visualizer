// You can experiment here, it won’t be checked

public class Task {
  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      if (!Thread.interrupted()) {
        System.out.println(Thread.interrupted());
      } else {
        while (true) {
          if (!Thread.interrupted()) {
            System.out.println(Thread.interrupted());
            break;
          }
        }
      }
    });
    thread.start();
    thread.interrupt();


    Thread thread1 = new Thread(() -> {
      while(Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(2_000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });
    thread1.start();
    thread1.interrupt();
    System.out.println(Thread.currentThread().isInterrupted());
  }
}
