package Projects.ProgrammierprüfungErgebnisVerteilung;

public class User {
    private String uKuerzel = "uKürzel";
    private boolean isStudent = true;
    private String email = uKuerzel + "@" + (isStudent ? "student." : "") + "kit.edu";
    private String password = "MeinPasswort";

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
