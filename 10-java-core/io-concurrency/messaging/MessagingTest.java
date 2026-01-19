public class MessagingTest {

    public static void main(String[] args) throws Exception {

        int messages = 10_000;

        // Platform threads test
        MessageQueue queue1 = new MessageQueue();
        MessageProcessor.runWithPlatformThreads(
                queue1, 100, messages);

        // Virtual threads test
        MessageQueue queue2 = new MessageQueue();
        MessageProcessor.runWithVirtualThreads(
                queue2, 1000, messages);
    }
}
