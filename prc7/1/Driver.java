
import java.util.List;

public class Driver {
    public static void main(String[] args)
            throws IllegalAccessException {

        SignupForm form = new SignupForm(
                "   ",
                "jeena@example.com",
                "verylongpassword123"
        );

        List<String> errors = FormValidator.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Validation errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}
