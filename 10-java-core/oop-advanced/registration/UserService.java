import java.util.HashSet;
import java.util.Set;

public class UserService {

    // Simulated database
    private static final Set<String> EMAIL_DB = new HashSet<>();

    public void register(User user)
            throws ValidationException, DuplicateUserException {

        if (EMAIL_DB.contains(user.getEmail())) {
            throw new DuplicateUserException("Email already registered");
        }

        EMAIL_DB.add(user.getEmail());
    }

    // Simulated DB access using try-with-resources
    public void findByEmail(String email) throws DatabaseException {
        try (FakeDBConnection conn = new FakeDBConnection()) {
            // simulate DB access
        } catch (Exception e) {
            throw new DatabaseException("Database access failed");
        }
    }

    // Dummy AutoCloseable resource
    static class FakeDBConnection implements AutoCloseable {
        @Override
        public void close() {
            // close resource
        }
    }
}
