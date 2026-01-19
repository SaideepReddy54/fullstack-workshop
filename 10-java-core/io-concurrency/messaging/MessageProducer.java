public class MessageProducer {

    private final MessageQueue queue;

    public MessageProducer(MessageQueue queue) {
        this.queue = queue;
    }

    public void produceMessages(int count) {
        for (int i = 1; i <= count; i++) {
            queue.publish(
                    new Message(i,
                            "Message-" + i,
                            i % 5));
        }
        System.out.println("Produced " + count + " messages");
    }
}
