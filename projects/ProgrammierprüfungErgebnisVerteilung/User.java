package Projects.ProgrammierprüfungErgebnisVerteilung;

public class User {
    private String uKuerzel = "uhrga";
    private boolean isStudent = true;
    private String email = uKuerzel + "@" + (isStudent ? "student." : "") + "kit.edu";
    private String password = "DiesesPasswortbringtmichinmein1.Fachsemseter!";

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
