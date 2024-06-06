package org.example.project2sem2;

public class LoginController {

    private String Username;
    private String Email;
    private String Password;

    public LoginController(String Username, String Email, String Password) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEmail(String email) {
        Email = email;

    }

    public void setPassword(String password) {
        Password = password;
    }
}
