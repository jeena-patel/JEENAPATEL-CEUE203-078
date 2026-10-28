
import java.lang.reflect.Method;

public class Driver {
    public static void main(String[] args)
            throws Exception {

        MyTests tests = new MyTests();
        int count = 0;

        for (Method method : MyTests.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                method.invoke(tests);
                count++;
            }
        }

        System.out.println("Tests run: " + count);
    }
}
