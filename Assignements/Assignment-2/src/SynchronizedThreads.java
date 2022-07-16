import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedThreads {
    private static Integer sum = 0;
    private static Integer nonSynchronizedSum = 0;

    public synchronized static void increment() {
        sum = sum + 1;
    }

    public static void nonSynchronizedIncrement() {
        nonSynchronizedSum = nonSynchronizedSum + 1;
    }
    private static final int numOfThreads = 1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            service.execute(() ->increment());
            service.execute(() -> nonSynchronizedIncrement());
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Sum when synchronized keyword used : " + sum);
        System.out.println("Sum when synchronized keyword not used : " + nonSynchronizedSum);
    }
}
