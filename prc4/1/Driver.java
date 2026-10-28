
public class Driver {
    public static void main(String[] args) {
        String[] passwords = {
            "abc",
            "abcdefgh",
            "Abcd1234!",
            "Hello123"
        };

        for (String pw : passwords) {
            System.out.println("\nPassword: " + pw);

            PasswordChecker.checkRules(pw);

            System.out.println("Strength: "
                    + PasswordChecker.strength(pw));
        }
    }
}
