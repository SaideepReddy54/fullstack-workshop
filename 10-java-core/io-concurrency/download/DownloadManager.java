import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadManager {

    private final ExecutorService executor;
    private final AtomicInteger totalDownloaded = new AtomicInteger(0);

    public DownloadManager(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    // Download single file
    public Future<String> download(String url) {
        Callable<String> task = () -> {
            DownloadTask downloadTask = new DownloadTask(url, totalDownloaded);
            downloadTask.run();
            return "downloads/" +
                    url.substring(url.lastIndexOf("/") + 1);
        };
        return executor.submit(task);
    }

    // Download multiple files
    public void downloadAll(List<String> urls) {

        List<Future<String>> futures = urls.stream()
                .map(this::download)
                .toList();

        for (Future<String> future : futures) {
            try {
                // Timeout handling
                future.get(30, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("Download timed out");
                future.cancel(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Download interrupted");
            } catch (ExecutionException e) {
                System.out.println("Download failed: " +
                        e.getCause());
            }
        }

        shutdown();
        System.out.println("Downloaded " +
                totalDownloaded.get() + " files total");
    }

    // Proper shutdown
    private void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}