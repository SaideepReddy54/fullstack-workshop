import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public void publish(Message msg) {
        queue.offer(msg);
    }

    public Message consume() throws InterruptedException {
        return queue.take();
    }

    public int size() {
        return queue.size();
    }
}
