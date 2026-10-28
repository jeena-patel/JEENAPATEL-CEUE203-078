
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ColumnMapper {

    public static void populate(
            Object obj, String[] headers, String[] data)
            throws Exception {

        if (headers.length != data.length) {
            throw new IllegalArgumentException(
                    "Header and data lengths must match");
        }

        Map<String, String> values = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            values.put(headers[i], data[i]);
        }

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            String columnName =
                    field.getAnnotation(Column.class).name();

            // Missing columns leave fields unchanged.
            if (!values.containsKey(columnName)) {
                continue;
            }

            String value = values.get(columnName);
            field.setAccessible(true);

            if (field.getType() == String.class) {
                field.set(obj, value);
            } else if (field.getType() == int.class) {
                field.setInt(obj, Integer.parseInt(value));
            }
        }
    }
}
