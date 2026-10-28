
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormValidator {

    public static List<String> validate(Object obj)
            throws IllegalAccessException {

        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (value == null ||
                    value.toString().trim().isEmpty()) {
                    errors.add(field.getName() + " must not be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)
                    && value != null) {
                int max = field.getAnnotation(MaxLength.class).value();

                if (value.toString().length() > max) {
                    errors.add(field.getName()
                            + " must not exceed " + max + " characters");
                }
            }
        }

        return errors;
    }
}
