import java.util.concurrent.atomic.AtomicInteger;

public class DownloadTask implements Runnable {

    private final String url;
    private final AtomicInteger completedCount;

    public DownloadTask(String url, AtomicInteger completedCount) {
        this.url = url;
        this.completedCount = completedCount;
    }

    @Override
    public void run() {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        int[] progress = { 0, 25, 50, 75, 100 };

        try {
            for (int p : progress) {
                // Check for interruption
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("[" + Thread.currentThread().getName() +
                            "] Download interrupted: " + fileName);
                    return;
                }

                System.out.println("[" + Thread.currentThread().getName() +
                        "] Downloading " + fileName + "... " + p + "%");

                Thread.sleep(1000); // simulate download time
            }

            completedCount.incrementAndGet();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt flag
            System.out.println("[" + Thread.currentThread().getName() +
                    "] Download interrupted: " + fileName);
        }
    }
}