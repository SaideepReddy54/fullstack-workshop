import java.util.regex.Pattern;

public class User {

    private String name;
    private String email;
    private String password;
    private int age;

    public User(String name, String email, String password, int age)
            throws ValidationException {

        validateName(name);
        validateEmail(email);
        validatePassword(password);
        validateAge(age);

        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    private void validateName(String name) throws ValidationException {
        if (name == null || name.length() < 2 || name.length() > 50) {
            throw new ValidationException("Name must be 2-50 characters");
        }
    }

    private void validateEmail(String email) throws ValidationException {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || !Pattern.matches(regex, email)) {
            throw new ValidationException("Invalid email");
        }
    }

    private void validatePassword(String password) throws ValidationException {
        if (password == null ||
                password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[0-9].*")) {

            throw new ValidationException(
                    "Password must be 8+ chars with 1 uppercase and 1 number");
        }
    }

    private void validateAge(int age) throws ValidationException {
        if (age < 13) {
            throw new ValidationException("Must be 13+");
        }
    }

    public String getEmail() {
        return email;
    }
}
