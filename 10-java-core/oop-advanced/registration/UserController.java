import java.util.ArrayList;
import java.util.List;

public class UserController {

    private UserService service = new UserService();

    public Result register(String name, String email, String password, int age) {

        List<String> errors = new ArrayList<>();

        try {
            User user = new User(name, email, password, age);
            service.register(user);
            return Result.success("User registered successfully");

        } catch (ValidationException e) {
            errors.add(e.getMessage());

        } catch (DuplicateUserException e) {
            errors.add(e.getMessage());

        } catch (Exception e) {
            errors.add("Unexpected error occurred");
        }

        return Result.failure(errors);
    }
}
