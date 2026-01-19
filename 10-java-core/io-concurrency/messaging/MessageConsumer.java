import java.util.concurrent.atomic.AtomicInteger;

public class MessageConsumer implements Runnable {

    private final MessageQueue queue;
    private final AtomicInteger processedCount;

    public MessageConsumer(MessageQueue queue,
            AtomicInteger processedCount) {
        this.queue = queue;
        this.processedCount = processedCount;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message msg = queue.consume();
                process(msg);
                processedCount.incrementAndGet();
            }
        } catch (InterruptedException e) {
            // graceful shutdown
            Thread.currentThread().interrupt();
        }
    }

    private void process(Message msg) throws InterruptedException {
        // Simulate work (I/O-like delay)
        Thread.sleep(2);
    }
}