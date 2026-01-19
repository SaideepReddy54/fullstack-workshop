public class UserService {

    public User getUser(Long id) {
        sleep(500);
        return new User(id, "User-" + id);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}